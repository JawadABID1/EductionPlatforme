package ma.abid.eductionPlatform.services.role;

//import ma.abid.eductionPlatform.dto.RoleDto;
import ma.abid.eductionPlatform.dto.role.RoleDto;
import ma.abid.eductionPlatform.entities.role.Role;
import ma.abid.eductionPlatform.exception.DuplicateResourceException;
import ma.abid.eductionPlatform.exception.ResourceNotFoundException;
import ma.abid.eductionPlatform.mapper.role.RoleMapper;
import ma.abid.eductionPlatform.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public RoleServiceImpl(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    @Override
    public List<RoleDto> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        return roles.stream().map(roleMapper::toDto).toList();
    }

    @Override
    public RoleDto createNewRole(RoleDto roleDto) throws DuplicateResourceException {
          Optional<Role> role = roleRepository.findByRole(roleDto.getRole());
          if (role.isPresent()) throw new DuplicateResourceException("A role already exists with same nom");
          Role roleToCreate = roleMapper.toEntity(roleDto);
          return roleMapper.toDto(roleRepository.save(roleToCreate));
    }

    @Override
    public RoleDto updateRole(Long id, RoleDto roleDto) throws ResourceNotFoundException {
        Role roleToUpdate = roleRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Role not found"));
        roleToUpdate.setRole(roleDto.getRole());
        return roleMapper.toDto(roleRepository.save(roleToUpdate));
    }

    @Override
    public void deleteRoleByNom(Long id) throws ResourceNotFoundException {
        Optional<Role> roleToDelete = roleRepository.findById(id);
        if (roleToDelete.isEmpty()) throw new  ResourceNotFoundException("Role not found");
        roleRepository.deleteById(id);
    }

    @Override
    public RoleDto getRoleByNom(String nom) throws ResourceNotFoundException {
        Role byNom = roleRepository.findByRole(nom).orElseThrow(()-> new ResourceNotFoundException("Role not found"));
        return roleMapper.toDto(byNom);
    }
}
