package ma.abid.eductionPlatforme.entities;

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
@DiscriminatorValue("PDF")  // Ce sera la valeur pour file_type
public class Pdf extends CourseFile {
    private String author;  // Auteur du PDF (optionnel)
    private int pageCount;  // Nombre de pages du PDF
}
