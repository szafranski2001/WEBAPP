package it.unical.studenti.strambackend.controller;

import it.unical.studenti.strambackend.persistence.DBManager;
import it.unical.studenti.strambackend.persistence.ErrorMessage.ReviewMessageDB;
import it.unical.studenti.strambackend.persistence.Model.Likeato;
import it.unical.studenti.strambackend.persistence.Model.Recensione;
import it.unical.studenti.strambackend.persistence.Model.Segnalazioni;
import it.unical.studenti.strambackend.persistence.dao.jdbc.VideogiocoProxy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
            VideogiocoProxy.UpdateRatingAfterAddingReview(review.getVideogioco());
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(ReviewMessageDB.ERROR_ADD_REVIEW_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/api/DeleteReview")
    public ResponseEntity<?> DeleteReview(@RequestBody Recensione review){
        try{
            DBManager.getInstance().recensioneDAO().delete(review);
            VideogiocoProxy.UpdateRatingAfterAddingReview(review.getVideogioco());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(ReviewMessageDB.ERROR_REMOVE_REVIEW_MESSAGE,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/api/GetLikes/{videogameId}")
    public ResponseEntity<List<Likeato>> GetLikes(@PathVariable int videogameId, @RequestBody String user){
        List<Likeato> UserLikes = DBManager.getInstance().recensioneDAO().findLikesVideogame(user,videogameId);
        return new ResponseEntity<>(UserLikes, HttpStatus.OK);
    }

    @PostMapping("/api/GetReports/{videogameId}")
    public ResponseEntity<List<Segnalazioni>> GetReports(@PathVariable int videogameId, @RequestBody String user){
        List<Segnalazioni> UserReports = DBManager.getInstance().segnalazioniDAO().findSegnalazioniUser(user,videogameId);
        return new ResponseEntity<>(UserReports, HttpStatus.OK);
    }

    @PostMapping("/api/AddLike")
    public ResponseEntity<?> AddLikeToReview(@RequestBody Likeato likeato){
        try{
            DBManager.getInstance().recensioneDAO().addOrRemoveLike(likeato,1);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(ReviewMessageDB.ERROR_ADD_LIKE_REVIEW_MESSAGE,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/api/RemoveLike")
    public ResponseEntity<?> RemoveLikeFromReview(@RequestBody Likeato likeato){
        try{
            DBManager.getInstance().recensioneDAO().addOrRemoveLike(likeato,-1);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(ReviewMessageDB.ERROR_REMOVE_LIKE_REVIEW_MESSAGE,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/api/AddReport")
    public ResponseEntity<?> AddReportToReview(@RequestBody Segnalazioni segnalazione){
        try{
            DBManager.getInstance().segnalazioniDAO().CreateReport(segnalazione);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(ReviewMessageDB.ERROR_ADD_REPORT_REVIEW_MESSAGE,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/api/RemoveReport")
    public ResponseEntity<?> RemoveReportFromReview(@RequestBody Segnalazioni segnalazione){
        try{
            DBManager.getInstance().segnalazioniDAO().DeleteReport(segnalazione);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(ReviewMessageDB.ERROR_REMOVE_REPORT_REVIEW_MESSAGE,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
