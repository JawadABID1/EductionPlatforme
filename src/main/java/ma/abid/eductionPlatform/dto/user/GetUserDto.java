package ma.abid.eductionPlatform.dto.user;

import lombok.*;
import ma.abid.eductionPlatform.dto.role.RoleDto;
import ma.abid.eductionPlatform.entities.role.Role;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@AllArgsConstructor @NoArgsConstructor @Getter @Setter @Builder
public class GetUserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private List<RoleDto> roles;
}
