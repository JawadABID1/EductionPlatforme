package ma.abid.eductionPlatform.repository;

import ma.abid.eductionPlatform.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Optional<Course> findByTitle(String title);
    List<Course> findBySubjectContainsIgnoreCase(String key);
}