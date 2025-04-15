package ma.abid.eductionPlatform.services.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.abid.eductionPlatform.entities.role.Role;
import ma.abid.eductionPlatform.entities.user.AppUser;
import ma.abid.eductionPlatform.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = userRepository.findByUsername(username)
                .orElseThrow(() -> {
                    log.error("User {} not found in database", username);
                    return new UsernameNotFoundException("AppUser Not Found " + username);
                });

        log.info("User {} loaded successfully", username);
        List<Role> roles = user.getRoles();
        List<String> authorities = roles.stream().map(r->r.getRole()).collect(Collectors.toList());
        User.UserBuilder userBuilder = User.withUsername(user.getUsername()).password(user.getPassword())
                .roles(authorities.stream().toArray(String[]::new));
        return userBuilder.build();
    }
}

