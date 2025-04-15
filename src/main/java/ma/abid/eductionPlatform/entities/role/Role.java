package ma.abid.eductionPlatform.entities.role;

import jakarta.persistence.*;
import lombok.*;

@Entity @AllArgsConstructor @NoArgsConstructor @Getter @Setter @Builder
public class Role {
    @Id
    private String role;
}
