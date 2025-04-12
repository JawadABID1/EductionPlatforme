package ma.abid.eductionPlatforme.services.courseFile.strategy;

import ma.abid.eductionPlatforme.entities.Course;
import ma.abid.eductionPlatforme.entities.CourseFile;
import ma.abid.eductionPlatforme.entities.Video;
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
