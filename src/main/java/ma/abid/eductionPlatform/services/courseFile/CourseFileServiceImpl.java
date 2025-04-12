package ma.abid.eductionPlatform.services.courseFile;

import lombok.RequiredArgsConstructor;
import ma.abid.eductionPlatform.dto.courseFile.CourseFileDownloadResponse;
import ma.abid.eductionPlatform.dto.courseFile.CourseFileDto;
import ma.abid.eductionPlatform.entities.course.Course;
import ma.abid.eductionPlatform.entities.courseFile.CourseFile;
import ma.abid.eductionPlatform.exception.DuplicateResourceException;
import ma.abid.eductionPlatform.exception.ResourceNotFoundException;
import ma.abid.eductionPlatform.mapper.courseFile.CourseFileMapper;
import ma.abid.eductionPlatform.repository.CourseFileRepository;
import ma.abid.eductionPlatform.repository.CourseRepository;
import ma.abid.eductionPlatform.services.courseFile.downloadStrategy.DownloadStrategy;
import ma.abid.eductionPlatform.services.courseFile.downloadStrategy.DownloadStrategyFactory;
import ma.abid.eductionPlatform.services.courseFile.uploadStrategy.UploadStrategy;
import ma.abid.eductionPlatform.services.courseFile.uploadStrategy.UploadStrategyFactory;
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
    private final UploadStrategyFactory uploadStrategyFactory;
    private final DownloadStrategyFactory downloadStrategyFactory;

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

        UploadStrategy strategy = uploadStrategyFactory.getStrategy(extension);
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
    public CourseFileDownloadResponse downloadFile(Long fileId) {
        CourseFile file = courseFileRepository.findById(fileId)
                .orElseThrow(() -> new ResourceNotFoundException("File not found"));

        Path path = Paths.get(file.getFilePath());
        try {
            byte[] fileBytes =  Files.readAllBytes(path);
            DownloadStrategy downloadStrategy = downloadStrategyFactory.getStrategy(getFileExtension(file.getFileName()));
            String contentType = downloadStrategy.getContentType(getFileExtension(file.getFileName()));
            return CourseFileDownloadResponse.builder()
                    .fileBytes(fileBytes)
                    .contentType(contentType)
                    .fileName(file.getFileName())
                    .build();
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
