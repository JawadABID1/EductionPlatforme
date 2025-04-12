package ma.abid.eductionPlatform.services.courseFile.uploadStrategy;

import ma.abid.eductionPlatform.entities.course.Course;
import ma.abid.eductionPlatform.entities.courseFile.CourseFile;
import ma.abid.eductionPlatform.entities.courseFile.Video;
import org.springframework.stereotype.Service;

import java.nio.file.Path;

@Service
public class UploadStrategyImplMp4 implements UploadStrategy {
    @Override
    public CourseFile treatFileType(String originalFilename, Course course, Path path) {
    CourseFile courseFile;
        courseFile = Video.builder()
                    .fileName(originalFilename)
                    .filePath(path.toString())
                    .course(course)
                    .videoUrl(path.toString()) // ou URL externe
                    .duration(0)               // à ajuster si parsé
                    .build();

    return courseFile;
    }
}
