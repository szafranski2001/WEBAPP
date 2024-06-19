package it.unical.studenti.strambackend.persistence.Model;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("InLavorazioneSegnalazione")
public class InLavorazioneSegnalazione extends StateSegnalazioni {
		
		public InLavorazioneSegnalazione(Segnalazioni segnalazione) {
		super(segnalazione);
	}

	

	@Override
	public String statoSegnalazione() {
		return "IN LAVORAZIONE";
		
	}
	

}
