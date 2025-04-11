package ma.abid.eductionPlatforme.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@Entity @AllArgsConstructor @NoArgsConstructor @Getter @Setter
@DiscriminatorValue("VIDEO")
public class Video extends CourseFile{
    private String videoUrl;  // URL de la vidéo
    private int duration;  // Durée de la vidéo en secondes
}
