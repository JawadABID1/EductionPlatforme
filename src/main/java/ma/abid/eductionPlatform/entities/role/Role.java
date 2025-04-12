package ma.abid.eductionPlatform.entities.role;

import jakarta.persistence.*;
import lombok.*;

@Entity @AllArgsConstructor @NoArgsConstructor @Getter @Setter @Builder
public class Role {
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
//    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<User> users;
}
