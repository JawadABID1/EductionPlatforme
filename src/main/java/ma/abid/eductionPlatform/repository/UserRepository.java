package ma.abid.eductionPlatforme.repository;

import ma.abid.eductionPlatforme.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    List<User> findByUsernameContainsIgnoreCase(String key);
}
