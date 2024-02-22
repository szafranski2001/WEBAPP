package it.unical.studenti.strambackend.persistence.dao;

import java.util.List;

import it.unical.studenti.strambackend.persistence.Model.Segnalazioni;

public interface SegnalazioniDAO {
	public void CreateReport(Segnalazioni segnalazione); //CREO UNA NUOVA SEGNALAZIONE
	public void DeleteReport(Segnalazioni segnalazione); // ELIMINO UNA SEGNALAZIONE
	public boolean ExistReport(Segnalazioni segnalazione);// CONRTOLLO SE UNA SEGNALAZIONE ESISTE
	public List <Segnalazioni> findAll(); // PRENDO TUTTE LE SEGNALAZIONI ATTIVE
	public void updateSegnalazione(Segnalazioni segnalazione); //AGGIORNO LO STATO DI UNA SEGNALAZIONE

}
