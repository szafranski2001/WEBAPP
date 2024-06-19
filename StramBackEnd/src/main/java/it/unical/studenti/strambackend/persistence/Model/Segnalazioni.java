package it.unical.studenti.strambackend.persistence.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

public class Segnalazioni {
	@JsonManagedReference
	private StateSegnalazioni stato;
	private String mittente;
	private String destinatario;
	private int idVideogioco;
	
	public Segnalazioni() {
		this.stato=new NuovaSegnalazione(this);

	}
	public Segnalazioni(String mittente, String destinatario,int idVideogioco) {
		this.mittente = mittente;
		this.destinatario = destinatario;
		this.idVideogioco = idVideogioco;
		this.stato = new NuovaSegnalazione(this);
	}
	
	public void changeState(StateSegnalazioni state)
	{
		this.stato=state;
	}
	public StateSegnalazioni getState() {
		return stato;
	}
	
	public void setState(StateSegnalazioni state) {
		this.stato = state;
	}
	
	public String getMittente() {
		return mittente;
	}
	
	public String getDestinatario() {
		return destinatario;
	}
	
	public int getIdVideogioco() {
		return idVideogioco;
	}
	
    public String getStato() {
        return stato.statoSegnalazione();
    }
	
}
