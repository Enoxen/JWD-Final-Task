package by.tc.task.entity;

import java.util.Objects;

/**
 * Created by Y50-70 on 05.02.2018.
 */
public class User {
    private int userId;
    private String email;
    private String login;
    private String role;
    private boolean isBanned;

    public User(){}

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isBanned() {
        return isBanned;
    }

    public void setBanned(boolean banned) {
        isBanned = banned;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId &&
                email == user.email &&
                isBanned == user.isBanned &&
                Objects.equals(login, user.login) &&
                Objects.equals(role, user.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, email, login, role, isBanned);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", email=" + email +
                ", login='" + login + '\'' +
                ", role='" + role + '\'' +
                ", isBanned=" + isBanned +
                '}';
    }




}
