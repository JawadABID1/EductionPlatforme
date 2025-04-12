package ma.abid.eductionPlatform.dto.user;

import lombok.*;
import ma.abid.eductionPlatform.entities.role.Role;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter @Builder
public class GetUserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private Role role;
}
