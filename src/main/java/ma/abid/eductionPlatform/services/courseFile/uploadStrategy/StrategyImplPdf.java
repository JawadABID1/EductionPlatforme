package ma.abid.eductionPlatform.services.courseFile.uploadStrategy;

import ma.abid.eductionPlatform.entities.Course;
import ma.abid.eductionPlatform.entities.CourseFile;
import ma.abid.eductionPlatform.entities.Pdf;
import org.springframework.stereotype.Service;

import java.nio.file.Path;

@Service
public class StrategyImplPdf implements Strategy {
    @Override
    public CourseFile treatFileType(String originalFilename, Course course, Path path) {
        System.out.println("_________________________PDF___________________________________");
        CourseFile courseFile;
            courseFile = Pdf.builder()
                    .fileName(originalFilename)
                    .filePath(path.toString())
                    .course(course)
                    .fileType("PDF")
                    .author("Inconnu")
                    .pageCount(0)     // à ajuster si parsé
                    .build();
//        }
        return courseFile;
    }
}
