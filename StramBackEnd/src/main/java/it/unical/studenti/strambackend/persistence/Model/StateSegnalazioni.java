package it.unical.studenti.strambackend.persistence.Model;

public abstract class StateSegnalazioni {
	Segnalazioni segnalazione;
	
	StateSegnalazioni(Segnalazioni segnalazione) {
		this.segnalazione=segnalazione;
	}
	public abstract String  statoSegnalazione();

}
