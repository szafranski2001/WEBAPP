package it.unical.studenti.strambackend.controller;
import it.unical.studenti.strambackend.persistence.DBManager;
import it.unical.studenti.strambackend.persistence.Model.User;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:4200")
public class UpdateUserController {

    @PostMapping("/api/profile/update")
    public ResponseEntity<?> updateUser(@RequestBody String userData){
        JSONObject data = new JSONObject(userData);
        String name = data.getString("nome");
        String surname = data.getString("cognome");
        String email = data.getString("email");
        String password = data.getString("password");
        String confirmPassword = data.getString("confirmPassword");
        String username = data.getString("username");

        // la conferma della password non corrisponde
        if(!password.equals(confirmPassword) || DBManager.getInstance().userDAO().bannedEmail(email)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // creo un nuovo user con le modifiche
        User newUser = DBManager.getInstance().userDAO().findByPrimaryKey(username);
        newUser.setPassword(password);

        if(!name.trim().isEmpty()) {newUser.setNome(name);}
        if(!surname.trim().isEmpty()) {newUser.setCognome(surname);}
        if(!email.trim().isEmpty()) {newUser.setEmail(email);}

        // update user
        DBManager.getInstance().userDAO().update(DBManager.getInstance().userDAO().findByPrimaryKey(username), newUser);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
