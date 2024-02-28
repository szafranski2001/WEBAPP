package it.unical.studenti.strambackend.controller;

import it.unical.studenti.strambackend.persistence.DBManager;
import it.unical.studenti.strambackend.persistence.Model.Recensione;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("http://localhost:4200")
public class ReviewController {

    @PostMapping("/api/AddReview")
    public boolean AddReview(@RequestBody Recensione review){
        DBManager.getInstance().recensioneDAO().save(review);
        return false;
    }
}
