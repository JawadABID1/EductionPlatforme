package ma.abid.eductionPlatforme.services.courseFile;

import ma.abid.eductionPlatforme.dto.CourseFileDto;
import ma.abid.eductionPlatforme.entities.*;
import ma.abid.eductionPlatforme.exception.DuplicateResourceException;
import ma.abid.eductionPlatforme.exception.ResourceNotFoundException;
import ma.abid.eductionPlatforme.mapper.CourseFileMapper;
import ma.abid.eductionPlatforme.repository.CourseFileRepository;
import ma.abid.eductionPlatforme.repository.CourseRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class CourseFileServiceImpl implements CourseFileService {

    private final CourseFileRepository courseFileRepository;
    private final CourseFileMapper courseFileMapper;
    private final CourseRepository courseRepository;

    private static final String UPLOAD_DIR = "uploads/";

    public CourseFileServiceImpl(CourseFileRepository courseFileRepository,
                                 CourseFileMapper courseFileMapper,
                                 CourseRepository courseRepository) {
        this.courseFileRepository = courseFileRepository;
        this.courseFileMapper = courseFileMapper;
        this.courseRepository = courseRepository;
    }

    @Override
    public CourseFileDto uploadFile(Long courseId, MultipartFile file) throws ResourceNotFoundException {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        String originalFilename = file.getOriginalFilename();
        Optional<CourseFile> byFileName = courseFileRepository.findByFileName(originalFilename);
        if (byFileName.isPresent()) throw new  DuplicateResourceException("A other file exists with same name: "+originalFilename);
        String extension = getFileExtension(originalFilename);



        // 1. Sauvegarder le fichier sur le disque
        Path path = Paths.get(UPLOAD_DIR + originalFilename);
        try {
            Files.createDirectories(path.getParent());
            Files.write(path, file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Failed to save file: " + e.getMessage(), e);
        }

        // 2. Créer l'entité appropriée
        CourseFile courseFile;
        if ("pdf".equalsIgnoreCase(extension)) {
            courseFile = Pdf.builder()
                    .fileName(originalFilename)
                    .filePath(path.toString())
                    .course(course)
                    .fileType("PDF")
                    .author("Inconnu")
                    .pageCount(0)     // à ajuster si parsé
                    .build();
        } else if ("mp4".equalsIgnoreCase(extension)) {
            courseFile = Video.builder()
                    .fileName(originalFilename)
                    .filePath(path.toString())
                    .course(course)
                    .videoUrl(path.toString()) // ou URL externe
                    .duration(0)               // à ajuster si parsé
                    .build();
        } else {
            throw new IllegalArgumentException("Unsupported file type: " + extension);
        }

        // 3. Enregistrer dans la base
        System.out.println("____________________________________start__________________");
        System.out.println("courseFile: " + courseFile.toString());
        System.out.println("____________________________________end___________________");
        courseFileRepository.save(courseFile);
        return courseFileMapper.toDto(courseFile);
    }

    @Override
    public CourseFileDto getFile(Long fileId) {
        CourseFile file = courseFileRepository.findById(fileId)
                .orElseThrow(() -> new ResourceNotFoundException("File not found"));
        return courseFileMapper.toDto(file);
    }

    @Override
    public List<CourseFileDto> getFilesByCourseId(Long courseId) {
        return courseFileRepository.findByCourseId(courseId)
                .stream()
                .map(courseFileMapper::toDto)
                .toList();
    }

    @Override
    public byte[] downloadFile(Long fileId) {
        CourseFile file = courseFileRepository.findById(fileId)
                .orElseThrow(() -> new ResourceNotFoundException("File not found"));

        Path path = Paths.get(file.getFilePath());
        try {
            return Files.readAllBytes(path);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read file", e);
        }
    }

    @Override
    public void deleteFile(Long fileId) {
        CourseFile file = courseFileRepository.findById(fileId)
                .orElseThrow(() -> new ResourceNotFoundException("File not found"));

        // Delete file from disk
        try {
            Files.deleteIfExists(Paths.get(file.getFilePath()));
        } catch (IOException e) {
            throw new RuntimeException("Failed to delete file from disk", e);
        }

        // Delete from DB
        courseFileRepository.delete(file);
    }

    private String getFileExtension(String fileName) {
        if (fileName == null || !fileName.contains(".")) {
            return "";
        }
        return fileName.substring(fileName.lastIndexOf('.') + 1);
    }
}
