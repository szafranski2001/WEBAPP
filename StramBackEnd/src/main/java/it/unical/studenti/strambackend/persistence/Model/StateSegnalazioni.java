package it.unical.studenti.strambackend.persistence.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;

public abstract class StateSegnalazioni {
	@JsonBackReference
	Segnalazioni segnalazione;
	
	StateSegnalazioni(Segnalazioni segnalazione) {
		this.segnalazione=segnalazione;
	}
	public abstract String  statoSegnalazione();

	public Segnalazioni getSegnalazione() {
		return segnalazione;
	}
}
