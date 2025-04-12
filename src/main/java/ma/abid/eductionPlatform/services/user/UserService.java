package ma.abid.eductionPlatform.services.user;

import ma.abid.eductionPlatform.dto.GetUserDto;
import ma.abid.eductionPlatform.dto.PostPutUserDto;
import ma.abid.eductionPlatform.exception.DuplicateResourceException;
import ma.abid.eductionPlatform.exception.ResourceNotFoundException;

import java.util.List;

public interface UserService {
   List<GetUserDto> getAllUsers();
   List<GetUserDto> getSearchedUser(String key);
   GetUserDto getUserById(Long id) throws ResourceNotFoundException;
   GetUserDto createNewUser(PostPutUserDto postPutUserDtoToCreating) throws DuplicateResourceException;
   GetUserDto updateUser(Long id, PostPutUserDto postPutUserDtoToCreating) throws ResourceNotFoundException;
   void deleteUser(Long id) throws ResourceNotFoundException;
}
