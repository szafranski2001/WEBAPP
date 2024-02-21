package com.project.model;

public abstract class StateSegnalazioni {
	Segnalazioni segnalazione;
	
	StateSegnalazioni(Segnalazioni segnalazione)
	{
		this.segnalazione=segnalazione;
	}
	public abstract String  statoSegnalazione();

}
