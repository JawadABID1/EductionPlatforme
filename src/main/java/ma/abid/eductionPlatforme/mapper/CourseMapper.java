package ma.abid.eductionPlatforme.mapper;

import ma.abid.eductionPlatforme.dto.CourseDto;
import ma.abid.eductionPlatforme.entities.Course;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    CourseDto toDto(Course course);
    Course toEntity(CourseDto courseDto);
}
