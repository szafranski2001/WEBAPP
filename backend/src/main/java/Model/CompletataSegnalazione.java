package com.project.model;

public class CompletataSegnalazione extends StateSegnalazioni {
		
		public CompletataSegnalazione(Segnalazioni segnalazione) {
			super(segnalazione);
	}
	

	@Override
	public String statoSegnalazione() {
		return "COMPLETATA";
		
	}
	

}
