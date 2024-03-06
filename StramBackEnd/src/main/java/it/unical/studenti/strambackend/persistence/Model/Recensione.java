package it.unical.studenti.strambackend.persistence.Model;


public class Recensione {
	private String username;
	private String commento;
	private Integer voto;
	private int videogioco;
	private Integer likes;

	private String titolo;
	
	public Recensione() {}
	
	
	public Recensione(String username,String titolo, String commento, Integer voto, int videogioco, Integer likes) {
		super();
		this.username = username;
		this.titolo=titolo;
		this.commento = commento;
		this.voto = voto;
		this.videogioco = videogioco;
		this.likes = likes;
	}

	public int getVideogioco() {
		return videogioco;
	}
	
	public void setVideogioco(int videogioco) {
		this.videogioco = videogioco;
	}
	
	public int getVoto() {
		return voto;
	}
	
	public Integer getLikes() {
	    return likes != null ? likes : 0;
	}
	
	public void setLikes(Integer likes) {
		this.likes = likes;
	}
	
	public void setVoto(Integer voto) {
		this.voto = voto;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getCommento() {
		return commento;
	}
	
	public void setCommento(String commento) {
		this.commento = commento;
	}

	public String getTitolo(){ return titolo;}

	public void setTitolo(String titolo){ this.titolo=titolo; }

}
