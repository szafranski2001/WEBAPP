package it.unical.studenti.strambackend.persistence.Model;

public class UserDTO {
    String token;
    UserCredenziali user;
    int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

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
