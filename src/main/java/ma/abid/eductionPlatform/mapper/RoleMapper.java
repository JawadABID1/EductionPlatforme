package ma.abid.eductionPlatform.mapper;

import ma.abid.eductionPlatform.dto.RoleDto;
import ma.abid.eductionPlatform.entities.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleDto toDto(Role role);
    Role toEntity(RoleDto roleDto);
}
