package pl.haladyj.springsecuritysummary.mapper;

import org.mapstruct.Mapper;
import pl.haladyj.springsecuritysummary.dto.UserDto;
import pl.haladyj.springsecuritysummary.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUser(UserDto userDto);
    UserDto toDto(User user);


}
