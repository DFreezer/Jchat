package jchat.dto;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

public class UserDTO {

    @Size(min=3, message = "minimal characters number is 3.")
    private String username;

    @NotEmpty(message = "is required")
    @Email
    private String email;

    @NotEmpty(message = "is required")
    private String password;

    @NotEmpty(message = "is required")
    private String confirmPassword;

    public UserDTO() {
    }

    public boolean checkPasswordConfirm() {
        return password != null && (confirmPassword != null && (password.equals(confirmPassword)));
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
