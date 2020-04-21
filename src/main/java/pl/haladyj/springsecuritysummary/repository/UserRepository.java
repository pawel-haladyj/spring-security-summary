package pl.haladyj.springsecuritysummary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.haladyj.springsecuritysummary.entity.User;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    public Optional<User> findById(Long id);
    public Optional<User> findByUsername(String username);
    public List<User> findAll();


}
