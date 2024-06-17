package it.unical.studenti.strambackend.persistence.dao;

import java.util.List;
import it.unical.studenti.strambackend.persistence.Model.Recensione;
import it.unical.studenti.strambackend.persistence.Model.User;
import it.unical.studenti.strambackend.persistence.Model.Videogioco;
import it.unical.studenti.strambackend.persistence.Model.Likeato;
import it.unical.studenti.strambackend.persistence.exceptions.DatabaseException;

public interface RecensioneDAO {
	public void save(Recensione recensione) throws DatabaseException; //SALVO UNA NUOVA RECENSIONE
	public Recensione findByPrimaryKey(User user, Videogioco videogioco) throws DatabaseException;
	public List<Recensione> findAll() throws DatabaseException; //CERCO TUTTE LE RECENSIONI SCRITTE DA UN UTENTE
	public void delete(Recensione recensione) throws DatabaseException; //ELIMINO UNA RECENSIONE SCRITTA DA UN UTENTE
	public boolean existsRecensione(User user, Videogioco videogioco); //CONTROLLO SE UN UTENTE HA SCRITTO UNA RECENSIONE PER UN VIDEOGIOCO
	public Integer mediaVoti(int id) throws DatabaseException; //CALCOLO LA MEDIA DELLA VALUTAZIONI
	public List<Recensione> findByVideogioco(User user, int idVideogioco) throws DatabaseException; //RESTITUISCO LA LISTA DI RECENSIONI  CHE SONO STATE SCRITTE PER UN VIDEOGIOCO
	public void addOrRemoveLike(Likeato likeato, int change) throws DatabaseException;//AGGIUNGO O RIMUOVO UN LIKE DA UNA RECENSIONE

	public List<Likeato> findLikes(User user); //RESTITUISCO LA LISTA DI LIKE FATTI ALLE RECENSIONI DA UN UTENTE

	public List<Likeato> findLikesVideogame(String user, int idVideogioco);

}