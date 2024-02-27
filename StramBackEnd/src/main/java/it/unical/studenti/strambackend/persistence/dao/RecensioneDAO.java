package it.unical.studenti.strambackend.persistence.dao;

import java.util.List;
import it.unical.studenti.strambackend.persistence.Model.Recensione;
import it.unical.studenti.strambackend.persistence.Model.User;
import it.unical.studenti.strambackend.persistence.Model.Videogioco;
import it.unical.studenti.strambackend.persistence.Model.Likeato;

public interface RecensioneDAO {
	public void save(Recensione recensione); //SALVO UNA NUOVA RECENSIONE
	public Recensione findByPrimaryKey(User user, Videogioco videogioco); 
	public List<Recensione> findAll(); //CERCO TUTTE LE RECENSIONI SCRITTE DA UN UTENTE
	public void delete(User user, Videogioco videogioco); //ELIMINO UNA RECENSIONE SCRITTA DA UN UTENTE
	public boolean existsRecensione(User user, Videogioco videogioco); //CONTROLLO SE UN UTENTE HA SCRITTO UNA RECENSIONE PER UN FILM
	public Integer mediaVoti(int id); //CALCOLO LA MEDIA DELLA VALUTAZIONI
	public List<Recensione> findByVideogioco(User user, Videogioco videogioco); //RESTITUISCO LA LISTA DI RECENSIONI  CHE SONO STATE SCRITTE PER UN FILM
	public void addOrRemoveLike(Likeato likeato, int change ); //AGGIUNGO O RIMUOVO UN LIKE DA UNA RECENSIONE
	public List<Likeato> findLikes(User user); //RESTITUISCO LA LISTA DI LIKE FATTI ALLE RECENSIONI DA UN UTENTE
	
}