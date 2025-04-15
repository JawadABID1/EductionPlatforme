package ma.abid.eductionPlatform.services.role;

import ma.abid.eductionPlatform.dto.role.RoleDto;
import ma.abid.eductionPlatform.exception.DuplicateResourceException;
import ma.abid.eductionPlatform.exception.ResourceNotFoundException;

import java.util.List;

public interface RoleService {
    List<RoleDto> getAllRoles();
    RoleDto getRoleByNom(String nom) throws ResourceNotFoundException;
    RoleDto createNewRole(RoleDto roleDto) throws DuplicateResourceException;
    RoleDto updateRole(Long id, RoleDto roleDto) throws ResourceNotFoundException;
    void deleteRoleByNom(Long id) throws ResourceNotFoundException;

}
