package ma.abid.eductionPlatforme.dto;

import lombok.*;
import ma.abid.eductionPlatforme.entities.Role;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter @Builder
public class PostPutUserDto {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private Role role;
}
