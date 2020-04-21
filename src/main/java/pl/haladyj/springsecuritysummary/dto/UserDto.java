package pl.haladyj.springsecuritysummary.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.haladyj.springsecuritysummary.entity.Role;

import java.util.Set;

@Getter
@Setter
@ToString
public class UserDto {
    private String name;
    private String username;
    private String email;
    private Set<Role> roles;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
