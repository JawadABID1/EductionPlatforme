package ma.abid.eductionPlatform.entities.user;

import jakarta.persistence.*;
import lombok.*;
import ma.abid.eductionPlatform.dto.role.RoleDto;
import ma.abid.eductionPlatform.entities.role.Role;

import java.util.ArrayList;
import java.util.List;

@Entity @AllArgsConstructor @NoArgsConstructor @Getter @Setter @Builder
public class AppUser {
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(unique = true, nullable = false)
    private String email;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles = new ArrayList<>();
}
