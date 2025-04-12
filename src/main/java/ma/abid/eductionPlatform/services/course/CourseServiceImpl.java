package ma.abid.eductionPlatforme.services.course;

import ma.abid.eductionPlatforme.dto.CourseDto;
import ma.abid.eductionPlatforme.entities.Course;
import ma.abid.eductionPlatforme.exception.DuplicateResourceException;
import ma.abid.eductionPlatforme.exception.ResourceNotFoundException;
import ma.abid.eductionPlatforme.mapper.CourseMapper;
import ma.abid.eductionPlatforme.repository.CourseRepository;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    public CourseServiceImpl(CourseRepository courseRepository, CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }

    @Override
    public List<CourseDto> getAllCourses() {
        List<Course> courseList = courseRepository.findAll();
        return courseList.stream().map(courseMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<CourseDto> getSearchedCourse(String key) {
        List<Course> searchedCourses = courseRepository.findBySubjectContainsIgnoreCase(key);
        return searchedCourses.stream().map(courseMapper::toDto).toList();
    }

    @Override
    public CourseDto getCourseById(Long id) throws ResourceNotFoundException {
        Optional<Course> course = courseRepository.findById(id);
        if (course.isEmpty()) throw new ResourceNotFoundException("Course not found");
        return courseMapper.toDto(course.get());
    }

    @Override
    public CourseDto createNewCourse(CourseDto courseDto) throws DuplicateResourceException {
        Optional<Course> byTitle = courseRepository.findByTitle(courseDto.getTitle());
        if (byTitle.isPresent()) throw  new DuplicateResourceException("Course not found");
        Course course = courseMapper.toEntity(courseDto);
        return courseMapper.toDto(courseRepository.save(course));
    }

    @Override
    public CourseDto updateCourse(Long id, CourseDto courseDto) throws ResourceNotFoundException {
        Course courseToUpdate = courseRepository.findById(id).orElseThrow(()-> new  ResourceNotFoundException("Course not found"));
        courseToUpdate.setTitle(courseDto.getTitle());
        courseToUpdate.setLevel(courseDto.getLevel());
        courseToUpdate.setSubject(courseToUpdate.getSubject());
        courseToUpdate.setContent(courseToUpdate.getContent());
        courseToUpdate.setCreatedBy(courseDto.getCreatedBy());
        return courseMapper.toDto(courseRepository.save(courseToUpdate));
    }

    @Override
    public void deleteCourse(Long id) throws ResourceNotFoundException {
        Optional<Course> courseToDelete = courseRepository.findById(id);
        if (courseToDelete.isEmpty()) throw new ResourceNotFoundException("Course not found");
        courseRepository.deleteById(id);

    }
}
