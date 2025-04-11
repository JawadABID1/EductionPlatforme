package ma.abid.eductionPlatforme.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "file_type", discriminatorType = DiscriminatorType.STRING)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@ToString
public abstract class CourseFile {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String fileName;

    private String filePath;

    @ManyToOne
    @JoinColumn(name = "courseId")
    private Course course;

    @Column(name = "file_type", insertable = false, updatable = false)  // Pas besoin d'insertion manuelle
    private String fileType;
}
