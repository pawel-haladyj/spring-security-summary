package pl.haladyj.springsecuritysummary.security.providers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import pl.haladyj.springsecuritysummary.repository.OneTimePasswordRepository;
import pl.haladyj.springsecuritysummary.security.authentication.OtpAuthentication;

import java.util.List;

@Component
public class OtpAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private OneTimePasswordRepository oneTimePasswordRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        var username = authentication.getName();
        var otp = authentication.getCredentials().toString();

        var optionalOtp = oneTimePasswordRepository.findByUsername(username);

        if(optionalOtp.isPresent()){
            return new OtpAuthentication(username,otp, List.of(()->"read"));
        }
        throw new BadCredentialsException("Bad Credentials");
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return OtpAuthentication.class.equals(aClass);
    }
}
