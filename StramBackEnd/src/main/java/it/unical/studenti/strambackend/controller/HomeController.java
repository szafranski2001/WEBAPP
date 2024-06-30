package it.unical.studenti.strambackend.controller;

import it.unical.studenti.strambackend.commands.CommandInvoker;
import it.unical.studenti.strambackend.commands.concreteCommands.addGame.GetOptionsForAddNewVideogame;
import it.unical.studenti.strambackend.commands.concreteCommands.home.GetSliders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@CrossOrigin("http://localhost:4200")
public class HomeController {

    private final CommandInvoker commandInvoker;

    @Autowired
    public HomeController(CommandInvoker commandInvoker) {
        this.commandInvoker = commandInvoker;
    }

    @PostMapping("/getSliders") @ResponseBody
    public ResponseEntity<?> getSliders(@RequestBody Map<String, String> allParams) {
        commandInvoker.setCommand(new GetSliders(allParams));
        return commandInvoker.executeCommand();
    }


}