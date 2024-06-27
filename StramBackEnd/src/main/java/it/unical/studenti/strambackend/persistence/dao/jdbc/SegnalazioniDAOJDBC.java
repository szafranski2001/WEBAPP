package it.unical.studenti.strambackend.persistence.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import it.unical.studenti.strambackend.persistence.Model.CompletataSegnalazione;
import it.unical.studenti.strambackend.persistence.Model.InLavorazioneSegnalazione;
import it.unical.studenti.strambackend.persistence.Model.Segnalazioni;
import it.unical.studenti.strambackend.persistence.Model.User;
import it.unical.studenti.strambackend.persistence.DBSource;
import it.unical.studenti.strambackend.persistence.dao.SegnalazioniDAO;
import it.unical.studenti.strambackend.persistence.exceptions.DatabaseException;

public class SegnalazioniDAOJDBC implements SegnalazioniDAO{
	DBSource dbSource;
	public SegnalazioniDAOJDBC(DBSource dbSource) {
		this.dbSource= dbSource;
	}

	@Override
	public void CreateReport(Segnalazioni segnalazione) throws DatabaseException { //creo una nuova segnalazione fa parte di un utente
		Connection conn;
		try {
			conn = dbSource.getConnection(); //utilizzo la connessione singleton con il db ed eseguo la query sottostante
			String query = "INSERT INTO public.segnalazioni (mittente, destinatario, videogioco, stato) values(?,?,?,?);";
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, segnalazione.getMittente());
			st.setString(2, segnalazione.getDestinatario());
			st.setInt(3, segnalazione.getIdVideogioco());
			st.setString(4, segnalazione.getState().statoSegnalazione());
			st.executeUpdate(); //eseguo query
			//chiudo tutte le varie connessioni
			st.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatabaseException();
		}
		
	}

	@Override
	public void DeleteReport(Segnalazioni segnalazione) throws DatabaseException { // elimino una segnalazione fatta da un utente
			Connection conn;
		try {
			conn = dbSource.getConnection(); //utilizzo la connessione singleton con il db ed eseguo la query sottostante
			String query = "delete from segnalazioni WHERE mittente=? and destinatario =? and videogioco = ?";
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, segnalazione.getMittente());
			st.setString(2, segnalazione.getDestinatario());
			st.setInt(3, segnalazione.getIdVideogioco());
			st.executeUpdate(); //eseguo query
			//chiudo tutte le varie connessioni
			st.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatabaseException();
		}
	}


	@Override
	public boolean ExistReport(Segnalazioni segnalazione) { // controllo se una segnalazione è presente nel db 
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
        	conn = dbSource.getConnection(); //utilizzo la connessione singleton con il db ed eseguo la query sottostante
            String query = "SELECT * FROM segnalazioni WHERE mittente = ? AND destinatario = ? AND videogioco = ?";
            st = conn.prepareStatement(query);
            st.setString(1, segnalazione.getMittente());
            st.setString(2, segnalazione.getDestinatario());
            st.setInt(3, segnalazione.getIdVideogioco());
            rs = st.executeQuery();	//eseguo query
            return rs.next(); //rs.next() ci da true se abbiamo almeno un elemento, false altrimenti

        } catch (SQLException e) {
            e.printStackTrace();
            return false; // In caso di eccezione, ritorno false
            
        } finally { //chiudo tutte le varie connessioni
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
	}


	@Override
	public List<Segnalazioni> findAll() { //prendo tutte le segnalazioni ATTIVE e le restituisco in una lista di oggetti "segnalazioni"
	    List<Segnalazioni> segnalazioni = new ArrayList<>();
	    Segnalazioni s = new Segnalazioni(); //creo un oggetto segnalazione per avere lo state "nuovaSegnalazione"
	    try {
	        Connection con = dbSource.getConnection(); //utilizzo la connessione singleton con il db ed eseguo la query sottostante
	        String query = "SELECT * FROM segnalazioni WHERE stato = ? OR stato = ?";
	        PreparedStatement st = con.prepareStatement(query);

	        st.setString(1, s.getState().statoSegnalazione());
	        // Imposta lo stato nella query prima di eseguire la query
	        s.changeState(new InLavorazioneSegnalazione (s));
	        st.setString(2,s.getState().statoSegnalazione());
	        ResultSet rs = st.executeQuery();	//eseguo query

	        while (rs.next()) {
	            Segnalazioni segnalazione = null;
	        	if (rs.getString("stato").equals(s.getState().statoSegnalazione())) //controllo se lo stato della segnalazione è in lavorazione  oppure se è una nuovaSegnalazione
	        	{
		             segnalazione = new Segnalazioni(rs.getString("mittente"), rs.getString("destinatario"), rs.getInt("videogioco")); //creo una nuova segnalazione
		             segnalazione.changeState(new InLavorazioneSegnalazione (segnalazione)); //cambio lo stato in lavorazione,
	        	}
	        	else
	        	{
		             segnalazione = new Segnalazioni(rs.getString("mittente"), rs.getString("destinatario"), rs.getInt("videogioco")); //creo una nuova segnalazione
	        	}
				segnalazioni.add(segnalazione); //aggiungo la segnalazione nuova o in lavorazione alla lista di oggetti "sengalazioni"
	        }
	      //chiudo tutte le varie connessioni
	        rs.close();
	        st.close();
	        con.close();  
	        Collections.reverse(segnalazioni); //inverto la lista così da avere come prime tuple le nuove segnalazioni
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		return segnalazioni; //ritorno la lista di oggetti "segnalazione"

	}
	
	@Override
    public void updateSegnalazione(Segnalazioni segnalazione) throws DatabaseException { //cambio lo stato della segnalazione
        Segnalazioni s = new Segnalazioni(); //creo un oggetto segnalazione così da avere i vari stati.
        
        if (segnalazione.getState().statoSegnalazione().equals(s.getState().statoSegnalazione())) //controllo se la segnalazione è NUOVA o VECCHIA (cioè che è stata già visualizzata da un admin ma non 
        																							// è stata completata) 
        {
            s.changeState(new InLavorazioneSegnalazione (s));

        }
        else
        {
            s.changeState(new CompletataSegnalazione(s));

        }

        try {
            Connection conn = dbSource.getConnection(); //utilizzo la connessione singleton con il db ed eseguo la query sottostante
            String update = "update segnalazioni SET stato = ? WHERE mittente=? and destinatario =? and videogioco =?";
            PreparedStatement st = conn.prepareStatement(update);
            st.setString(1, s.getState().statoSegnalazione());
            st.setString(2, segnalazione.getMittente());
            st.setString(3, segnalazione.getDestinatario());
            st.setInt(4, segnalazione.getIdVideogioco());

            st.executeUpdate(); //eseguo query
          //chiudo tutte le varie connessioni
            st.close();
            conn.close();
        } 
		catch (SQLException e) {
            e.printStackTrace();
			throw new DatabaseException();
        }
	}

		@Override
		public List<Segnalazioni> findSegnalazioniUser(String user,int id) { //controllo tutte le segnalazioni da uno specifico utente e restituisco una lista di oggetti "sengalazioni"
			List<Segnalazioni> segnalazioni = new ArrayList<>();
			try {
				Connection con = dbSource.getConnection(); //utilizzo la connessione singleton con il db
				String query = "select * from segnalazioni where mittente=? and videogioco=?";
				PreparedStatement st = con.prepareStatement(query);
				st.setString(1,user);
				st.setInt(2,id);
				ResultSet rs = st.executeQuery();
				while (rs.next()) {
						Segnalazioni segnalazione = new Segnalazioni(rs.getString("mittente"), rs.getString("destinatario"), rs.getInt("videogioco"));
						segnalazioni.add(segnalazione);
				}
				//chiudo tutte le varie connessioni
				rs.close();
				st.close();
				con.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
			return segnalazioni; //ritorno la lista segnalazioni
		}

}

