package pl.haladyj.springsecuritysummary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.haladyj.springsecuritysummary.dto.UserDto;
import pl.haladyj.springsecuritysummary.service.implementation.UserServiceImpl;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long id){
        Optional<UserDto> userDtoOptional = userService.findUserById(id);
        return userDtoOptional
                .map(result -> (ResponseEntity.ok().body(result)))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> users = userService.findAllUsers();
        return ResponseEntity.ok().body(users);
    }
}
