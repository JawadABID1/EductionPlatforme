package ma.abid.eductionPlatforme.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@Entity @AllArgsConstructor @NoArgsConstructor @Getter @Setter
@DiscriminatorValue("PDF")
public class Pdf extends CourseFile{
    private String author;  // Auteur du PDF (optionnel)
    private int pageCount;  // Nombre de pages du PDF
}
