package it.unical.studenti.strambackend.commands.concreteCommands.addGame;

import it.unical.studenti.strambackend.commands.Command;
import it.unical.studenti.strambackend.util.Settings;
import org.springframework.http.ResponseEntity;

import java.time.Year;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class GetVideogameYearsOptions extends Command {


    private String userToken;

    public GetVideogameYearsOptions(Map<String, String> allParameters) {
        super(allParameters);
    }



    @Override
    protected <T> ResponseEntity<T> logicCommand(Map<String, String> allParameters) {

        LinkedList<String> years = new LinkedList<>();
        for(int i = Year.now().getValue(); i >= 1940; i--) {
            years.add(String.valueOf(i));
        }

        if (years.isEmpty())
            return (ResponseEntity<T>) ResponseEntity.internalServerError().body(years);
        return (ResponseEntity<T>) ResponseEntity.ok().body(years);
    }

    @Override
    protected Set<Settings.params> getRequiredParams() {
        return Set.of(Settings.params.USERTOKEN);
    }








    /*
    private String userToken;

    public GetVideogameYearsOptions(String userToken) {
        this.userToken = userToken;
    }





    @Override
    public boolean checkNecessaryParameters(Map<String, String> allParameters) {
        return false;
    }

     */

}
