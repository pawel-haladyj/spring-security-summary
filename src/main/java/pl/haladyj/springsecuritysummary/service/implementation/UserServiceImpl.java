package pl.haladyj.springsecuritysummary.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.haladyj.springsecuritysummary.dto.UserDto;
import pl.haladyj.springsecuritysummary.entity.User;
import pl.haladyj.springsecuritysummary.mapper.UserMapper;
import pl.haladyj.springsecuritysummary.repository.UserRepository;
import pl.haladyj.springsecuritysummary.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.nonNull;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public Optional<UserDto> findUserById(Long id) {
        checkArgument(nonNull(id), "Expected none-null id");
        Optional<User> user = userRepository.findById(id);

        if(user.isPresent()){
            UserDto userDto = userMapper.toDto(user.get());
            return Optional.of(userDto);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<UserDto> userDtos = new ArrayList();
        userRepository
                .findAll()
                .forEach(user -> userDtos.add(userMapper.toDto(user)));
        return userDtos;

    }
}
