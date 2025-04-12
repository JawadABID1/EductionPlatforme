package ma.abid.eductionPlatforme.dto;

import lombok.*;
import ma.abid.eductionPlatforme.entities.Course;
import ma.abid.eductionPlatforme.enumm.FileType;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter @Builder
public class CourseFileDto {
    private Long id;
    private String fileName;
    private String filePath;
    private String url;
    private Course course;
}
