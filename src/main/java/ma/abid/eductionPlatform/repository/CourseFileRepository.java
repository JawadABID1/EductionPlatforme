package ma.abid.eductionPlatform.repository;

import ma.abid.eductionPlatform.entities.CourseFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourseFileRepository extends JpaRepository<CourseFile, Long> {

    List<CourseFile> findByCourseId(Long courseId);
    Optional<CourseFile> findByFileName(String fileName);
}
