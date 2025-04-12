package ma.abid.eductionPlatform.dto.courseFile;

import lombok.*;
import ma.abid.eductionPlatform.entities.course.Course;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter @Builder
public class CourseFileDto {
    private Long id;
    private String fileName;
    private String filePath;
    private String url;
    private Course course;
}
