package it.unical.studenti.strambackend.controller;

import it.unical.studenti.strambackend.persistence.DBManager;
import it.unical.studenti.strambackend.persistence.Model.Likeato;
import it.unical.studenti.strambackend.persistence.Model.Recensione;
import it.unical.studenti.strambackend.persistence.Model.Segnalazioni;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
public class ReviewController {

    @GetMapping("/api/GetReviews/{id}")
    public ResponseEntity<List<Recensione>> GetReviews(@PathVariable int id){
        try{
            List<Recensione> reviews=DBManager.getInstance().recensioneDAO().findByVideogioco(null,id);
            return new ResponseEntity<>(reviews,HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/api/AddReview")
    public ResponseEntity<?> AddReview(@RequestBody Recensione review){
        try {
            DBManager.getInstance().recensioneDAO().save(review);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/api/DeleteReview")
    public ResponseEntity<?> DeleteReview(@RequestBody Recensione review){
        try{
            DBManager.getInstance().recensioneDAO().delete(review);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Fixare post User
    @GetMapping("/api/GetLikes/{videogameId}")
    public ResponseEntity<List<Likeato>> GetLikes(@PathVariable int videogameId){
        List<Likeato> UserLikes=DBManager.getInstance().recensioneDAO().findLikes(null); //capire come prendere l'utente
        return new ResponseEntity<>(UserLikes,HttpStatus.OK);
    }

    @PostMapping("/api/AddLike")
    public ResponseEntity<?> AddLikeToReview(@RequestBody Likeato likeato){
        try{
            DBManager.getInstance().recensioneDAO().addOrRemoveLike(likeato,1);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/api/RemoveLike")
    public ResponseEntity<?> RemoveLikeFromReview(@RequestBody Likeato likeato){
        try{
            DBManager.getInstance().recensioneDAO().addOrRemoveLike(likeato,-1);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //@GetMapping("/api/GetReports")

    @PostMapping("/api/AddReport")
    public ResponseEntity<?> AddReportToReview(@RequestBody Segnalazioni segnalazione){
        try{
            DBManager.getInstance().segnalazioniDAO().CreateReport(segnalazione);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/api/RemoveReport")
    public ResponseEntity<?> RemoveReportFromReview(@RequestBody Segnalazioni segnalazione){
        try{
            DBManager.getInstance().segnalazioniDAO().DeleteReport(segnalazione);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Mancano quelle chiamate dove serve prendere l'utente devo ancora capirei il modo migliore per farlo

}
