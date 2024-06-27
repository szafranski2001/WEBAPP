package it.unical.studenti.strambackend.persistence.dao;

import java.util.List;

import it.unical.studenti.strambackend.persistence.Model.User;
import it.unical.studenti.strambackend.persistence.exceptions.DatabaseException;

public interface UserDAO {
	public boolean existsUser(String user); //CONTROLLO SE UN USER ESISTE
	public boolean checkPassword(String user, String password); //CONTROLLO SE LA PASSWORD INSERITA È CORRETTA
	public User findByPrimaryKey(String username); //RESTITUISCO L'OGGETTO USER IN BASE AL SUO USERNAME
	public boolean save(User user); // SALVO UN NUOVO USER NEL DB
	public boolean existsUserEmail(String email); //CONTROLLO SE UN'EMAIL ESISTE
	public void setPassword(String username, String password); // AGGIORNO LA PASSWORD DI UN UTENTE
	public boolean update(User old, User newu); //AGIGORNO I DATI RELATIVI AD UN UTENTE
	public List<User> findAll(); //RESTITUISCO TUTTI GLI UTENTE REGISTRATI
	public void delete(String username) throws DatabaseException; //ELIMINO UN UTENTE
	public boolean bannedEmail(String email); //CONTROLLO SE L'EMAIL INSERITA È STATA BANNATA
	public String existsDomanda(String email); //CONTROLLO SE LA DOMANDA DI SICUREZZA ESISTE
	public boolean checkRisposta(String email, String risposta); // CONTROLLO SE LA RISPOSTA ALLA DOMANDA DI SICUREZZA È ESATTA
	public int getTypeUser(String user);
	public void setToken(String user, String token);
	public String getToken(String user);
}
