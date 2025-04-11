package ma.abid.eductionPlatforme.services.role;

import ma.abid.eductionPlatforme.dto.RoleDto;
import ma.abid.eductionPlatforme.exception.DuplicateResourceException;
import ma.abid.eductionPlatforme.exception.ResourceNotFoundException;

import java.util.List;

public interface RoleService {
    List<RoleDto> getAllRoles();
    RoleDto getRoleById(Long id) throws ResourceNotFoundException;
    RoleDto createNewRole(RoleDto roleDto) throws DuplicateResourceException;
    RoleDto updateRole(Long id, RoleDto roleDto) throws ResourceNotFoundException;
    void deleteRoleById(Long id) throws ResourceNotFoundException;
    RoleDto getRoleByNom(String nom) throws ResourceNotFoundException;

}
