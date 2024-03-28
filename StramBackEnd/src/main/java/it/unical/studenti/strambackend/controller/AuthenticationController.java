package it.unical.studenti.strambackend.controller;

import it.unical.studenti.strambackend.persistence.DBManager;
import it.unical.studenti.strambackend.persistence.Model.TokenManager;
import it.unical.studenti.strambackend.persistence.Model.User;
import it.unical.studenti.strambackend.persistence.Model.UserCredenziali;
import it.unical.studenti.strambackend.persistence.Model.UserDTO;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/authenticate")
public class AuthenticationController {
    @PostMapping("/signUp")
    public boolean completeRegistration(@RequestBody User user) {
        if (!DBManager.getInstance().userDAO().existsUser(user.getUsername()))
        {
            if (!DBManager.getInstance().userDAO().bannedEmail(user.getEmail()))
            {
                if(!DBManager.getInstance().userDAO().existsUserEmail(user.getEmail()))
                {
                    DBManager.getInstance().userDAO().save(user);
                }
            }
        }
        return true;
    }
    @PostMapping("/login")
    public ResponseEntity login (@RequestBody UserCredenziali user)
    {
        if (DBManager.getInstance().userDAO().existsUser(user.getUsername()))
        {
            if (DBManager.getInstance().userDAO().checkPassword(user.getUsername(), user.getPassword()))
            {
                UserDTO userDTO= new UserDTO();
                String authenticationToken = TokenManager.creaToken(user.getUsername(), 100*60*60*24*30*6);
                userDTO.setUser(user);
                userDTO.setToken(authenticationToken);
                return ResponseEntity.ok().body(userDTO);
            }
        }
        return ResponseEntity.badRequest().body("Username o Password errati");

    }

}