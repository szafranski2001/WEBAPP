package com.project.model;

public class ListeRequest {
	private String username;
    private int idVideogioco;


    public ListeRequest() {
    }

    public ListeRequest(String username, int idVideogioco) {
		super();
		this.username = username;
		this.idVideogioco = idVideogioco;
	}

	public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getIdVideogioco() {
        return idVideogioco;
    }

    public void setIdVideogioco(int idVideogioco) {
        this.idVideogioco = idVideogioco;
    }
}