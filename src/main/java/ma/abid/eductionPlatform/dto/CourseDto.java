package ma.abid.eductionPlatform.dto;

import lombok.*;
import ma.abid.eductionPlatform.entities.User;


@AllArgsConstructor @NoArgsConstructor @Getter @Setter @Builder
public class CourseDto {
    private String title;
    private String subject;
    private String level;
    private String content;
    private User createdBy;
}
