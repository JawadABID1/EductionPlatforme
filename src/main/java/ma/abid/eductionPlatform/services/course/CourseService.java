package ma.abid.eductionPlatform.services.course;

import ma.abid.eductionPlatform.dto.CourseDto;
import ma.abid.eductionPlatform.exception.DuplicateResourceException;
import ma.abid.eductionPlatform.exception.ResourceNotFoundException;

import java.util.List;

public interface CourseService {
    List<CourseDto> getAllCourses();
    List<CourseDto> getSearchedCourse(String key);
    CourseDto getCourseById(Long id) throws ResourceNotFoundException;
    CourseDto createNewCourse(CourseDto courseDto) throws DuplicateResourceException;
    CourseDto updateCourse(Long id, CourseDto courseDto) throws ResourceNotFoundException;
    void deleteCourse(Long id) throws ResourceNotFoundException;

}
