package ma.abid.eductionPlatforme.services.courseFile.strategy;

import ma.abid.eductionPlatforme.entities.Course;
import ma.abid.eductionPlatforme.entities.CourseFile;

import java.nio.file.Path;

public interface Strategy {
   CourseFile treatFileType(String originalFilename, Course course, Path path);
}
