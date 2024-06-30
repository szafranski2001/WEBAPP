package it.unical.studenti.strambackend.controller;

import it.unical.studenti.strambackend.commands.CommandInvoker;
import it.unical.studenti.strambackend.commands.concreteCommands.addGame.GetOptionsForAddNewVideogame;
import it.unical.studenti.strambackend.commands.concreteCommands.home.GetSliders;
import it.unical.studenti.strambackend.persistence.DBManager;
import it.unical.studenti.strambackend.persistence.Model.Videogioco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin("http://localhost:4200")
public class HomeController {


    public HomeController() {}

    @PostMapping("/getSliders") @ResponseBody
    public ResponseEntity<Map<String, List<Videogioco>>> getSliders(@RequestBody Map<String, String> allParams) {
        Map<String, List<Videogioco>> res = new HashMap<>();

        res.put("big-poster", null);
        res.put("Top 10", DBManager.getInstance().VideogiocoDAO().top10());

        return new ResponseEntity<>(res, HttpStatus.OK);
    }


}