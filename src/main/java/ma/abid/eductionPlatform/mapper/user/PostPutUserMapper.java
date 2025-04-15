package ma.abid.eductionPlatform.mapper.user;

import ma.abid.eductionPlatform.dto.user.PostPutUserDto;
import ma.abid.eductionPlatform.entities.user.AppUser;
import ma.abid.eductionPlatform.mapper.role.RoleMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = RoleMapper.class)
public interface PostPutUserMapper {
//    PostPutUserDto toDto(AppUser appUser);
    AppUser toEntity(PostPutUserDto postPutUserDtoToCreating);
}
