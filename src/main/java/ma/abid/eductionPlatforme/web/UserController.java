package ma.abid.eductionPlatforme.web;

import ma.abid.eductionPlatforme.dto.GetUserDto;
import ma.abid.eductionPlatforme.dto.PostPutUserDto;
import ma.abid.eductionPlatforme.entities.User;
import ma.abid.eductionPlatforme.services.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @RequestMapping("/api/v1/users")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }
    @GetMapping
    public ResponseEntity<List<GetUserDto>> getAllUsers(){
        List<GetUserDto> allUsers = service.getAllUsers();
        return ResponseEntity.ok(allUsers);
    }

    @GetMapping("/search")
    public ResponseEntity<List<GetUserDto>> getSearchedUser(@RequestParam String key){
        List<GetUserDto> searchedUser = service.getSearchedUser(key);
        return ResponseEntity.ok(searchedUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetUserDto> getUserById(Long id){
        return ResponseEntity.ok(service.getUserById(id));
    }

    @PostMapping
    public ResponseEntity<GetUserDto> createNewUser(@RequestBody PostPutUserDto postUserDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createNewUser(postUserDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GetUserDto> updateUser(@PathVariable Long id, @RequestBody PostPutUserDto putUserDto){
        return ResponseEntity.ok(service.updateUser(id, putUserDto));
    }

    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        service.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
