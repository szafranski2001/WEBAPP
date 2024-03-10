package it.unical.studenti.strambackend.commands.concreteCommands.addGame;

import it.unical.studenti.strambackend.commands.Command;
import it.unical.studenti.strambackend.util.Settings;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class GetVideogameGenresOptions extends Command {

    public GetVideogameGenresOptions(Map<String, String> allParameters) {
        super(allParameters);
    }

    @Override
    protected <T> ResponseEntity<T> logicCommand(Map<String, String> allParameters) {
        List<String> genres = List.of("Avventura", "Horror", "Strategia");
        return (ResponseEntity<T>) ResponseEntity.ok().body(genres);
    }

    @Override
    protected Set<Settings.params> getRequiredParams() {
        return Set.of(Settings.params.USERTOKEN);
    }
}
