package by.tc.task.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by Y50-70 on 11.01.2018.
 */
public class AuthUserData implements Serializable {
    private static final long serialVersionUID = 2369516567984376389L;
    private String login;
    private String newLogin;
    private String oldPassword;
    private String newPassword;
    private String passwordConfirm;
    private String email;
    private String newEmail;
    private String role;
    private int userId;


    public AuthUserData(){}

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNewLogin() {
        return newLogin;
    }

    public void setNewLogin(String newLogin) {
        this.newLogin = newLogin;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNewEmail() {
        return newEmail;
    }

    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthUserData that = (AuthUserData) o;
        return userId == that.userId &&
                Objects.equals(login, that.login) &&
                Objects.equals(newLogin, that.newLogin) &&
                Objects.equals(oldPassword, that.oldPassword) &&
                Objects.equals(newPassword, that.newPassword) &&
                Objects.equals(passwordConfirm, that.passwordConfirm) &&
                Objects.equals(email, that.email) &&
                Objects.equals(newEmail, that.newEmail) &&
                Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, newLogin, oldPassword, newPassword, passwordConfirm, email, newEmail, role, userId);
    }

    @Override
    public String toString() {
        return "AuthUserData{" +
                "login='" + login + '\'' +
                ", newLogin='" + newLogin + '\'' +
                ", oldPassword='" + oldPassword + '\'' +
                ", newPassword='" + newPassword + '\'' +
                ", passwordConfirm='" + passwordConfirm + '\'' +
                ", email='" + email + '\'' +
                ", newEmail='" + newEmail + '\'' +
                ", role='" + role + '\'' +
                ", userId=" + userId +
                '}';
    }
}
