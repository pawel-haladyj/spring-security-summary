package pl.haladyj.springsecuritysummary.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.haladyj.springsecuritysummary.dto.UserDto;
import pl.haladyj.springsecuritysummary.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUser(UserDto userDto);

    @Mapping(target = "name", source = "name")
    @Mapping(target = "username", source = "username")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "roles", source = "roles")
    UserDto toDto(User user);


}
