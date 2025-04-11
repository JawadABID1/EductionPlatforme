package ma.abid.eductionPlatforme.mapper;

import ma.abid.eductionPlatforme.dto.PostPutUserDto;
import ma.abid.eductionPlatforme.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostPutUserMapper {
    PostPutUserDto toDto(User user);
    User toEntity(PostPutUserDto postPutUserDtoToCreating);
}
