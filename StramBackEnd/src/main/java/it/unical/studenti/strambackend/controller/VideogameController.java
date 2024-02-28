package it.unical.studenti.strambackend.controller;

import it.unical.studenti.strambackend.persistence.DBManager;
import it.unical.studenti.strambackend.persistence.Model.Videogioco;
import org.springframework.web.bind.annotation.*;

@RestController
public class VideogameController {

    @GetMapping("/api/videogameData")
    public Videogioco GetVideogame(@RequestBody int id){
        return DBManager.getInstance().VideogiocoDAO().findByPrimaryKey(id);
    }

    @PostMapping("/api/AddVideogameData")
    public String AddVideogame(@RequestBody Videogioco videogame){
        DBManager.getInstance().VideogiocoDAO().save(videogame);
        return "OK";
    }

    @DeleteMapping("/api/DeleteVideogameData")
    public String DeleteVideogame(@RequestBody Videogioco videogioco){
        DBManager.getInstance().VideogiocoDAO().save(videogioco);
        return "Videogame Deleted";
    }

    @PostMapping("/api/EditVideogameData")
    public String EditVideogame(@RequestBody Videogioco videogioco){
        DBManager.getInstance().VideogiocoDAO().update(videogioco);
        return "Videogame Edited";
    }
}
