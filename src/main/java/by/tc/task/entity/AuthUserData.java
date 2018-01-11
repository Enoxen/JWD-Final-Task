package by.tc.task.entity;

import java.io.Serializable;

/**
 * Created by Y50-70 on 11.01.2018.
 */
public class AuthUserData implements Serializable {
    private static final long serialVersionUID = 2369516567984376389L;
    private String login;
    private String role;
    private int userId;

    public AuthUserData(){}

    public AuthUserData(String login, String role){
        this.login = login;
        this.role = role;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AuthUserData)) return false;

        AuthUserData that = (AuthUserData) o;

        if (userId != that.userId) return false;
        if (login != null ? !login.equals(that.login) : that.login != null) return false;
        return role != null ? role.equals(that.role) : that.role == null;

    }

    @Override
    public int hashCode() {
        int result = login != null ? login.hashCode() : 0;
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + userId;
        return result;
    }

    @Override
    public String toString() {
        return "AuthUserData{" +
                "login='" + login + '\'' +
                ", role='" + role + '\'' +
                ", userId=" + userId +
                '}';
    }
}
