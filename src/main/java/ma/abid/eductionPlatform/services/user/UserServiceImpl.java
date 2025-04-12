package ma.abid.eductionPlatforme.services.user;

import ma.abid.eductionPlatforme.dto.GetUserDto;
import ma.abid.eductionPlatforme.dto.PostPutUserDto;
import ma.abid.eductionPlatforme.entities.User;
import ma.abid.eductionPlatforme.exception.DuplicateResourceException;
import ma.abid.eductionPlatforme.exception.ResourceNotFoundException;
import ma.abid.eductionPlatforme.mapper.GetUserMapper;
import ma.abid.eductionPlatforme.mapper.PostPutUserMapper;
import ma.abid.eductionPlatforme.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PostPutUserMapper postPutUserMapper;
    private final GetUserMapper getUserMapper;

    public UserServiceImpl(UserRepository userRepository, PostPutUserMapper postPutUserMapper, GetUserMapper getUserMapper) {
        this.userRepository = userRepository;
        this.postPutUserMapper = postPutUserMapper;
        this.getUserMapper = getUserMapper;
    }

    @Override
    public List<GetUserDto> getAllUsers() {
        List<User> userList = userRepository.findAll();
        return userList.stream().map(getUserMapper::toDto).toList();
    }

    @Override
    public List<GetUserDto> getSearchedUser(String key) {
        List<User> userList = userRepository.findByUsernameContainsIgnoreCase(key);
        return userList.stream().map(getUserMapper::toDto).toList();
    }

    @Override
    public GetUserDto getUserById(Long id) throws ResourceNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("User not found"));
        return getUserMapper.toDto(user);
    }

    @Override
    public GetUserDto createNewUser(PostPutUserDto postPutUserDtoToCreating) throws DuplicateResourceException {
//        User user = userRepository.findByEmail(postPutUserDtoToCreating.getEmail())
//                .orElseThrow(()-> new DuplicateResourceException("A user already exists with same email: "
//                        + postPutUserDtoToCreating.getEmail()));
        Optional<User> userByEmail = userRepository.findByEmail(postPutUserDtoToCreating.getEmail());
        if (userByEmail.isPresent()) throw new DuplicateResourceException("A user already exists with same email: " + postPutUserDtoToCreating.getEmail());

        return getUserMapper.toDto(userRepository.save(postPutUserMapper.toEntity(postPutUserDtoToCreating)));
    }

    @Override
    public GetUserDto updateUser(Long id, PostPutUserDto postPutUserDtoToCreating) throws ResourceNotFoundException {
        User user = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not found"));
        user.setFirstName(postPutUserDtoToCreating.getFirstName());
        user.setLastName(postPutUserDtoToCreating.getLastName());
        user.setUsername(postPutUserDtoToCreating.getUsername());
        user.setEmail(postPutUserDtoToCreating.getEmail());
        user.setRole(postPutUserDtoToCreating.getRole());
        return getUserMapper.toDto(userRepository.save(user));
    }

    @Override
    public void deleteUser(Long id) throws ResourceNotFoundException {

    }
}
