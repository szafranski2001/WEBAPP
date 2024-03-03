package it.unical.studenti.strambackend.controller;

import it.unical.studenti.strambackend.persistence.DBManager;
import it.unical.studenti.strambackend.persistence.Model.Likeato;
import it.unical.studenti.strambackend.persistence.Model.Recensione;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:4200")
public class ReviewController {

    @PostMapping(value = "/api/AddReview")
    public ResponseEntity<String> AddReview(@RequestBody Recensione review){
        try {
            DBManager.getInstance().recensioneDAO().save(review);
            return new ResponseEntity<>(HttpStatus.OK);
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



}
