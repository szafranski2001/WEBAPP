package it.unical.studenti.strambackend.persistence.Model;

public class NuovaSegnalazione extends StateSegnalazioni {

	NuovaSegnalazione(Segnalazioni segnalazione) {
		super(segnalazione);
	}

	@Override
	public String statoSegnalazione() {
		return "NUOVA SEGNALAZIONE";
		
	}
	

}
