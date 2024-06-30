package it.unical.studenti.strambackend.commands;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CommandInvoker {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public <T> ResponseEntity<T> executeCommand() {
        return command.execute();
    }

}