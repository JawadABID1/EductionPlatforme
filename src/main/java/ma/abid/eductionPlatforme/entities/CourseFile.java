package ma.abid.eductionPlatforme.entities;

import jakarta.persistence.*;
import lombok.*;

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "file_type", discriminatorType = DiscriminatorType.STRING)
@Entity @AllArgsConstructor @NoArgsConstructor @Getter @Setter @Builder
public class CourseFile {
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String fileName;
//    private FileType fileType;
    private String filePath;
    @ManyToOne
    @JoinColumn(name = "courseId")
    private Course course;

}
