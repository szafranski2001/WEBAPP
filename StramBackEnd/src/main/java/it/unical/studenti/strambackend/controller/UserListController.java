package it.unical.studenti.strambackend.controller;

import it.unical.studenti.strambackend.persistence.DBManager;
import it.unical.studenti.strambackend.persistence.ErrorMessage.ListMessageDB;
import it.unical.studenti.strambackend.persistence.Model.Lists;
import it.unical.studenti.strambackend.persistence.Model.SingleGameInfo;
import it.unical.studenti.strambackend.persistence.Model.Videogioco;
import it.unical.studenti.strambackend.persistence.dao.jdbc.ListeDAOJDBC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
public class UserListController {


    @GetMapping("/api/GetVideogameInPreferredList/")
    public ResponseEntity<SingleGameInfo[]> getUserPreferredList(@RequestParam String User) {
        List<Videogioco> videogames = DBManager.getInstance().listeDAO().OpenUserList(Lists.preferiti.toString(), User);
        SingleGameInfo[] gameInfos = new SingleGameInfo[videogames.size()];
        int i=0;
        for (Videogioco game : videogames) {
            SingleGameInfo gameInfo = new SingleGameInfo(i+1, game.getId(), game.getTitolo(), "http://localhost:8080/images/videogames/"+game.getId()+".png", game.getValutazione(), true);
            gameInfos[i] =gameInfo;
            i++;
        }
        return new ResponseEntity<>(gameInfos, HttpStatus.OK);
    }

    @GetMapping("/api/GetVideogameInWishList/")
    public ResponseEntity<SingleGameInfo[]> getUserWishList(@RequestParam String User) {
        List<Videogioco> videogames = DBManager.getInstance().listeDAO().OpenUserList(Lists.wishlist.toString(), User);
        SingleGameInfo[] gameInfos = new SingleGameInfo[videogames.size()];
        int i=0;
        for (Videogioco game : videogames) {
            SingleGameInfo gameInfo = new SingleGameInfo(i+1, game.getId(), game.getTitolo(), "http://localhost:8080/images/videogames/"+game.getId()+".png", game.getValutazione(), false);
            gameInfos[i] =gameInfo;
            i++;
        }
        return new ResponseEntity<>(gameInfos, HttpStatus.OK);
    }



    @PostMapping("/api/GetVideogameInPreferredList/{id}")
    public ResponseEntity<Boolean> GetVideogameInPreferredList(@PathVariable int id, @RequestBody String User){
        return new ResponseEntity<>(DBManager.getInstance().listeDAO().existVideogiocoInUserList(id,User,Lists.preferiti.toString()),HttpStatus.OK);
    }

    @PostMapping("/api/GetVideogameInWishList/{id}")
    public ResponseEntity<Boolean> GetVideogameInWishList(@PathVariable int id, @RequestBody String User){
        return new ResponseEntity<>(DBManager.getInstance().listeDAO().existVideogiocoInUserList(id,User,Lists.wishlist.toString()),HttpStatus.OK);
    }

    @PostMapping("/api/AddVideogameInPreferredList/{id}")
    public ResponseEntity<?> AddVideogameInPreferredList(@PathVariable int id, @RequestBody String User){
        try{
            DBManager.getInstance().listeDAO().InsertVideogameInList(id,User,Lists.preferiti.toString());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(ListMessageDB.ERROR_ADD_VIDEOGAME_MESSAGE,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/api/AddVideogameInWishList/{id}")
    public ResponseEntity<?> AddVideogameInWishList(@PathVariable int id, @RequestBody String User){
        try{
            DBManager.getInstance().listeDAO().InsertVideogameInList(id,User,Lists.wishlist.toString());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(ListMessageDB.ERROR_ADD_VIDEOGAME_MESSAGE,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/api/RemoveVideogameInPreferredList/{id}")
    public ResponseEntity<?> RemoveVideogameInPreferredList(@PathVariable int id, @RequestBody String User){
        try{
            DBManager.getInstance().listeDAO().RemoveVideogameFromList(id,User,Lists.preferiti.toString());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(ListMessageDB.ERROR_REMOVE_VIDEOGAME_MESSAGE,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/api/RemoveVideogameInWishList/{id}")
    public ResponseEntity<?> RemoveVideogameInWishList(@PathVariable int id, @RequestBody String User){
        try{
            DBManager.getInstance().listeDAO().RemoveVideogameFromList(id,User,Lists.wishlist.toString());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(ListMessageDB.ERROR_REMOVE_VIDEOGAME_MESSAGE,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
