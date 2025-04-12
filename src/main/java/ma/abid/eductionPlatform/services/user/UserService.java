package ma.abid.eductionPlatforme.services.user;

import ma.abid.eductionPlatforme.dto.GetUserDto;
import ma.abid.eductionPlatforme.dto.PostPutUserDto;
import ma.abid.eductionPlatforme.exception.DuplicateResourceException;
import ma.abid.eductionPlatforme.exception.ResourceNotFoundException;

import java.util.List;

public interface UserService {
   List<GetUserDto> getAllUsers();
   List<GetUserDto> getSearchedUser(String key);
   GetUserDto getUserById(Long id) throws ResourceNotFoundException;
   GetUserDto createNewUser(PostPutUserDto postPutUserDtoToCreating) throws DuplicateResourceException;
   GetUserDto updateUser(Long id, PostPutUserDto postPutUserDtoToCreating) throws ResourceNotFoundException;
   void deleteUser(Long id) throws ResourceNotFoundException;
}
