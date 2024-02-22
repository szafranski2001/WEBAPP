package it.unical.studenti.strambackend.persistence.Model;

public class CompletataSegnalazione extends StateSegnalazioni {
		
		public CompletataSegnalazione(Segnalazioni segnalazione) {
			super(segnalazione);
	}
	

	@Override
	public String statoSegnalazione() {
		return "COMPLETATA";
		
	}
	

}
