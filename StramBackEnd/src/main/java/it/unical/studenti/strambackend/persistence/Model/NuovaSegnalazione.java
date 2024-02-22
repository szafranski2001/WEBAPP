package it.unical.studenti.strambackend.persistence.Model;

public class NuovaSegnalazione extends StateSegnalazioni {

	NuovaSegnalazione(Segnalazioni segnalazione) {
		super(segnalazione);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String statoSegnalazione() {
		return "NUOVA SEGNALAZIONE";
		
	}
	

}
