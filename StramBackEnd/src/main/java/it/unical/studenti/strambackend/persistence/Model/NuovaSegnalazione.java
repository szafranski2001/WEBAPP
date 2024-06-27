package it.unical.studenti.strambackend.persistence.Model;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("NuovaSegnalazione")
public class NuovaSegnalazione extends StateSegnalazioni {

	NuovaSegnalazione(Segnalazioni segnalazione) {
		super(segnalazione);
	}

	@Override
	public String statoSegnalazione() {
		return "NUOVA SEGNALAZIONE";
	}
	

}
