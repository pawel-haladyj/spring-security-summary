package pl.haladyj.springsecuritysummary.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_table")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 3, max = 40)
    private String name;

    @NotBlank
    @Size(min = 3, max= 40)
    private String username;

    @NotBlank
    @Size(min=6,max=60)
    private String password;

    @NotBlank
    @Size(min=5,max=40)
    @Email
    private String email;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "USER_ROLES",
            joinColumns = {@JoinColumn(name = "USER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "ROLE_ID")}
    )
    private Set<Role> roles;

}
