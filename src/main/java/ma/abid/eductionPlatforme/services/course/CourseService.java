package ma.abid.eductionPlatforme.services.course;

import ma.abid.eductionPlatforme.dto.CourseDto;
import ma.abid.eductionPlatforme.exception.DuplicateResourceException;
import ma.abid.eductionPlatforme.exception.ResourceNotFoundException;

import java.util.List;

public interface CourseService {
    List<CourseDto> getAllCourses();
    List<CourseDto> getSearchedCourse(String key);
    CourseDto getCourseById(Long id) throws ResourceNotFoundException;
    CourseDto createNewCourse(CourseDto courseDto) throws DuplicateResourceException;
    CourseDto updateCourse(Long id, CourseDto courseDto) throws ResourceNotFoundException;
    void deleteCourse(Long id) throws ResourceNotFoundException;

}
