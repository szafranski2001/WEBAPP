package it.unical.studenti.strambackend.commands;

import it.unical.studenti.strambackend.util.Settings;
import org.springframework.http.ResponseEntity;

import java.util.Map;
import java.util.Set;

public abstract class Command {
    protected final Map<String, String> allParameters;

    public Command(Map<String, String> allParameters) {
        this.allParameters = allParameters;
    }

    // Public method
    public <T> ResponseEntity<T> execute() {

        if (!checkRequiredParameters(allParameters))
            return ResponseEntity.badRequest().build();

        return logicCommand(allParameters);
    }

    protected boolean checkRequiredParameters(Map<String, String> allParameters) {
        for (Settings.params param : this.getRequiredParams())
            if (!allParameters.containsKey(param.label))
                return false;

        return true;
    }

    protected abstract <T> ResponseEntity<T> logicCommand(Map<String, String> allParameters);

    protected abstract Set<Settings.params> getRequiredParams();



}
