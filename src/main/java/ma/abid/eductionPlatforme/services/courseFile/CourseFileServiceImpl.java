package ma.abid.eductionPlatforme.services.courseFile;

import lombok.RequiredArgsConstructor;
import ma.abid.eductionPlatforme.dto.CourseFileDto;
import ma.abid.eductionPlatforme.entities.*;
import ma.abid.eductionPlatforme.exception.DuplicateResourceException;
import ma.abid.eductionPlatforme.exception.ResourceNotFoundException;
import ma.abid.eductionPlatforme.mapper.CourseFileMapper;
import ma.abid.eductionPlatforme.repository.CourseFileRepository;
import ma.abid.eductionPlatforme.repository.CourseRepository;
import ma.abid.eductionPlatforme.services.courseFile.strategy.Strategy;
import ma.abid.eductionPlatforme.services.courseFile.strategy.StrategyFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseFileServiceImpl implements CourseFileService {

    private final CourseFileRepository courseFileRepository;
    private final CourseFileMapper courseFileMapper;
    private final CourseRepository courseRepository;
    private final StrategyFactory strategyFactory;

    private static final String UPLOAD_DIR = "uploads/";

    @Override
    public CourseFileDto uploadFile(Long courseId, MultipartFile file) throws ResourceNotFoundException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        String originalFilename = file.getOriginalFilename();
        Optional<CourseFile> byFileName = courseFileRepository.findByFileName(originalFilename);
        if (byFileName.isPresent()) throw new  DuplicateResourceException("A file with the same name already exists: " + originalFilename);
        String extension = getFileExtension(originalFilename);

//        Path path = Paths.get(UPLOAD_DIR + originalFilename);
        Path path = Path.of(UPLOAD_DIR+originalFilename);
        try {
            Files.createDirectories(path.getParent());
            Files.write(path, file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Failed to save file: " + e.getMessage(), e);
        }

        Strategy strategy = strategyFactory.getStrategy(extension);
        CourseFile fileType = strategy.treatFileType(originalFilename, course, path);
        courseFileRepository.save(fileType);
        return courseFileMapper.toDto(fileType);
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
