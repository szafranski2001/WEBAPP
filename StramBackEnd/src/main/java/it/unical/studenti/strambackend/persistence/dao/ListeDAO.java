package it.unical.studenti.strambackend.persistence.dao;

import java.util.List;

import it.unical.studenti.strambackend.persistence.Model.Videogioco;
import it.unical.studenti.strambackend.persistence.exceptions.DatabaseException;

public interface ListeDAO {

    public boolean existVideogiocoInUserList(int idVideogioco, String User, String NomeLista);

    public void InsertVideogameInList(int idVideogioco, String User, String NomeLista) throws DatabaseException;

    public void RemoveVideogameFromList(int idVideogioco, String User, String NomeLista) throws DatabaseException;

    public List <Videogioco> OpenUserList(String NomeLista, String user);


}
