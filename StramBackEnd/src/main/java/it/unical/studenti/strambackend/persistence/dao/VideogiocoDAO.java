package it.unical.studenti.strambackend.persistence.dao;

import java.util.List;

import it.unical.studenti.strambackend.persistence.Model.Videogioco;
import it.unical.studenti.strambackend.persistence.exceptions.DatabaseException;


public interface VideogiocoDAO {
	public void save(Videogioco videogioco) throws DatabaseException; // inserisco nel DB un nuovo VIDEOGIOCO
	public Videogioco findByPrimaryKey(int idVideogioco) throws DatabaseException;	// RESTITUISCO L'OGGETTO VIDEOGIOCO IN BASE ALL'ID
	public List<Videogioco> findAll() throws DatabaseException;       // CERCO TUTTI I VIDEOGIOCHI PRESENTI NEL DB
	public void update(Videogioco videogioco);	
	public void delete(int videogiocoId) throws DatabaseException;	//ELIMINO UN VIDEOGIOCO
	public List<Videogioco> findByName(Videogioco videogioco) throws DatabaseException;
	public List<Videogioco> risultati(String input); // CERCO NEL DB VIDEOGIOCO CON CARATTERISTICHE SIMILI ALL'INPUT
	public boolean existsVideogioco(Videogioco videogioco); // CONTROLLO SE UN VIDEOGIOCO ESISTE
	public void updateVideogioco(Videogioco videogioco, int videogiocoId) throws DatabaseException;//AGGIORNO I DATI DI UN VIDEOGIOCO
	public String findTitoloById(int videogioco); //PRENDO IL TITOLO DI UN VIDEOGIOCO UTILIZZANDO IL SUO ID
	public int lastID(); //RESTITUISCO L'ULTIMO ID INSERITO
	
}
