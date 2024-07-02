package it.unical.studenti.strambackend.controller;

import it.unical.studenti.strambackend.persistence.DBManager;
import it.unical.studenti.strambackend.persistence.Model.Videogioco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@CrossOrigin("http://localhost:4200")
public class HomeController {


    public HomeController() {}

    @PostMapping("/getSliders") @ResponseBody
    public List<List<Videogioco>> getSliders() {
        List<List<Videogioco>> res = new LinkedList<>();

        res.add(DBManager.getInstance().VideogiocoDAO().top10());
        List<Videogioco> get10 = DBManager.getInstance().VideogiocoDAO().get10();

        Map<String, List<Videogioco>> genereMap = new HashMap<>();

        // Raggruppiamo i videogiochi per genere
        for (Videogioco v : get10) {
            genereMap.computeIfAbsent(v.getGenere(), k -> new LinkedList<>()).add(v);
        }

        // Aggiungiamo tutte le liste raggruppate a res
        res.addAll(genereMap.values());

        return res;
    }




}