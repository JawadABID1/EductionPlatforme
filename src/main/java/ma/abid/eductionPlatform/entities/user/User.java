package ma.abid.eductionPlatform.entities.user;

import jakarta.persistence.*;
import lombok.*;
import ma.abid.eductionPlatform.entities.role.Role;

@Entity @AllArgsConstructor @NoArgsConstructor @Getter @Setter @Builder
@Table(name = "app_user")
public class User {
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
//    @Column(unique = true
    private String username;
    @Column(unique = true)
    private String email;
    private String password;
    @ManyToOne
    @JoinColumn(name = "roleId")
    private Role role;
}
