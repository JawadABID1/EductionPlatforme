package ma.abid.eductionPlatform.web;

//import ma.abid.eductionPlatform.dto.RoleDto;
import ma.abid.eductionPlatform.dto.role.RoleDto;
import ma.abid.eductionPlatform.services.role.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {
    private final RoleService service;

    public RoleController(RoleService service) {
        this.service = service;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_PROF')")
    public ResponseEntity<List<RoleDto>> getAllRoles(){
        return ResponseEntity.ok(service.getAllRoles());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_PROF')")
    public ResponseEntity<RoleDto> createNewRole(@RequestBody RoleDto roleDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createNewRole(roleDto));
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ROLE_PROF')")
    public ResponseEntity<RoleDto> upDateRole(@PathVariable Long id, @RequestBody RoleDto roleDto){
        return ResponseEntity.ok(service.updateRole(id, roleDto));
    }

    @GetMapping("/search")
    @PreAuthorize("hasAuthority('ROLE_PROF')")
    public ResponseEntity<RoleDto> getRoleByNom(@RequestParam String nom){
        return ResponseEntity.ok(service.getRoleByNom(nom));
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('ROLE_PROF')")
    public ResponseEntity<Void> deleteRoleById(@PathVariable Long id){
        service.deleteRoleByNom(id);
        return ResponseEntity.noContent().build();
    }
}
