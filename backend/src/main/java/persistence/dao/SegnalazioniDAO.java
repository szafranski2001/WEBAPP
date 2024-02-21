package persistence.dao;

import java.util.List;

import com.project.model.Segnalazioni;

public interface SegnalazioniDAO {
	public void CreateReport(Segnalazioni segnalazione); //CREO UNA NUOVA SEGNALAZIONE
	public void DeleteReport(Segnalazioni segnalazione); // ELIMINO UNA SEGNALAZIONE
	public boolean ExistReport(Segnalazioni segnalazione);// CONRTOLLO SE UNA SEGNALAZIONE ESISTE
	public List <Segnalazioni> findAll(); // PRENDO TUTTE LE SEGNALAZIONI ATTIVE
	public void updateSegnalazione(Segnalazioni segnalazione); //AGGIORNO LO STATO DI UNA SEGNALAZIONE

}
