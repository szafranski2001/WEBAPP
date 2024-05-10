package it.unical.studenti.strambackend.controller;
import it.unical.studenti.strambackend.persistence.DBManager;
import it.unical.studenti.strambackend.persistence.DBSource;
import it.unical.studenti.strambackend.persistence.Model.User;
import it.unical.studenti.strambackend.persistence.dao.UserDAO;
import it.unical.studenti.strambackend.persistence.dao.jdbc.UserDAOJDBC;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
public class UpdateUserController {

    @PostMapping("/api/profile/update")
    public void updateUser(@RequestBody String userData){
        JSONObject data = new JSONObject(userData);
        String name = data.getString("nome");
        String surname = data.getString("cognome");
        String email = data.getString("email");
        String password = data.getString("password");
        String confirmPassword = data.getString("confirmPassword");
        String username = data.getString("username");

        // la conferma della password non corrisponde
        if(!password.equals(confirmPassword)){
            return;
        }

        // creo un nuovo user con le modifiche
        User newUser = DBManager.getInstance().userDAO().findByPrimaryKey(username);
        if(!name.isEmpty()) {newUser.setNome(name);}
        if(!surname.isEmpty()) {newUser.setCognome(surname);}
        if(!email.isEmpty()) {newUser.setEmail(email);}
        if(!password.isEmpty()) {newUser.setPassword(password);}

        // update user
        DBManager.getInstance().userDAO().update(DBManager.getInstance().userDAO().findByPrimaryKey(username), newUser);
    }

}
