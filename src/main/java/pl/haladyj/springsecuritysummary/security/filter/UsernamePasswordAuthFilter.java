package pl.haladyj.springsecuritysummary.security.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
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
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Component
public class UsernamePasswordAuthFilter extends OncePerRequestFilter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private OneTimePasswordRepository oneTimePasswordRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String username = request.getHeader("username");
        String password = request.getHeader("password");
        String otp = request.getHeader("otp");

        if(otp==null){
            var authentication = new UsernamePasswordAuthentication(username,password, List.of(()->"read"));
            authenticationManager.authenticate(authentication);

            var otpCode = String.valueOf(new Random().nextInt(8999)+1000);
            var createdAt = new Date();

            OneTimePassword oneTimePassword = new OneTimePassword();
            oneTimePassword.setOtp(otpCode);
            oneTimePassword.setUsername(username);
            oneTimePassword.setCreatedAt(createdAt);

            oneTimePasswordRepository.save(oneTimePassword);

        } else {
            var authentication = new OtpAuthentication(username,otp, List.of(()->"read"));
            authenticationManager.authenticate(authentication);
            response.setHeader("Authorization", UUID.randomUUID().toString());
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !request.getServletPath().equals("/login");
    }
}
