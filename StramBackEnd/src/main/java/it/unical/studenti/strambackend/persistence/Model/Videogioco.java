package it.unical.studenti.strambackend.persistence.Model;

public class Videogioco {
	private int id;
	private String titolo;
	private String descrizione;
	private String genere;
	private int durata;
	private int anno;
	private int valutazione;
	private String img;
	private String trailer;
	
	public Videogioco() {}
	
	public Videogioco(int id, String titolo, String descrizione, String genere, int durata, int anno, int valutazione, String img, String trailer) {
		super();
		this.id = id;
		this.titolo = titolo;
		this.descrizione = descrizione;
		this.genere = genere;
		this.durata = durata;
		this.anno = anno;
		this.valutazione = valutazione;
		this.img = img;
		this.trailer = trailer;
		
	}


	public String getTrailer() {
		return trailer;
	}
	public void setTrailer(String trailer) {
		this.trailer = trailer;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getGenere() {
		return genere;
	}
	public void setGenere(String genere) {
		this.genere = genere;
	}
	public int getDurata() {
		return durata;
	}
	public void setDurata(int durata) {
		this.durata = durata;
	}
	public int getAnno() {
		return anno;
	}
	public void setAnno(int anno) {
		this.anno = anno;
	}
	public int getValutazione() {
		return valutazione;
	}
	public void setValutazione(int valutazione) {
		this.valutazione = valutazione;
	}
	
	

}
