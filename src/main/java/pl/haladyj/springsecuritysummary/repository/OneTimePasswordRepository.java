package pl.haladyj.springsecuritysummary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.haladyj.springsecuritysummary.entity.OneTimePassword;

import java.util.Optional;

public interface OneTimePasswordRepository extends JpaRepository<OneTimePassword, Long> {
    Optional<OneTimePassword> findByOtp (String otp);
    Optional<OneTimePassword> findByUsername (String username);
}
