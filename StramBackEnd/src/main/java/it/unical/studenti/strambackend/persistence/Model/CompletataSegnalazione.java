package it.unical.studenti.strambackend.persistence.Model;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("CompletataSegnalazione")
public class CompletataSegnalazione extends StateSegnalazioni {
		
		public CompletataSegnalazione(Segnalazioni segnalazione) {
			super(segnalazione);
	}
	

	@Override
	public String statoSegnalazione() {
		return "COMPLETATA";
		
	}
	

}
