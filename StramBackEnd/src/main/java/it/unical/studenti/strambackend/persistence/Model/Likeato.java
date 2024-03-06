package it.unical.studenti.strambackend.persistence.Model;

public class Likeato {
	private String mittente;
    private String destinatario;
    private int idVideogioco;

    
    public Likeato(String usernameMittente, int idVideogioco, String usernameDestinatario) {
		this.mittente = usernameMittente;
		this.idVideogioco = idVideogioco;
		this.destinatario = usernameDestinatario;
	}

	public String getMittente() {
        return mittente;
    }

    public int getIdVideogioco() {
        return idVideogioco;
    }
    
	public String getDestinatario() {
        return destinatario;
    }

	@Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Likeato like = (Likeato) obj;

        return mittente.equals(like.mittente) && idVideogioco == like.idVideogioco && destinatario.equals(like.destinatario);
    }
}