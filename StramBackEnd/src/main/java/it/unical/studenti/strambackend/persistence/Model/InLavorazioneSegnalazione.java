package it.unical.studenti.strambackend.persistence.Model;

public class InLavorazioneSegnalazione extends StateSegnalazioni {
		
		public InLavorazioneSegnalazione(Segnalazioni segnalazione) {
		super(segnalazione);
	}

	

	@Override
	public String statoSegnalazione() {
		return "IN LAVORAZIONE";
		
	}
	

}
