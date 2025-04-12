package ma.abid.eductionPlatform.services.courseFile;

import ma.abid.eductionPlatform.dto.CourseFileDto;
import ma.abid.eductionPlatform.exception.ResourceNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface CourseFileService {
    CourseFileDto uploadFile(Long courseId, MultipartFile file) throws ResourceNotFoundException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException;
    CourseFileDto getFile(Long fileId) throws ResourceNotFoundException;
    List<CourseFileDto> getFilesByCourseId(Long courseId) throws ResourceNotFoundException;
    byte[] downloadFile(Long fileId) throws ResourceNotFoundException;
    void deleteFile(Long fileId) throws ResourceNotFoundException;
}
