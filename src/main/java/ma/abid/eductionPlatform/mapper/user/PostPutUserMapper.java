package ma.abid.eductionPlatform.mapper.user;

import ma.abid.eductionPlatform.dto.user.PostPutUserDto;
import ma.abid.eductionPlatform.entities.user.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostPutUserMapper {
    PostPutUserDto toDto(User user);
    User toEntity(PostPutUserDto postPutUserDtoToCreating);
}
