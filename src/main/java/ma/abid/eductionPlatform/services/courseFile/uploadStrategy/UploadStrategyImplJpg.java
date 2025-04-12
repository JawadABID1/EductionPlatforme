package ma.abid.eductionPlatform.services.courseFile.uploadStrategy;

import ma.abid.eductionPlatform.entities.course.Course;
import ma.abid.eductionPlatform.entities.courseFile.CourseFile;
import ma.abid.eductionPlatform.entities.courseFile.Image;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
@Service
public class UploadStrategyImplJpg implements UploadStrategy {
    @Override
    public CourseFile treatFileType(String originalFilename, Course course, Path path) {
        return Image.builder()
                .fileName(originalFilename)
                .filePath(path.toString())
                .course(course)
                .resolution("1920x1080")
                .build();
    }
}
