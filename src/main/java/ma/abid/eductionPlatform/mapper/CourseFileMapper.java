package ma.abid.eductionPlatforme.mapper;

import ma.abid.eductionPlatforme.dto.CourseDto;
import ma.abid.eductionPlatforme.dto.CourseFileDto;
import ma.abid.eductionPlatforme.entities.CourseFile;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseFileMapper {
    CourseFileDto toDto(CourseFile courseFile);
//    CourseFile toEntity(CourseDto courseDto);

}
