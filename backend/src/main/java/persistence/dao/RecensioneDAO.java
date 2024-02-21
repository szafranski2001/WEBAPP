package persistence.dao;

import java.util.List;

import com.project.model.Likeato;
import com.project.model.Recensione;

public interface RecensioneDAO {
	public void save(int videogioco, String username, Integer voto, String commento); //SALVO UNA NUOVA RECENSIONE
	public Recensione findByPrimaryKey(String username, int videogioco); 
	public List<Recensione> findAll(String username); //CERCO TUTTE LE RECENSIONI SCRITTE DA UN UTENTE
	public void delete(String username, int videogioco); //ELIMINO UNA RECENSIONE SCRITTA DA UN UTENTE
	public boolean existsRecensione(String username, int videogioco); //CONTROLLO SE UN UTENTE HA SCRITTO UNA RECENSIONE PER UN FILM
	public Integer mediaVoti(int id); //CALCOLO LA MEDIA DELLA VALUTAZIONI
	public List<Recensione> findByVideogioco(int videogioco, String username); //RESTITUISCO LA LISTA DI RECENSIONI  CHE SONO STATE SCRITTE PER UN FILM
	public void addOrRemoveLike(Likeato likeato, int change ); //AGGIUNGO O RIMUOVO UN LIKE DA UNA RECENSIONE
	public List<Likeato> findLikes(String user); //RESTITUISCO LA LISTA DI LIKE FATTI ALLE RECENSIONI DA UN UTENTE
	
}