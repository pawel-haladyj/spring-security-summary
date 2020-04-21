package pl.haladyj.springsecuritysummary.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.haladyj.springsecuritysummary.entity.User;
import pl.haladyj.springsecuritysummary.repository.UserRepository;
import pl.haladyj.springsecuritysummary.security.model.CustomSecurityUser;

import java.util.Optional;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var optionalUser = repository.findUserByUsername(username);
        User user = optionalUser.orElseThrow(()->new UsernameNotFoundException("User not found"));
        return new CustomSecurityUser(user);
    }
}
