package ma.abid.eductionPlatforme.repository;

import ma.abid.eductionPlatforme.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Optional<Course> findByTitle(String title);
    List<Course> findBySubjectContainsIgnoreCase(String key);
}