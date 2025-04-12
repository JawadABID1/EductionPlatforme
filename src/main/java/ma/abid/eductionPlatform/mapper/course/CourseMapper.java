package ma.abid.eductionPlatform.mapper.course;

import ma.abid.eductionPlatform.dto.course.CourseDto;
import ma.abid.eductionPlatform.entities.course.Course;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    CourseDto toDto(Course course);
    Course toEntity(CourseDto courseDto);
}
