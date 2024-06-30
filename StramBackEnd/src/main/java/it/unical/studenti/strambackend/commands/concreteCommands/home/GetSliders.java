package it.unical.studenti.strambackend.commands.concreteCommands.home;

import it.unical.studenti.strambackend.commands.Command;
import it.unical.studenti.strambackend.persistence.DBManager;
import it.unical.studenti.strambackend.persistence.Model.Videogioco;
import it.unical.studenti.strambackend.util.Settings;
import org.springframework.http.ResponseEntity;

import java.util.*;

public class GetSliders extends Command {
    public GetSliders(Map<String, String> allParameters) {
        super(allParameters);
    }

    @Override
    protected <T> ResponseEntity<T> logicCommand(Map<String, String> allParameters) {
        Map<String, List<Videogioco>> res = new HashMap<>();

        res.put("big-poster", null);
        res.put("Top 10", DBManager.getInstance().VideogiocoDAO().top10());
        res.put("Adventure", DBManager.getInstance().VideogiocoDAO().risultati(""));

        //DBManager.getInstance().VideogiocoDAO().risultati("")

        return (ResponseEntity<T>) res;
    }

    @Override
    protected Set<Settings.params> getRequiredParams() {
        return Set.of();
    }



}
