package ma.abid.eductionPlatform.entities.courseFile;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity @AllArgsConstructor @NoArgsConstructor @Getter @Setter @SuperBuilder
@DiscriminatorValue("IMAGE")
public class Image extends CourseFile {
    private String resolution;
}
