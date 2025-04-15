package ma.abid.eductionPlatform.dto.course;

import lombok.*;
import ma.abid.eductionPlatform.entities.user.AppUser;


@AllArgsConstructor @NoArgsConstructor @Getter @Setter @Builder
public class CourseDto {
    private String title;
    private String subject;
    private String level;
    private String content;
    private AppUser createdBy;
}
