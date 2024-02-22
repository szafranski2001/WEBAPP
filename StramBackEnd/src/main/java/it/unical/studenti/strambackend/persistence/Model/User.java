package it.unical.studenti.strambackend.persistence.Model;

import java.util.ArrayList;
import java.util.List;

import it.unical.studenti.strambackend.persistence.DBManager;

public class User {
	private String username;
	private String password;
	private String tipo;
	private String email;
	private String nome;
	private String cognome;
	private String domanda;
	private String risposta;
	private Liste preferiti = null;
    private Liste watchlist = null;
    private List  <Likeato> likes = new ArrayList <Likeato>();
	public User() {}
	
	
	public User(String username, String password, String email, String nome, String cognome, String domanda, String risposta) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.nome = nome;
		this.cognome = cognome;
		this.domanda = domanda;
		this.risposta = risposta;
		this.preferiti = new Liste("preferiti", this.username);
	    this.watchlist = new Liste("watchlist", this.username);
		this.preferiti.setList(DBManager.getInstance().listeDAO().OpenList(this.preferiti));
		this.watchlist.setList(DBManager.getInstance().listeDAO().OpenList(this.watchlist));
		this.likes= DBManager.getInstance().recensioneDAO().findLikes(username);
		
	}
	public List <Likeato> getLikes()
	{
		return likes;
	}


	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getTipo() {
		return tipo;
	}

	public Liste getPreferiti() {
		return preferiti;
	}


	public Liste getWatchlist() {
		return watchlist;
	}


	public String getEmail() {
		return email;
	}

	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}


	public String getDomanda() {
		return domanda;
	}


	public void setDomanda(String domanda) {
		this.domanda = domanda;
	}


	public String getRisposta() {
		return risposta;
	}


	public void setRisposta(String risposta) {
		this.risposta = risposta;
	}
	
	public String resetPassword(String emailUtente) throws Exception {
		String nuovaPassword = generaPassword();
		return nuovaPassword;
	}
	
	private String generaPassword(){
		
		String AlphaNumericString = 
				 "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz"
                + "[@!%&£°#'?*=.]"; 
		
		StringBuilder sb = new StringBuilder(20); 
		
		for (int i = 0; i < 10; i++) { 
			int index  = (int)(AlphaNumericString.length() * Math.random()); 
			sb.append(AlphaNumericString .charAt(index)); 
		} 

		return sb.toString();
		
	}
	public boolean checkRegexPassword(String password) {
        String regex="^(?=.*[A-Z])(?=.*[0-9])(?=.*[@!%&£°#'?*=.])(?!.*\\\\s)[a-zA-Z0-9@!%&£°#'?*=.]{8,}$";
        if (password.matches(regex))
        {         
            return true;
        }
        else
        {
            return false;
        }
		
	}

	
	public void addOrRemoveLikes(Likeato like)
    {
        if (this.likes.contains(like))
        {
            this.likes.add(like);
        }
        else
        {
            this.likes.remove(like);
        }
    }
	
	public boolean checkLiked(Likeato like)
    {
        if (this.likes.contains(like))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

}
