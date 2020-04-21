package pl.haladyj.springsecuritysummary.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "one_time_password")
public class OneTimePassword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String otp;
    private String username;
    private Date createdat;

    public OneTimePassword() {
    }

    public OneTimePassword(
            Long id,
            String otp,
            String username,
            Date createdat) {
        this.id = id;
        this.otp = otp;
        this.username = username;
        this.createdat = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getCreatedAt() {
        return createdat;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdat = createdat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OneTimePassword that = (OneTimePassword) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(otp, that.otp) &&
                Objects.equals(username, that.username) &&
                Objects.equals(createdat, that.createdat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, otp, username, createdat);
    }
}
