package ma.abid.eductionPlatform.mapper.user;

import ma.abid.eductionPlatform.dto.user.GetUserDto;
import ma.abid.eductionPlatform.entities.user.AppUser;
import ma.abid.eductionPlatform.mapper.role.RoleMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = RoleMapper.class)
public interface GetUserMapper{
//    @Mapping(target = "fullName", expression = "java(appUser.getFirstName() + \" \" + appUser.getLastName())")
    GetUserDto toDto(AppUser appUser);
}