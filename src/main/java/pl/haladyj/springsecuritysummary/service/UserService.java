package pl.haladyj.springsecuritysummary.service;

import pl.haladyj.springsecuritysummary.dto.UserDto;
import pl.haladyj.springsecuritysummary.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<UserDto> findUserById(Long id);
    List<UserDto> findAllUsers();
}
