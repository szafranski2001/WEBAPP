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

public class AuthenticationController {
    @PostMapping("/authenticate/signUp")
    public boolean completeRegistration(@RequestBody User user) {
        System.out.println(user.getNome());
        if (!DBManager.getInstance().userDAO().existsUser(user.getUsername()))
        { System.out.println(user.getNome());
            if (!DBManager.getInstance().userDAO().bannedEmail(user.getEmail()))
            { System.out.println(user.getNome());
                if(!DBManager.getInstance().userDAO().existsUserEmail(user.getEmail()))
                { System.out.println(user.getNome());
                    DBManager.getInstance().userDAO().save(user);
                }
            }
        }
        return true;
    }
    @PostMapping("/authenticate/login")
    public ResponseEntity <UserDTO> login (@RequestBody UserCredenziali user)
    {
        if (DBManager.getInstance().userDAO().existsUser(user.getUsername()))
        {
            if (DBManager.getInstance().userDAO().checkPassword(user.getUsername(), user.getPassword()))
            {
                UserDTO userDTO= new UserDTO();
                String authenticationToken = TokenManager.creaToken(user.getUsername(), 100*60*60*24*30*6);
                userDTO.setUser(user);
                userDTO.setToken(authenticationToken);
                userDTO.setType(DBManager.getInstance().userDAO().getTypeUser(user.getUsername()));
                return ResponseEntity.ok().body(userDTO);

            }
        }
        return ResponseEntity.badRequest().body(null);

    }

}