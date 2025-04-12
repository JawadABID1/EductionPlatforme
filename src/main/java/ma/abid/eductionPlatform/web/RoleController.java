package ma.abid.eductionPlatforme.web;

import ma.abid.eductionPlatforme.dto.RoleDto;
import ma.abid.eductionPlatforme.services.role.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<RoleDto>> getAllRoles(){
        return ResponseEntity.ok(service.getAllRoles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDto> getRoleById(@PathVariable Long id){
        return ResponseEntity.ok(service.getRoleById(id));
    }

    @PostMapping
    public ResponseEntity<RoleDto> createNewRole(@RequestBody RoleDto roleDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createNewRole(roleDto));
    }

    @PutMapping
    public ResponseEntity<RoleDto> upDateRole(@PathVariable Long id, @RequestBody RoleDto roleDto){
        return ResponseEntity.ok(service.updateRole(id, roleDto));
    }

    @GetMapping("/search")
    public ResponseEntity<RoleDto> getRoleByNom(@RequestParam String nom){
        return ResponseEntity.ok(service.getRoleByNom(nom));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteRoleById(@PathVariable Long id){
        service.deleteRoleById(id);
        return ResponseEntity.noContent().build();
    }
}
