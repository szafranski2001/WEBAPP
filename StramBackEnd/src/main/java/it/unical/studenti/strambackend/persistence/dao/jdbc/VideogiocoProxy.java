
package it.unical.studenti.strambackend.persistence.dao.jdbc;

import it.unical.studenti.strambackend.persistence.DBManager;
import it.unical.studenti.strambackend.persistence.DBSource;
import it.unical.studenti.strambackend.persistence.ErrorMessage.VideogameMessageDB;
import it.unical.studenti.strambackend.persistence.Model.TokenManager;
import it.unical.studenti.strambackend.persistence.Model.Videogioco;
import it.unical.studenti.strambackend.persistence.dao.VideogiocoDAO;
import it.unical.studenti.strambackend.persistence.exceptions.DatabaseException;
import it.unical.studenti.strambackend.persistence.exceptions.NoAuthException;

import java.util.List;

public class VideogiocoProxy implements VideogiocoDAO {

    private final VideogiocoDAOJDBC videogiocoDAOJDBC;

    private String CurrentToken;

    private final String ERROR_NO_AUTH_MESSAGE = "Non si ha l'autorizzazione per compiere questa operazione";

    public VideogiocoProxy(DBSource source) {
        this.videogiocoDAOJDBC = new VideogiocoDAOJDBC(source);
    }

    @Override
    public void save(Videogioco videogioco) throws Exception {
        //Manca da inserire un controllo per vedere se l'utente è loggato ed è un admin
        if(true){
            videogiocoDAOJDBC.save(videogioco);
        }
        else{
            throw new NoAuthException(ERROR_NO_AUTH_MESSAGE);
        }
    }

    @Override
    public Videogioco findByPrimaryKey(int idVideogioco) throws Exception {
        return videogiocoDAOJDBC.findByPrimaryKey(idVideogioco);
    }

    @Override
    public List<Videogioco> findAll() throws Exception {
        return videogiocoDAOJDBC.findAll();
    }

    @Override
    public void update(Videogioco videogioco) {
        videogiocoDAOJDBC.update(videogioco);
    }

    @Override
    public void delete(int videogiocoId) throws Exception {
        if(true)
            videogiocoDAOJDBC.delete(videogiocoId);
        else {
            throw new NoAuthException(ERROR_NO_AUTH_MESSAGE);
        }
    }

    @Override
    public List<Videogioco> findByName(Videogioco videogioco) throws Exception {
        return videogiocoDAOJDBC.findByName(videogioco);
    }

    @Override
    public List<Videogioco> risultati(String input) {
        return videogiocoDAOJDBC.risultati(input);
    }

    @Override
    public boolean existsVideogioco(Videogioco videogioco) {
        return videogiocoDAOJDBC.existsVideogioco(videogioco);
    }

    @Override
    public void updateVideogioco(Videogioco videogioco, int videogiocoId) throws Exception {
        if(true){
            videogiocoDAOJDBC.updateVideogioco(videogioco,videogiocoId);
        }
        else{
            throw new NoAuthException(ERROR_NO_AUTH_MESSAGE);
        }
    }

    @Override
    public String findTitoloById(int videogioco) {
        return videogiocoDAOJDBC.findTitoloById(videogioco);
    }

    @Override
    public int lastID() {
        return videogiocoDAOJDBC.lastID();
    }

}
