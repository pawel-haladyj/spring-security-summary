package pl.haladyj.springsecuritysummary.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.haladyj.springsecuritysummary.entity.User;
import pl.haladyj.springsecuritysummary.repository.UserRepository;
import pl.haladyj.springsecuritysummary.security.model.SecurityUser;

@Service
public class JpaUserSecurityService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var optionalUser = userRepository.findByUsername(username);
        User user = optionalUser.orElseThrow(()->new UsernameNotFoundException("Username does not exist in database"));

        return new SecurityUser(user);
    }
}
