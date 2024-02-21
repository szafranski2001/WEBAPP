package persistence.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.project.model.Videogioco;
import com.project.persistence.DBSource;
import persistence.dao.VideogiocoDAO;

public class VideogiocoDAOJDBC implements VideogiocoDAO {
	DBSource dbSource;
	
	public VideogiocoDAOJDBC(DBSource dbSource) {
		this.dbSource= dbSource;
	}

	@Override
	public void save(String titolo, String descrizione, String genere, int durata, int anno, String img, String trailer) { //salvo nel DB un nuovo videogiocoo
		Connection conn;
		try {
			conn = dbSource.getConnection(); //utilizzo la connessione singleton con il db ed eseguo la query sottostante
			String query = "INSERT INTO public.videogiochi (titolo, descrizione, genere, durata, anno, valutazione, img, trailer) values(?,?,?,?,?,?,?,?);";
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, titolo);
			st.setString(2, descrizione);
			st.setString(3, genere);
			st.setInt(4, durata);
			st.setInt(5, anno);
			st.setInt(6, 0);
			st.setString(7, img);
			st.setString(8, trailer);
			st.executeUpdate(); //eseguo query
			//chiudo tutte le varie connessioni
			st.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Videogioco findByPrimaryKey(int idVideogioco) { //restituisco l'oggetto videogiocoo con tutti i dati utilizzando il suo id
		try {
			Connection conn = dbSource.getConnection(); //utilizzo la connessione singleton con il db ed eseguo la query sottostante
			String query = "select * from videogiochi where id=?";
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, idVideogioco);
			ResultSet rs = st.executeQuery(); //eseguo query
			Videogioco videogiocoo=null;
			if (rs.next()) { //creo l'oggetto videogiocoo 
				 videogiocoo = new Videogioco (rs.getInt("id"),rs.getString("titolo"),rs.getString("descrizione"),rs.getString("genere"),
				rs.getInt("durata"),rs.getInt("anno"),rs.getInt("valutazione"),rs.getString("img"),rs.getString("trailer"));
				}
			//chiudo tutte le varie connessioni
			rs.close();
			st.close();
			conn.close();
			return videogiocoo; //restituisco l'oggetto videogiocoo
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; // in caso di errore restutuisco null

	}

	@Override
	public List<Videogioco> findAll() { //cerco tutti i videogiocoo presenti nel DB e li inserisco in una lista di oggetti "videogiocoo"
		List<Videogioco> videogiochi = new ArrayList<Videogioco>();
		try {
			Connection con = dbSource.getConnection();//utilizzo la connessione singleton con il db ed eseguo la query sottostante
			String query = "select * from videogiochi";
			PreparedStatement st = con.prepareStatement(query);
			ResultSet rs = st.executeQuery(); //eseguo query
			while (rs.next()) {
				Videogioco videogiocoo = new Videogioco (rs.getInt("id"),rs.getString("titolo"),rs.getString("descrizione"),rs.getString("genere"),
				rs.getInt("durata"),rs.getInt("anno"),rs.getInt("valutazione"),rs.getString("img"),rs.getString("trailer")); //creo l'oggetto videogiocoo
				videogiochi.add(videogiocoo);				// inserisco il videogiocoo nella lista di oggetti videogiocoo
			}
			//chiudo tutte le varie connessioni
			rs.close();
			st.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return videogiochi; //restituisco la lista
	}

	@Override
	public void update(Videogioco videogiocoo) { //aggiorno i dati di un videogiocoo
		try {
			Connection conn = dbSource.getConnection();//utilizzo la connessione singleton con il db ed eseguo la query sottostante
			String update = "update videogiochi SET valutazione = ? WHERE id=?";
			PreparedStatement st = conn.prepareStatement(update);
			st.setInt(1, videogiocoo.getValutazione());
			st.setInt(2, videogiocoo.getId());
			st.executeUpdate(); //eseguo query
			//chiudo tutte le varie connessioni
			st.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	 @Override
	    public void delete(int idVideogioco) { //elimino un videogiocoo dal catalogo dei videogiocoo

	        try {
	            Connection con = dbSource.getConnection();//utilizzo la connessione singleton con il db ed eseguo la query sottostante
	            // ELIMINO TUTTI I LIKES
	            String query = "DELETE FROM recensioni_likes WHERE  videogiocoo = ?";
	            PreparedStatement st = con.prepareStatement(query);
	            st.setInt(1, idVideogioco);
	            st.executeUpdate();
	            st.close();

	            // ELIMINO TUTTE LE SEGNALAZIONI
	            query = "DELETE FROM segnalazioni WHERE videogiocoo = ?";
	            st = con.prepareStatement(query);
	            st.setInt(1, idVideogioco);
	            st.executeUpdate();
	            st.close();

	            // ELIMINO LE RECENSIONI
	            query = "DELETE FROM recensioni WHERE videogiocoo = ?";
	            st = con.prepareStatement(query);
	            st.setInt(1, idVideogioco);
	            st.executeUpdate();
	            st.close();

	            //ELIMINO I FILM NELLE LISTE
	            query = "delete from videogiocooinliste WHERE videogiocoo = ? ";
	            st = con.prepareStatement(query);
	            st.setInt(1, idVideogioco);
	            st.executeUpdate();
	            st.close();

	            //ELIMINO IL FILM
	            query = "delete from videogiochi WHERE id = ?";
	            st = con.prepareStatement(query);
	            st.setInt(1, idVideogioco);
	            st.executeUpdate();
	        	//chiudo tutte le varie connessioni
	            st.close();
	            con.close();

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	}

	@Override
	public List<Videogioco> findByName(String titolo) { //??
		List<Videogioco> videogiochi = new ArrayList <Videogioco>();
		try {
			Connection con = dbSource.getConnection();//utilizzo la connessione singleton con il db ed eseguo la query sottostante
			String query = "select id from videogiochi where titolo=? group by id;";
			PreparedStatement st = con.prepareStatement(query);
			st.setString(1, titolo);
			ResultSet rs = st.executeQuery(); //eseguo query
			while (rs.next()) {
				Videogioco videogiocoo = findByPrimaryKey(rs.getInt("id"));
				videogiochi.add(videogiocoo);			
			}
			//chiudo tutte le varie connessioni
			rs.close();
			st.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return videogiochi;
	}
	
	@Override
	public List<Videogioco> risultati(String input) { //cerco i videogiocoo nei quali il titolo/anno/genere rispecchia la stringa input e restituisco la lista di oggetti videogiocoo
		List<Videogioco> videogiochi = new ArrayList <Videogioco>();
		try {
			Connection con = dbSource.getConnection();//utilizzo la connessione singleton con il db ed eseguo la query sottostante
			String query = "select *  from videogiochi where (titolo ILIKE ? or genere ILIKE ? or CAST(anno AS TEXT) ILIKE ?)";
			PreparedStatement st = con.prepareStatement(query);
			st.setString(1, input);
			st.setString(2, input);
			st.setString(3, input);
			ResultSet rs = st.executeQuery(); //eseguo query
			while (rs.next()) { 
				Videogioco videogiocoo = new Videogioco (rs.getInt("id"),rs.getString("titolo"),rs.getString("descrizione"),rs.getString("genere"),
						rs.getInt("durata"),rs.getInt("anno"),rs.getInt("valutazione"),rs.getString("img"),rs.getString("trailer")); //creo un oggetto videogiocoo
				videogiochi.add(videogiocoo);	//aggiungo il videogiocoo alla lista di oggetti videogiocoo
			}
			//chiudo tutte le varie connessioni
			rs.close();
			st.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return videogiochi; //restituisco la lista
	}

	public boolean existsVideogioco(int id) { //controllo se un videogiocoo esiste nel DB
		Connection con=null;
		PreparedStatement st=null;
		ResultSet rs=null;
		try {
			
			 con = dbSource.getConnection();//utilizzo la connessione singleton con il db ed eseguo la query sottostante
			String query = "select * from videogiochi where id=?";
			st = con.prepareStatement(query);
			st.setInt(1, id);
			rs = st.executeQuery();//eseguo query
			return rs.next(); //rs.next() ci da true se abbiamo almeno un elemento, false altrimenti
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally { 	//chiudo tutte le varie connessioni
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

	}
	public boolean updateVideogioco(Videogioco oldF, Videogioco newF) { //aggiorno i dati di un videogiocoo esistente e resituisco true se la modifica è avvenuta con successo
		Connection conn = null;
		try {
			conn = this.dbSource.getConnection();//utilizzo la connessione singleton con il db ed eseguo la query sottostante
			String query = "update videogiochi SET titolo = ?, descrizione = ?, genere = ?, durata = ?, anno = ? WHERE id=?";
			PreparedStatement st = conn.prepareStatement(query);
			
			st.setString(1, newF.getTitolo());
			st.setString(2, newF.getDescrizione());
			st.setString(3, newF.getGenere());
			st.setInt(4, newF.getDurata());
			st.setInt(5, newF.getAnno());
			st.setInt(6, oldF.getId());
			st.executeUpdate(); //eseguo query
			//chiudo tutte le varie connessioni
			st.close();
			conn.close();
			return true;
			
		} catch (SQLException e) {
			return false;		 // In caso di eccezione, ritorno false 	
		} 
	}
	
	public String findTitoloById(int idVideogioco) { //restituisco il titolo del videogiocoo in base al suo id
        try {
            Connection conn = dbSource.getConnection();//utilizzo la connessione singleton con il db ed eseguo la query sottostante
            String query = "SELECT titolo FROM videogiochi WHERE id=?";
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1, idVideogioco); 
            ResultSet rs = st.executeQuery(); //eseguo query
            String titolo = null;
            if (rs.next()) {
                titolo = rs.getString("titolo");
            }
        	//chiudo tutte le varie connessioni
            rs.close();
            st.close();
            conn.close();
            return titolo; //restituisco il titolo
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; //altrimenti null
    }

	@Override
	public int lastID() { //controllo qual è stato l'ultimo id inserito e lo restituisco
        try {
            Connection conn = dbSource.getConnection();//utilizzo la connessione singleton con il db ed eseguo la query sottostante
            String query = "SELECT MAX(id) FROM videogiochi;";
            PreparedStatement st = conn.prepareStatement(query);
            ResultSet rs = st.executeQuery(); //eseguo query
            int id = 0;
            if (rs.next()) {
                id = rs.getInt("max");
            }
        	//chiudo tutte le varie connessioni
            rs.close();
            st.close();
            conn.close();
            return id; //restituisco il numero id più alto
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0; //altrimenti restituisco 0
	}

}
