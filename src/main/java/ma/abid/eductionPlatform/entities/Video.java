package ma.abid.eductionPlatform.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@DiscriminatorValue("VIDEO")  // Ce sera la valeur pour file_type
public class Video extends CourseFile {
    private String videoUrl;  // URL de la vidéo
    private int duration;  // Durée de la vidéo en secondes
}
