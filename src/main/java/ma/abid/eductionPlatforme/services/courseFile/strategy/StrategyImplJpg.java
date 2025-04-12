package ma.abid.eductionPlatforme.services.courseFile.strategy;

import ma.abid.eductionPlatforme.entities.Course;
import ma.abid.eductionPlatforme.entities.CourseFile;
import ma.abid.eductionPlatforme.entities.Image;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
@Service
public class StrategyImplJpg implements Strategy {
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
