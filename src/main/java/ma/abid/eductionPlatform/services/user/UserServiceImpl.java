package ma.abid.eductionPlatform.services.user;

import ma.abid.eductionPlatform.dto.GetUserDto;
import ma.abid.eductionPlatform.dto.PostPutUserDto;
import ma.abid.eductionPlatform.entities.User;
import ma.abid.eductionPlatform.exception.DuplicateResourceException;
import ma.abid.eductionPlatform.exception.ResourceNotFoundException;
import ma.abid.eductionPlatform.mapper.GetUserMapper;
import ma.abid.eductionPlatform.mapper.PostPutUserMapper;
import ma.abid.eductionPlatform.repository.UserRepository;

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
