package it.unical.studenti.strambackend.commands.concreteCommands.addGame;

import it.unical.studenti.strambackend.commands.Command;
import it.unical.studenti.strambackend.util.Settings;
import org.springframework.http.ResponseEntity;
import org.thymeleaf.expression.Sets;

import java.util.Map;
import java.util.Set;

public class CreateVideogame extends Command {

    public CreateVideogame(Map<String, String> allParameters) {
        super(allParameters);
    }

    @Override
    protected <T> ResponseEntity<T> logicCommand(Map<String, String> allParameters) {
        return null;
    }

    @Override
    protected Set<Settings.params> getRequiredParams() {
        return Set.of(Settings.params.USERTOKEN);
    }
}
