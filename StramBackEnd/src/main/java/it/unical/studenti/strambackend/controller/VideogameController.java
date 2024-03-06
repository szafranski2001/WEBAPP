package it.unical.studenti.strambackend.controller;

import it.unical.studenti.strambackend.persistence.DBManager;
import it.unical.studenti.strambackend.persistence.Model.Videogioco;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:4200")
public class VideogameController {

    @GetMapping("/api/Videogame/{id}")
    public ResponseEntity<Videogioco> GetVideogame(@PathVariable int id){
        try {
            Videogioco videogame = DBManager.getInstance().VideogiocoDAO().findByPrimaryKey(id);
            return new ResponseEntity<>(videogame, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/api/getVideogameImage/{id}")
    public ResponseEntity<String> GetVideogameImage(@PathVariable int id){
        return new ResponseEntity<>("http:",HttpStatus.OK);
    }

    @PostMapping("/api/AddVideogameData")
    public ResponseEntity<?> AddVideogame(@RequestBody Videogioco videogame){
        try{
            DBManager.getInstance().VideogiocoDAO().save(videogame);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/api/DeleteVideogameData")
    public ResponseEntity<?> DeleteVideogame(@RequestBody Videogioco videogioco){
        try {
            DBManager.getInstance().VideogiocoDAO().save(videogioco);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/api/EditVideogameData")
    public ResponseEntity<?> EditVideogame(@RequestBody Videogioco videogioco){
        try {
            DBManager.getInstance().VideogiocoDAO().update(videogioco);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
