package ma.abid.eductionPlatforme.services.courseFile;

import ma.abid.eductionPlatforme.dto.CourseFileDto;
import ma.abid.eductionPlatforme.exception.ResourceNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CourseFileService {
    CourseFileDto uploadFile(Long courseId, MultipartFile file) throws ResourceNotFoundException;
    CourseFileDto getFile(Long fileId) throws ResourceNotFoundException;
    List<CourseFileDto> getFilesByCourseId(Long courseId) throws ResourceNotFoundException;
    byte[] downloadFile(Long fileId) throws ResourceNotFoundException;
    void deleteFile(Long fileId) throws ResourceNotFoundException;
}
