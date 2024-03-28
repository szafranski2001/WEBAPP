package it.unical.studenti.strambackend.persistence.Model;

public class UserDTO {
    String token;
    UserCredenziali user;

    public void setToken(String token) {
        this.token = token;
    }

    public void setUser(UserCredenziali user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public UserCredenziali getUser() {
        return user;
    }
}
