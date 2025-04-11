package ma.abid.eductionPlatforme.services.courseFile;

import ma.abid.eductionPlatforme.dto.CourseFileDto;
import ma.abid.eductionPlatforme.entities.Course;
import ma.abid.eductionPlatforme.entities.CourseFile;
import ma.abid.eductionPlatforme.exception.ResourceNotFoundException;
import ma.abid.eductionPlatforme.mapper.CourseFileMapper;
import ma.abid.eductionPlatforme.repository.CourseFileRepository;
import ma.abid.eductionPlatforme.repository.CourseRepository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@org.springframework.stereotype.Service
public class CourseFileServiceImpl implements CourseFileService {
    private final CourseFileRepository courseFileRepository;
    private final CourseFileMapper courseFileMapper;
    private final CourseRepository courseRepository;
    private static final String UPLOAD_DIR = "uploads/";  // Répertoire où les fichiers seront stockés

    public CourseFileServiceImpl(CourseFileRepository courseFileRepository, CourseFileMapper courseFileMapper, CourseRepository courseRepository) {
        this.courseFileRepository = courseFileRepository;
        this.courseFileMapper = courseFileMapper;
        this.courseRepository = courseRepository;
    }

    @Override
    public CourseFileDto uploadFile(Long courseId, MultipartFile file) throws ResourceNotFoundException {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        // Create a path to file
        Path path = Paths.get(UPLOAD_DIR + file.getOriginalFilename());
        try {
            Files.createDirectories(path.getParent());
            Files.write(path, file.getBytes());
             // Create et save the file in database
             CourseFile courseFile = CourseFile.builder()
                     .fileName(file.getOriginalFilename())
                     .filePath(path.toString())
                     .course(course)
                     .build();

             courseFileRepository.save(courseFile);
             return courseFileMapper.toDto(courseFile);

        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
//            throw new RuntimeException("Failed to upload file", e);
        }

    }

    @Override
    public CourseFileDto getFile(Long fileId) throws ResourceNotFoundException {
        CourseFile courseFile = courseFileRepository.findById(fileId)
                .orElseThrow(()->new ResourceNotFoundException("File not found"));
        return courseFileMapper.toDto(courseFile);
    }

    @Override
    public List<CourseFileDto> getFilesByCourseId(Long courseId) throws ResourceNotFoundException {
       List<CourseFile> courseFiles = courseFileRepository.findByCourseId(courseId);
        return courseFiles.stream().map(courseFileMapper::toDto).toList();
    }

    @Override
    public byte[] downloadFile(Long fileId) throws ResourceNotFoundException {
        CourseFile courseFile = courseFileRepository.findById(fileId)
                .orElseThrow(()-> new ResourceNotFoundException("File not found"));

        Path path = Paths.get(courseFile.getFilePath());
        try {
          return   Files.readAllBytes(path);
        } catch (IOException e) {
            throw new RuntimeException("Failed to download file", e);
//            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void deleteFile(Long fileId) throws ResourceNotFoundException {
        CourseFile courseFile = courseFileRepository.findById(fileId)
                .orElseThrow(()-> new ResourceNotFoundException("File note found"));
        Path path = Paths.get(courseFile.getFilePath());
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            throw new RuntimeException("Failed to delete file", e);
//            throw new RuntimeException(e);
        }

        courseFileRepository.delete(courseFile);
    }
}
