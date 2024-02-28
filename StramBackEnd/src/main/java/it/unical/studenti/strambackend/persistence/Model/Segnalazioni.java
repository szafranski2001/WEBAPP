package it.unical.studenti.strambackend.persistence.Model;

import it.unical.studenti.strambackend.persistence.DBManager;

public class Segnalazioni {
	private StateSegnalazioni state;
	private String mittente;
	private String destinatario;
	private int idVideogioco;
	
	public Segnalazioni() {
		this.state=new NuovaSegnalazione(this);

	}
	public Segnalazioni(String mittente, String destinatario,int idVideogioco) {
		this.mittente = mittente;
		this.destinatario = destinatario;
		this.idVideogioco = idVideogioco;
		this.state = new NuovaSegnalazione(this);
	}
	
	public void changeState(StateSegnalazioni state)
	{
		this.state=state;
	}
	public StateSegnalazioni getState() {
		return state;
	}
	
	public void setState(StateSegnalazioni state) {
		this.state = state;
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
	
	public String getTitoloVideogioco() {
        String titolo = DBManager.getInstance().VideogiocoDAO().findTitoloById(this.idVideogioco);
        return titolo;
    }
	
    public String getStato() {
        return state.statoSegnalazione();
    }
	
}
