package ma.abid.eductionPlatform.services.courseFile.uploadStrategy;

import ma.abid.eductionPlatform.entities.Course;
import ma.abid.eductionPlatform.entities.CourseFile;

import java.nio.file.Path;

public interface Strategy {
   CourseFile treatFileType(String originalFilename, Course course, Path path);
}
