package it.unical.studenti.strambackend.persistence.dao;

import java.util.List;

import it.unical.studenti.strambackend.persistence.Model.Segnalazioni;
import it.unical.studenti.strambackend.persistence.Model.User;
import it.unical.studenti.strambackend.persistence.exceptions.DatabaseException;

public interface SegnalazioniDAO {
	public void CreateReport(Segnalazioni segnalazione) throws DatabaseException; //CREO UNA NUOVA SEGNALAZIONE
	public void DeleteReport(Segnalazioni segnalazione) throws DatabaseException; // ELIMINO UNA SEGNALAZIONE
	public boolean ExistReport(Segnalazioni segnalazione);// CONRTOLLO SE UNA SEGNALAZIONE ESISTE
	public List <Segnalazioni> findAll(); // PRENDO TUTTE LE SEGNALAZIONI ATTIVE
	public void updateSegnalazione(Segnalazioni segnalazione) throws DatabaseException; //AGGIORNO LO STATO DI UNA SEGNALAZIONE
	public List<Segnalazioni> findSegnalazioniUser(String user,int idVideogioco);

}
