package ma.abid.eductionPlatforme.services.courseFile.strategy;

import ma.abid.eductionPlatforme.entities.Course;
import ma.abid.eductionPlatforme.entities.CourseFile;
import ma.abid.eductionPlatforme.entities.Pdf;
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
