package ma.abid.eductionPlatform.mapper.role;

//import ma.abid.eductionPlatform.dto.RoleDto;
import ma.abid.eductionPlatform.dto.role.RoleDto;
import ma.abid.eductionPlatform.entities.role.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleDto toDto(Role role);
    Role toEntity(RoleDto roleDto);
}
