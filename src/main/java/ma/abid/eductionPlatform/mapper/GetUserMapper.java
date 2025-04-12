package ma.abid.eductionPlatforme.mapper;

import ma.abid.eductionPlatforme.dto.GetUserDto;
import ma.abid.eductionPlatforme.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GetUserMapper{
//    @Mapping(target = "fullName", expression = "java(user.getFirstName() + \" \" + user.getLastName())")
    GetUserDto toDto(User user);
}