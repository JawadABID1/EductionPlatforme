package ma.abid.eductionPlatform.services.courseFile.uploadStrategy;

import ma.abid.eductionPlatform.entities.course.Course;
import ma.abid.eductionPlatform.entities.courseFile.CourseFile;

import java.nio.file.Path;

public interface UploadStrategy {
   CourseFile treatFileType(String originalFilename, Course course, Path path);
}
