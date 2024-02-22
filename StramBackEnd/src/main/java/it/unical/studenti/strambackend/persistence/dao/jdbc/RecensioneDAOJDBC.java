package it.unical.studenti.strambackend.persistence.dao.jdbc;

import it.unical.studenti.strambackend.persistence.Model.Likeato;
import it.unical.studenti.strambackend.persistence.Model.Recensione;
import it.unical.studenti.strambackend.persistence.DBSource;
import it.unical.studenti.strambackend.persistence.dao.RecensioneDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecensioneDAOJDBC implements RecensioneDAO{
	DBSource dbSource;
	
	public RecensioneDAOJDBC(DBSource dbSource) {
		this.dbSource= dbSource;
	}
	
	@Override
	public void save(int videogioco, String username, Integer voto, String commento) { //salvo una nuova recensione associata ad un user ed un videogioco
		Connection conn;
		try {
			conn = dbSource.getConnection(); //utilizzo la connessione singleton con il db
			String query = "INSERT INTO public.recensioni (videogioco, username, voto, commento) values(?,?,?,?);";
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, videogioco);
			st.setString(2, username);
			st.setInt(3, voto);
			st.setString(4, commento);
			st.executeUpdate(); //eseguo query
			//chiudo tutte le varie connessioni
			st.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Recensione findByPrimaryKey(String username, int videogioco) { //cerco nel db una recensione scritta da un utente in uno specifico videogioco
		Recensione recensione = null;
		try {
			Connection conn = dbSource.getConnection(); //utilizzo la connessione singleton con il db
			String query = "select * from recensioni where username=? and videogioco=?";
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, username);
			st.setInt(2, videogioco);
			
			ResultSet rs = st.executeQuery();
			if (rs.next()) { //rs.next() ci da true se abbiamo almeno un elemento, false altrimenti
				recensione = new Recensione (rs.getString("username"),rs.getString("commento"),rs.getInt("voto"),rs.getInt("videogioco"),rs.getInt("likes"));
				}
			//chiudo tutte le varie connessioni
			rs.close();
			st.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return recensione;	
	}

	@Override
	public List<Recensione> findAll(String username) { //cerco tutte le recensioni scritte in un determinato videogioco e restituisco una lista di oggetti "recensione"
		List<Recensione> recensioni = new ArrayList<Recensione>();
		try {
			Connection con = dbSource.getConnection(); //utilizzo la connessione singleton con il db
			String query = "select * from recensioni";
			PreparedStatement st = con.prepareStatement(query);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				Recensione recensione = new Recensione(rs.getString("username"),rs.getString("commento"),rs.getInt("voto"),rs.getInt("videogioco"), rs.getInt("likes"));
				recensioni.add(recensione);				
			}
			//chiudo tutte le varie connessioni
			rs.close();
			st.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		
		}
		//ordino la lista di oggetti recensioni in modo da avere prima quelle con più likes
		Collections.sort(recensioni, (r1, r2) -> Integer.compare(r2.getLikes(), r1.getLikes()));
		return recensioni;
	}
	
	// Metodo per trovare una recensione specifica
	private Recensione trovaRecensioneSpecificata(List<Recensione> recensioni, String username) {
	    for (int i = recensioni.size() - 1; i >= 0; i--) {
	        Recensione recensione = recensioni.get(i);
	        if (recensione.getUsername().equals(username)) {
	            return recensione;
	        }
	    }
	    return null; // Restituisci null se la recensione specifica non è presente
	}

	@Override
	public void delete(String username, int videogioco) { //elimino una recensiosione da un videogioco
	    Connection con = null;
	    try {
	        con = this.dbSource.getConnection(); //utilizzo la connessione singleton con il db

	        // ELIMINO TUTTI I LIKES
	        String query = "DELETE FROM recensioni_likes WHERE usernamedestinatario = ? AND videogioco = ?";
	        PreparedStatement st = con.prepareStatement(query);
	        st.setString(1, username);
	        st.setInt(2, videogioco);
	        st.executeUpdate();
	        st.close();

	        // ELIMINO TUTTE LE SEGNALAZIONI
	        query = "DELETE FROM segnalazioni WHERE destinatario = ? AND videogioco = ?";
	        st = con.prepareStatement(query);
	        st.setString(1, username);
	        st.setInt(2, videogioco);
	        st.executeUpdate();
	        st.close();

	        // ELIMINO LA RECENSIONE
	        query = "DELETE FROM recensioni WHERE username = ? AND videogioco = ?";
	        st = con.prepareStatement(query);
	        st.setString(1, username);
	        st.setInt(2, videogioco);
	        st.executeUpdate();
	      //chiudo tutte le varie connessioni
	        st.close();
	        con.close();
	    } catch (SQLException e) {
	        throw new RuntimeException(e.getMessage());
	    }
	}


	@Override
	public boolean existsRecensione(String username, int videogioco) { //controllo se una recensione ad un videogioco fatta da un utete esiste
			Connection conn = null;
			PreparedStatement st= null;
			ResultSet rs=null;
		try {
			conn = dbSource.getConnection(); //utilizzo la connessione singleton con il db
			String query = "select * from recensioni where username=? and videogioco=?";
			st = conn.prepareStatement(query);
			st.setString(1, username);
			st.setInt(2, videogioco);
			rs = st.executeQuery();
			return rs.next(); //rs.next() ci da true se abbiamo almeno un elemento, false altrimenti
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally { //chiudo tutte le varie connessioni
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
	public Integer mediaVoti(int videogioco) { //calcolo la media  di valutazione delle varie recensioni
		int media = 0;
		try {
			Connection conn = dbSource.getConnection(); //utilizzo la connessione singleton con il db
			String query = "SELECT AVG(voto) AS average_rating FROM recensioni WHERE videogioco=?";
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, videogioco);
			
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				media = rs.getInt("average_rating");
			}
			//chiudo tutte le varie connessioni
			rs.close();
			st.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	

		return media;
		
	}

	@Override
	public List<Recensione> findByVideogioco(int videogioco, String username) { //cerco le recensioni di un determinato videogioco e restituisco una lista di oggetti "recensione"
		List<Recensione> recensioni = new ArrayList<Recensione>();
		try {
			Connection con = dbSource.getConnection(); //utilizzo la connessione singleton con il db
			String query = "select * from recensioni where videogioco=?";
			PreparedStatement st = con.prepareStatement(query);
			st.setInt(1,videogioco);
			ResultSet rs = st.executeQuery(); //eseguo query
			while (rs.next()) {
				Recensione recensione = new Recensione(rs.getString("username"),rs.getString("commento"),rs.getInt("voto"),rs.getInt("videogioco"), rs.getInt("likes"));
				recensioni.add(recensione);			//aggiungo la recensione alla lista di oggetti "recensione"	
			}
			rs.close();
			st.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//ordino la lista di oggetti recensioni in modo da avere prima quelle con più likes
		Collections.sort(recensioni, (r1, r2) -> Integer.compare(r2.getLikes(), r1.getLikes())); //
		//se l'utente ha scritto una recensione in quel videogioco verrà messa in cima
		if (recensioni.size()>0)
		{
			//ordino la lista di oggetti recensioni in modo da avere prima quelle con più likes
			Collections.sort(recensioni, (r1, r2) -> Integer.compare(r2.getLikes(), r1.getLikes())); //
			Recensione recensioneSpecificata = trovaRecensioneSpecificata(recensioni,username);
			//se l'utente ha scritto una recensione in quel videogioco verrà messa in cima
			if (recensioneSpecificata != null) {
		        recensioni.remove(recensioneSpecificata);
		        recensioni.add(0, recensioneSpecificata);

		    }
		}
		return recensioni;
	}
	
	@Override
	public void addOrRemoveLike(Likeato liked, int change) { //aggiungo o rimuovo il like da una recensione

	    try {
	        Connection conn = dbSource.getConnection(); //utilizzo la connessione singleton con il db
	        String query = "select likes from recensioni where username=? and videogioco=?";
	        PreparedStatement st = conn.prepareStatement(query);
	        st.setString(1, liked.getUsernameDestinatario());
	        st.setInt(2, liked.getIdVideogioco());
	        ResultSet rs = st.executeQuery(); //eseguo la query
	        int likes = 0;
	        if (rs.next()) {
	            likes = rs.getInt("likes");
	        } //prendo il numero di likes che ha una recensione
	        rs.close();	 
	        likes += change; //aggiungo la differenza
	        st.close();
	        query = "update recensioni SET likes = ? WHERE username=? and videogioco=?"; //aggiorno la quantità di likes
	        st = conn.prepareStatement(query);
	        st.setInt(1, likes);
	        st.setString(2, liked.getUsernameDestinatario());
	        st.setInt(3, liked.getIdVideogioco());
	        st.executeUpdate();
	        st.close();

	        if (change == 1) { //controllo se la funzione è stata richiamata per aggiungere o togliere il like
	            query = "insert into recensioni_likes (usernameMittente, videogioco, usernameDestinatario) values (?,?,?)"; // se aggiungo inserico il like nella tabella recensioni_likes
	        } else {
	            query = "DELETE FROM recensioni_likes WHERE usernameMittente = ? and  videogioco =? and usernameDestinatario=?"; // altrimenti rimuovo il like dalla tabella 
	        }
	        st = conn.prepareStatement(query);
	        st.setString(1, liked.getUsernameMittente());
	        st.setInt(2, liked.getIdVideogioco());
	        st.setString(3, liked.getUsernameDestinatario());
	        st.executeUpdate(); //eseguo la query

			//chiudo tutte le varie connessioni
	        st.close();
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	}


	@Override
	public List<Likeato> findLikes(String user) { //controllo tutti i like messi da uno specifico utente e restituisco una lista di oggetti "likeati"
		List<Likeato> likeati = new ArrayList<Likeato>();
		try {
			Connection con = dbSource.getConnection(); //utilizzo la connessione singleton con il db
			String query = "select * from recensioni_likes where usernameMittente=?";
			PreparedStatement st = con.prepareStatement(query);
			st.setString(1,user);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				Likeato like = new Likeato(rs.getString("usernameMittente"),rs.getInt("videogioco"),rs.getString("usernameDestinatario"));		// creo l'oggetto likeato
				likeati.add(like);		// aggiungo l'oggetto alla lista	
			}
			//chiudo tutte le varie connessioni
			rs.close();
			st.close();
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return likeati; //ritorno la lista likeati
	}

	
}
