package it.unical.studenti.strambackend.persistence.dao;

import java.util.List;

import it.unical.studenti.strambackend.persistence.Model.Videogioco;
import it.unical.studenti.strambackend.persistence.Model.Liste;

public interface ListeDAO {
    public void insertList(Liste lista, Videogioco videogioco); // inserisci videogioco alla lista
    public List <Videogioco>  OpenList(Liste lista); //	APRO UNA SPECIFICA LISTA
    public void deleteVideogiocoInList(Liste lista,Videogioco videogioco); // ELIMINO UN FILM DA UNA LISTA 
    public boolean existVideogiocoInListe(Liste lista, Videogioco videogioco); //CONTROLLO SE UN FILM APPARTIENE ALLA LISTA

}
