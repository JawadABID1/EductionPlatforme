package ma.abid.eductionPlatform.mapper.user;

import ma.abid.eductionPlatform.dto.user.GetUserDto;
import ma.abid.eductionPlatform.entities.user.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GetUserMapper{
//    @Mapping(target = "fullName", expression = "java(user.getFirstName() + \" \" + user.getLastName())")
    GetUserDto toDto(User user);
}