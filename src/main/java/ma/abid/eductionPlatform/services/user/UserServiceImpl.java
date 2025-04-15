package ma.abid.eductionPlatform.services.user;

import ma.abid.eductionPlatform.dto.user.GetUserDto;
import ma.abid.eductionPlatform.dto.user.PostPutUserDto;
import ma.abid.eductionPlatform.entities.user.AppUser;
import ma.abid.eductionPlatform.exception.DuplicateResourceException;
import ma.abid.eductionPlatform.exception.ResourceNotFoundException;
import ma.abid.eductionPlatform.mapper.user.GetUserMapper;
import ma.abid.eductionPlatform.mapper.user.PostPutUserMapper;
import ma.abid.eductionPlatform.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PostPutUserMapper postPutUserMapper;
    private final GetUserMapper getUserMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PostPutUserMapper postPutUserMapper, GetUserMapper getUserMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.postPutUserMapper = postPutUserMapper;
        this.getUserMapper = getUserMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<GetUserDto> getAllUsers() {
        List<AppUser> appUserList = userRepository.findAll();
        return appUserList.stream().map(getUserMapper::toDto).toList();
    }

    @Override
    public GetUserDto getUserById(Long id) throws ResourceNotFoundException {
        AppUser appUser = userRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("AppUser not found"));
        return getUserMapper.toDto(appUser);
    }

    @Override
    public GetUserDto getUserByUserName(String username) {
        Optional<AppUser> userByUsername = userRepository.findByUsername(username);
        if (userByUsername.isEmpty()) throw new ResourceNotFoundException("AppUser not found");
        return getUserMapper.toDto(userByUsername.get());
    }

    @Override
    public GetUserDto createNewUser(PostPutUserDto postPutUserDtoToCreating) throws DuplicateResourceException {
        Optional<AppUser> userByEmail = userRepository.findByEmail(postPutUserDtoToCreating.getEmail());
        if (userByEmail.isPresent()) throw new DuplicateResourceException("A user already exists with same email: " + postPutUserDtoToCreating.getEmail());
        AppUser userEntity = postPutUserMapper.toEntity(postPutUserDtoToCreating);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        return getUserMapper.toDto(userRepository.save(userEntity));
    }

    @Override
    public GetUserDto updateUser(Long id, PostPutUserDto postPutUserDtoToCreating) throws ResourceNotFoundException {
        AppUser appUser = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("AppUser not found"));
        appUser.setFirstName(postPutUserDtoToCreating.getFirstName());
        appUser.setLastName(postPutUserDtoToCreating.getLastName());
        appUser.setUsername(postPutUserDtoToCreating.getUsername());
        appUser.setEmail(postPutUserDtoToCreating.getEmail());
        return getUserMapper.toDto(userRepository.save(appUser));
    }

    @Override
    public void deleteUser(Long id) throws ResourceNotFoundException {

    }
}
