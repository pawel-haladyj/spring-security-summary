package pl.haladyj.springsecuritysummary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.haladyj.springsecuritysummary.entity.Role;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

    public Optional<Role> findById(Long id);
    public List<Role> findAll();
}
