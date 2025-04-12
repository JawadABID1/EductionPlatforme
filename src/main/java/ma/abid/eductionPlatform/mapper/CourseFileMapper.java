package ma.abid.eductionPlatform.mapper;

import ma.abid.eductionPlatform.dto.CourseFileDto;
import ma.abid.eductionPlatform.entities.CourseFile;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseFileMapper {
    CourseFileDto toDto(CourseFile courseFile);
//    CourseFile toEntity(CourseDto courseDto);

}
