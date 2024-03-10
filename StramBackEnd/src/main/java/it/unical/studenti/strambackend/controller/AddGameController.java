package it.unical.studenti.strambackend.controller;

import it.unical.studenti.strambackend.commands.CommandInvoker;
import it.unical.studenti.strambackend.commands.concreteCommands.addGame.GetVideogameAziendeProduttriciOptions;
import it.unical.studenti.strambackend.commands.concreteCommands.addGame.GetVideogameGenresOptions;
import it.unical.studenti.strambackend.commands.concreteCommands.addGame.GetVideogameYearsOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@CrossOrigin("http://localhost:4200")
public class AddGameController {
    private final CommandInvoker commandInvoker;

    @Autowired
    public AddGameController(CommandInvoker commandInvoker) {
        this.commandInvoker = commandInvoker;
    }


    @PostMapping("/getVideogameYearsOptions") @ResponseBody
    public ResponseEntity<Object> getVideogameYearsOptions(@RequestBody Map<String,String> allParams) {
        commandInvoker.setCommand(new GetVideogameYearsOptions(allParams));
        return commandInvoker.executeCommand();
    }

    @PostMapping("/getVideogameAziendeProduttriciOptions") @ResponseBody
    public ResponseEntity<Object> getVideogameAziendeProduttriciOptions(@RequestBody Map<String, String> allParams) {
        commandInvoker.setCommand(new GetVideogameAziendeProduttriciOptions(allParams));
        return commandInvoker.executeCommand();
    }

    @PostMapping("/getVideogameGenresOptions") @ResponseBody
    public ResponseEntity<Object> getVideogameGenresOptions(@RequestBody Map<String, String> allParams) {
        commandInvoker.setCommand(new GetVideogameGenresOptions(allParams));
        return commandInvoker.executeCommand();
    }




}
