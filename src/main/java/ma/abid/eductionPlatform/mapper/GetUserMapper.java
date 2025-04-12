package ma.abid.eductionPlatform.mapper;

import ma.abid.eductionPlatform.dto.GetUserDto;
import ma.abid.eductionPlatform.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GetUserMapper{
//    @Mapping(target = "fullName", expression = "java(user.getFirstName() + \" \" + user.getLastName())")
    GetUserDto toDto(User user);
}