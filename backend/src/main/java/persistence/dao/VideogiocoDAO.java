package persistence.dao;

import java.util.List;

import com.project.model.Videogioco;


public interface VideogiocoDAO {
	public void save(String titolo, String descrizione, String genere, int durata, int anno, String img, String trailer); // inserisco nel DB un nuovo FILM
	public Videogioco findByPrimaryKey(int idVideogioco);	// RESTITUISCO L'OGGETTO FILM IN BASE ALL'ID
	public List<Videogioco> findAll();       // CERCO TUTTI I FILM PRESENTI NEL DB
	public void update(Videogioco videogioco);	
	public void delete(int  idVideogioco);	//ELIMINO UN FILM
	public List<Videogioco> findByName(String titolo);
	public List<Videogioco> risultati(String input); // CERCO NEL DB FILM CON CARATTERISTICHE SIMILI ALL'INPUT
	public boolean existsVideogioco(int id); // CONTROLLO SE UN FILM ESISTE
	public boolean updateVideogioco(Videogioco oldV, Videogioco newV);//AGGIORNO I DATI DI UN FILM
	public String findTitoloById(int idVideogioco); //PRENDO IL TITOLO DI UN FILM UTILIZZANDO IL SUO ID
	public int lastID(); //RESTITUISCO L'ULTIMO ID INSERITO
	
}
