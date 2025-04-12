package ma.abid.eductionPlatform.services.courseFile.uploadStrategy;

import ma.abid.eductionPlatform.entities.Course;
import ma.abid.eductionPlatform.entities.CourseFile;
import ma.abid.eductionPlatform.entities.Video;
import org.springframework.stereotype.Service;

import java.nio.file.Path;

@Service
public class StrategyImplMp4 implements Strategy {
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
