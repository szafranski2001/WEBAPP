package it.unical.studenti.strambackend.controller;

import it.unical.studenti.strambackend.persistence.DBManager;
import it.unical.studenti.strambackend.persistence.ErrorMessage.VideogameMessageDB;
import it.unical.studenti.strambackend.persistence.Model.Videogioco;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;

@RestController
@CrossOrigin("http://localhost:4200")
public class VideogameController {

    @GetMapping("/api/Videogame/{id}")
    public ResponseEntity<Videogioco> GetVideogame(@PathVariable int id){
        try {
            Videogioco videogame = DBManager.getInstance().VideogiocoDAO().findByPrimaryKey(id);
            return videogame!=null ?  new ResponseEntity<>(videogame, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/api/DeleteVideogame/{videogameId}")
    public ResponseEntity<?> DeleteVideogame(@PathVariable int videogameId){
        try {
            DBManager.getInstance().VideogiocoDAO().delete(videogameId);

            String folderPath = "src/main/resources/static/images/videogames";
            String fileName=videogameId+".png";
            String filaNameCopertina=videogameId+"-Copertina.png";
            String filePath = folderPath + File.separator;
            File imageFile = new File(filePath + fileName);
            File imageFileCopertina = new File(filePath + filaNameCopertina);
            imageFile.delete();
            imageFileCopertina.delete();

            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(VideogameMessageDB.ERROR_REMOVE_VIDEOGAME_MESSAGE,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/api/EditVideogame/{videogiocoId}")
    public ResponseEntity<?> EditVideogame(@PathVariable int videogiocoId ,@RequestBody Videogioco videogioco){
        try {
            DBManager.getInstance().VideogiocoDAO().updateVideogioco(videogioco,videogiocoId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(VideogameMessageDB.ERROR_EDIT_VIDEOGAME_MESSAGE,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
