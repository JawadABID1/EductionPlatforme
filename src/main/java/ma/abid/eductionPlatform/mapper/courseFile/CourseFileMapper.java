package ma.abid.eductionPlatform.mapper.courseFile;

import ma.abid.eductionPlatform.dto.courseFile.CourseFileDto;
import ma.abid.eductionPlatform.entities.courseFile.CourseFile;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseFileMapper {
    CourseFileDto toDto(CourseFile courseFile);
//    CourseFile toEntity(CourseDto courseDto);

}
