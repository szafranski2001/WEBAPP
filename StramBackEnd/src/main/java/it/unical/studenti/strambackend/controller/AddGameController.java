package it.unical.studenti.strambackend.controller;

import it.unical.studenti.strambackend.commands.CommandInvoker;
import it.unical.studenti.strambackend.commands.concreteCommands.addGame.GetOptionsForAddNewVideogame;
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

    @PostMapping("/getOptionsForAddNewVideogame") @ResponseBody
    public ResponseEntity<Object> getOptionsForAddNewVideogame(@RequestBody Map<String, String> allParams) {
        commandInvoker.setCommand(new GetOptionsForAddNewVideogame(allParams));
        return commandInvoker.executeCommand();
    }




}
