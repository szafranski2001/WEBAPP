package com.project.model;

public class Likeato {
	private String usernameMittente;
    private int idVideogioco;
    private String usernameDestinatario;

    
    public Likeato(String usernameMittente, int idVideogioco, String usernameDestinatario) {
		this.usernameMittente = usernameMittente;
		this.idVideogioco = idVideogioco;
		this.usernameDestinatario = usernameDestinatario;
	}

	public String getUsernameMittente() {
        return usernameMittente;
    }

    public int getIdVideogioco() {
        return idVideogioco;
    }
    
	public String getUsernameDestinatario() {
        return usernameDestinatario;
    }

	@Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Likeato like = (Likeato) obj;

        return usernameMittente.equals(like.usernameMittente) && idVideogioco == like.idVideogioco && usernameDestinatario.equals(like.usernameDestinatario);
    }
}