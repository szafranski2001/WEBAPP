package com.project.model;
import java.util.ArrayList;
import java.util.List;



public class Liste{
    private String nome;
    private String username;
    private List <Videogioco>  lista  = new ArrayList <Videogioco> ();

    public Liste(String nome, String username)
    {
    	this.nome=nome;
    	this.username=username;
    }
    
    public void setList( List <Videogioco> lista) {
    	this.lista=lista;
    }

    public List<Videogioco> getLista() {
          return lista;
    } 

	public String getNome() {
	      return nome;
	}

  	public void addToList(Videogioco videogioco) {
  		lista.add(videogioco);
  	}
  	
  	public String getUsername() {
  		return username;
  	}
  	
  	public void removeFromList(Videogioco Spettacolo)
  	{
  		lista.remove(Spettacolo);
  	}

}