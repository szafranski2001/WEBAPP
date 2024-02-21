package com.project.model;

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
