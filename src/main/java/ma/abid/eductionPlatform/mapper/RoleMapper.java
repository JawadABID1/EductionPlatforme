package ma.abid.eductionPlatforme.mapper;

import ma.abid.eductionPlatforme.dto.RoleDto;
import ma.abid.eductionPlatforme.entities.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleDto toDto(Role role);
    Role toEntity(RoleDto roleDto);
}
