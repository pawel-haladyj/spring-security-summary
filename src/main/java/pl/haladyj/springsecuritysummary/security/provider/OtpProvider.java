package pl.haladyj.springsecuritysummary.security.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.haladyj.springsecuritysummary.repository.OneTimePasswordRepository;
import pl.haladyj.springsecuritysummary.security.authentication.OtpAuthentication;

import java.util.List;

@Component
public class OtpProvider implements AuthenticationProvider {

    @Autowired
    private OneTimePasswordRepository oneTimePasswordRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
       var username = authentication.getName();
       var otp = authentication.getCredentials().toString();

       var oprionalOtp = oneTimePasswordRepository.findByUsername(username);

       if(oprionalOtp.isPresent()){
           return new OtpAuthentication(username,otp, List.of(()->"read"));
       }
       throw new BadCredentialsException("Bad credentials");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return OtpAuthentication.class.equals(authentication);
    }
}
