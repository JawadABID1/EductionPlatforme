package ma.abid.eductionPlatform.entities.course;

import jakarta.persistence.*;
import lombok.*;
import ma.abid.eductionPlatform.entities.user.AppUser;

@Entity @AllArgsConstructor @NoArgsConstructor @Getter @Setter @Builder
public class Course {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String subject;
    private String level;
    @Lob
    private String content;
    @ManyToOne
    @JoinColumn(name = "userId")
    private AppUser createdBy;
}
