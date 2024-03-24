package it.unical.studenti.strambackend.commands.concreteCommands.addGame;

import it.unical.studenti.strambackend.commands.Command;
import it.unical.studenti.strambackend.persistence.DBManager;
import it.unical.studenti.strambackend.util.Settings;
import org.springframework.http.ResponseEntity;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GetVideogameAziendeProduttriciOptions extends Command {

    public GetVideogameAziendeProduttriciOptions(Map<String, String> allParams) {
        super(allParams);
    }

    @Override
    protected <T> ResponseEntity<T> logicCommand(Map<String, String> allParameters) {
        //DBManager.getInstance().
        List<String> aziendeProduttrici = List.of("Mojang", "Epic Games");
        return (ResponseEntity<T>) ResponseEntity.ok().body(aziendeProduttrici);
    }

    @Override
    protected Set<Settings.params> getRequiredParams() {
        return Set.of(Settings.params.USERTOKEN);
    }

}
