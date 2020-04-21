package pl.haladyj.springsecuritysummary.security.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import pl.haladyj.springsecuritysummary.entity.OneTimePassword;
import pl.haladyj.springsecuritysummary.repository.OneTimePasswordRepository;
import pl.haladyj.springsecuritysummary.security.authentication.OtpAuthentication;
import pl.haladyj.springsecuritysummary.security.authentication.UsernamePasswordAuthentication;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

@Component
public class UsernamePasswordAuthFilter extends OncePerRequestFilter {

    @Value("${otp.validity.minutes}")
    private Long otpValidityInMinutes;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private OneTimePasswordRepository oneTimePasswordRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        var username = request.getHeader("username");
        var password = request.getHeader("password");
        var otp = request.getHeader("otp").toString();

        if(otp == null){
            var authentication = new UsernamePasswordAuthentication(username,password);
            authenticationManager.authenticate(authentication);

            String code = String.valueOf(new Random().nextInt(8999) + 1000);

            OneTimePassword otpEntity = new OneTimePassword();
            otpEntity.setUsername(username);
            otpEntity.setOtp(code);

            oneTimePasswordRepository.save(otpEntity);


        }else{
            var authentication = new OtpAuthentication(username,otp);
            authenticationManager.authenticate(authentication);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            response.setHeader("Authorization", UUID.randomUUID().toString());
        }


    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !request.getServletPath().equals("/login");
    }

/*    private boolean isOtpValid(String otp, String username){
        if(otp!=null){
            var otpOptional = oneTimePasswordRepository.findByOtp(otp);
            boolean isOtpPresent = otpOptional.isPresent();
            boolean isValid = false;
            boolean belongsToUsername = false;

            if(isOtpPresent){
                var otpEntity = otpOptional.get();
                Date expiry = new Date(otpEntity.getCreatedAt().getTime() + otpValidityInMinutes*60*1000);

                isValid = expiry.after(new Date());

                belongsToUsername = otpEntity.getUsername().equals(username);
            }
            return isValid && belongsToUsername;
        }
        return false;
    }*/


}
