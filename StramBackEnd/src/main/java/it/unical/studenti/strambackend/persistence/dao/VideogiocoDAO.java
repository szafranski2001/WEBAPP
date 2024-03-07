package it.unical.studenti.strambackend.persistence.dao;

import java.util.List;

import it.unical.studenti.strambackend.persistence.Model.Videogioco;


public interface VideogiocoDAO {
	public void save(Videogioco videogioco); // inserisco nel DB un nuovo VIDEOGIOCO
	public Videogioco findByPrimaryKey(int idVideogioco);	// RESTITUISCO L'OGGETTO VIDEOGIOCO IN BASE ALL'ID
	public List<Videogioco> findAll();       // CERCO TUTTI I VIDEOGIOCO PRESENTI NEL DB
	public void update(Videogioco videogioco);	
	public void delete(int videogiocoId);	//ELIMINO UN VIDEOGIOCO
	public List<Videogioco> findByName(Videogioco videogioco);
	public List<Videogioco> risultati(String input); // CERCO NEL DB VIDEOGIOCO CON CARATTERISTICHE SIMILI ALL'INPUT
	public boolean existsVideogioco(Videogioco videogioco); // CONTROLLO SE UN VIDEOGIOCO ESISTE
	public boolean updateVideogioco(Videogioco videogioco, int videogiocoId);//AGGIORNO I DATI DI UN VIDEOGIOCO
	public String findTitoloById(int videogioco); //PRENDO IL TITOLO DI UN VIDEOGIOCO UTILIZZANDO IL SUO ID
	public int lastID(); //RESTITUISCO L'ULTIMO ID INSERITO
	
}
