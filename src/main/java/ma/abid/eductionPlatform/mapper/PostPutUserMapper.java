package ma.abid.eductionPlatform.mapper;

import ma.abid.eductionPlatform.dto.PostPutUserDto;
import ma.abid.eductionPlatform.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostPutUserMapper {
    PostPutUserDto toDto(User user);
    User toEntity(PostPutUserDto postPutUserDtoToCreating);
}
