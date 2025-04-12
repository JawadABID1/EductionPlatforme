package ma.abid.eductionPlatform.mapper;

import ma.abid.eductionPlatform.dto.CourseDto;
import ma.abid.eductionPlatform.entities.Course;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    CourseDto toDto(Course course);
    Course toEntity(CourseDto courseDto);
}
