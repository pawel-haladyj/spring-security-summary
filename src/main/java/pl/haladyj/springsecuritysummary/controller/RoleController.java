package pl.haladyj.springsecuritysummary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import pl.haladyj.springsecuritysummary.repository.RoleRepository;

@RestController
public class RoleController {

    @Autowired
    private RoleRepository roleRepository;


}
