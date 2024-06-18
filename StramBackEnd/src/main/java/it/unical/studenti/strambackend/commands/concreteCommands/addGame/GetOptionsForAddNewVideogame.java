package it.unical.studenti.strambackend.commands.concreteCommands.addGame;

import it.unical.studenti.strambackend.commands.Command;
import it.unical.studenti.strambackend.persistence.DBManager;
import it.unical.studenti.strambackend.util.Settings;
import org.springframework.http.ResponseEntity;
import org.thymeleaf.expression.Sets;

import java.util.*;

public class GetOptionsForAddNewVideogame extends Command {


    public GetOptionsForAddNewVideogame(Map<String, String> allParameters) {
        super(allParameters);
    }

    @Override
    protected <T> ResponseEntity<T> logicCommand(Map<String, String> allParameters) {

        Map<String, List<String>> response = new HashMap<>();



        List<String> yearOptions = new ArrayList<>(1);
        response.put("fromYear", yearOptions);

        List<String> genreOptions = new ArrayList<>();
        response.put("genres", genreOptions);

        List<String> casaProduttriceOptions = new ArrayList<>();
        response.put("caseProduttrici", casaProduttriceOptions);


        yearOptions.add(String.valueOf(Settings.MIN_VIDEOGAME_YEAR_OPTION));
        //prendere dal database
        genreOptions.add("avventura");
        genreOptions.add("horror");
        casaProduttriceOptions.add("mojang");
        casaProduttriceOptions.add("epicGames");

        return (ResponseEntity<T>) ResponseEntity.ok().body(response);
    }

    @Override
    protected Set<Settings.params> getRequiredParams() {
        return Set.of(Settings.params.USERTOKEN);
    }
}
