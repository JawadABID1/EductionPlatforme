package ma.abid.eductionPlatform.entities;

import jakarta.persistence.*;
import lombok.*;

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
    private User createdBy;
}
