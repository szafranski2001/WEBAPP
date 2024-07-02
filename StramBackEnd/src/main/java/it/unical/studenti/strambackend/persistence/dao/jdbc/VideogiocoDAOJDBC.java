package it.unical.studenti.strambackend.persistence.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import it.unical.studenti.strambackend.persistence.Model.Videogioco;
import it.unical.studenti.strambackend.persistence.DBSource;
import it.unical.studenti.strambackend.persistence.dao.VideogiocoDAO;
import it.unical.studenti.strambackend.persistence.exceptions.DatabaseException;

public class VideogiocoDAOJDBC implements VideogiocoDAO {
	DBSource dbSource;
	
	public VideogiocoDAOJDBC(DBSource dbSource) {
		this.dbSource= dbSource;
	}

	@Override
	public void save(Videogioco videogioco) throws DatabaseException { //salvo nel DB un nuovo videogiocoo
		Connection conn;
		try {
			conn = dbSource.getConnection(); //utilizzo la connessione singleton con il db ed eseguo la query sottostante
			String query = "INSERT INTO public.videogiochi (titolo, descrizione, genere, durata, anno, valutazione, trailer, casamadre) values(?,?,?,?,?,?,?,?);";
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, videogioco.getTitolo());
			st.setString(2, videogioco.getDescrizione());
			st.setString(3, videogioco.getGenere());
			st.setInt(4, videogioco.getDurata());
			st.setInt(5, videogioco.getAnno());
			st.setInt(6, 0);
			st.setString(7, videogioco.getTrailer());
			st.setString(8,videogioco.getCasaP()	);
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
	public Videogioco findByPrimaryKey(int idVideogioco) throws DatabaseException{ //restituisco l'oggetto videogioco con tutti i dati utilizzando il suo id
		try {
			Connection conn = dbSource.getConnection(); //utilizzo la connessione singleton con il db ed eseguo la query sottostante
			String query = "select * from videogiochi where id=?";
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, idVideogioco);
			ResultSet rs = st.executeQuery(); //eseguo query
			Videogioco videogioco=null;
			if (rs.next()) { //creo l'oggetto videogiocoo 
				 videogioco = new Videogioco (rs.getInt("id"),rs.getString("titolo"),rs.getString("descrizione"),rs.getString("genere"),
				rs.getInt("durata"),rs.getInt("anno"),rs.getInt("valutazione"),rs.getString("trailer"),rs.getString("casamadre"));
				}
			//chiudo tutte le varie connessioni
			rs.close();
			st.close();
			conn.close();
			return videogioco; //restituisco l'oggetto videogiocoo
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatabaseException();
		}
	}

	@Override
	public List<Videogioco> findAll() throws DatabaseException { //cerco tutti i videogiocoo presenti nel DB e li inserisco in una lista di oggetti "videogiocoo"
		List<Videogioco> videogiochi = new ArrayList<Videogioco>();
		try {
			Connection con = dbSource.getConnection();//utilizzo la connessione singleton con il db ed eseguo la query sottostante
			String query = "select * from videogiochi";
			PreparedStatement st = con.prepareStatement(query);
			ResultSet rs = st.executeQuery(); //eseguo query
			while (rs.next()) {
				Videogioco videogioco = new Videogioco (rs.getInt("id"),rs.getString("titolo"),rs.getString("descrizione"),rs.getString("genere"),
				rs.getInt("durata"),rs.getInt("anno"),rs.getInt("valutazione"),rs.getString("trailer"),rs.getString("casamadre")); //creo l'oggetto videogiocoo
				videogiochi.add(videogioco);				// inserisco il videogiocoo nella lista di oggetti videogiocoo
			}
			//chiudo tutte le varie connessioni
			rs.close();
			st.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatabaseException();
		}
		return videogiochi; //restituisco la lista
	}

	 @Override
	    public void delete(int videogiocoId) throws DatabaseException { //elimino un videogiocoo dal catalogo dei videogiocoo

	        try {
	            Connection con = dbSource.getConnection();//utilizzo la connessione singleton con il db ed eseguo la query sottostante
	            // ELIMINO TUTTI I LIKES
	            String query = "DELETE FROM recensioni_likes WHERE  videogioco = ?";
	            PreparedStatement st = con.prepareStatement(query);
	            st.setInt(1, videogiocoId);
	            st.executeUpdate();
	            st.close();

	            // ELIMINO TUTTE LE SEGNALAZIONI
	            query = "DELETE FROM segnalazioni WHERE videogioco = ?";
	            st = con.prepareStatement(query);
	            st.setInt(1, videogiocoId);
	            st.executeUpdate();
	            st.close();

	            // ELIMINO LE RECENSIONI
	            query = "DELETE FROM recensioni WHERE videogioco = ?";
	            st = con.prepareStatement(query);
	            st.setInt(1, videogiocoId);
	            st.executeUpdate();
	            st.close();

	            //ELIMINO I VIDEOGIOCHI NELLE LISTE
	            query = "delete from videogiochiinliste WHERE videogioco = ? ";
	            st = con.prepareStatement(query);
	            st.setInt(1, videogiocoId);
	            st.executeUpdate();
	            st.close();

	            //ELIMINO IL VIDEOGIOCO
	            query = "delete from videogiochi WHERE id = ?";
	            st = con.prepareStatement(query);
	            st.setInt(1, videogiocoId);
	            st.executeUpdate();
	        	//chiudo tutte le varie connessioni
	            st.close();
	            con.close();

	        } catch (SQLException e) {
	            e.printStackTrace();
				throw new DatabaseException();
	        }
	}

	@Override
	public List<Videogioco> findByName(String videogioco) throws DatabaseException { //??
		List<Videogioco> videogiochi = new ArrayList <Videogioco>();
		try {
			Connection con = dbSource.getConnection();//utilizzo la connessione singleton con il db ed eseguo la query sottostante
			String query = "select id from videogiochi where titolo=? group by id;";
			PreparedStatement st = con.prepareStatement(query);
			st.setString(1, videogioco);
			ResultSet rs = st.executeQuery(); //eseguo query
			while (rs.next()) {
				Videogioco videogiocoo = findByPrimaryKey(rs.getInt("id"));
				videogiochi.add(videogiocoo);			
			}
			//chiudo tutte le varie connessioni
			rs.close();
			st.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DatabaseException();
		}
		return videogiochi;
	}
	
	@Override
	public List<Videogioco> risultati(String input) { //cerco i videogiocoo nei quali il titolo/anno/genere rispecchia la stringa input e restituisco la lista di oggetti videogioco
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
						rs.getInt("durata"),rs.getInt("anno"),rs.getInt("valutazione"),rs.getString("trailer"),rs.getString("casamadre")); //creo un oggetto videogiocoo
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
	@Override
	public boolean existsVideogioco(Videogioco videogioco) { //controllo se un videogioco esiste nel DB
		Connection con=null;
		PreparedStatement st=null;
		ResultSet rs=null;
		try {
			
			 con = dbSource.getConnection();//utilizzo la connessione singleton con il db ed eseguo la query sottostante
			String query = "select * from videogiochi where id=?";
			st = con.prepareStatement(query);
			st.setInt(1, videogioco.getId());
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
	@Override
	public void updateVideogioco(Videogioco videogioco, int videogiocoId) throws DatabaseException { //aggiorno i dati di un videogiocoo esistente e resituisco true se la modifica è avvenuta con successo
		Connection conn = null;
		try {
			conn = this.dbSource.getConnection();//utilizzo la connessione singleton con il db ed eseguo la query sottostante
			String query = "update videogiochi SET titolo = ?, descrizione = ?, genere = ?, durata = ?, anno = ? WHERE id=?";
			PreparedStatement st = conn.prepareStatement(query);
			
			st.setString(1, videogioco.getTitolo());
			st.setString(2, videogioco.getDescrizione());
			st.setString(3, videogioco.getGenere());
			st.setInt(4, videogioco.getDurata());
			st.setInt(5, videogioco.getAnno());
			st.setInt(6, videogiocoId);
			st.executeUpdate(); //eseguo query
			//chiudo tutte le varie connessioni
			st.close();
			conn.close();
			
		} catch (SQLException e) {
			throw new DatabaseException();		 // In caso di eccezione, ritorno false
		}
	}
	
	@Override
	public String findTitoloById(int videogioco) { { //restituisco il titolo del videogiocoo in base al suo id
        try {
            Connection conn = dbSource.getConnection();//utilizzo la connessione singleton con il db ed eseguo la query sottostante
            String query = "SELECT titolo FROM videogiochi WHERE id=?";
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1, videogioco); 
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
        return null; 
	}//altrimenti null
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


	@Override
	public List<Videogioco> top10() {
		List<Videogioco> res = new LinkedList<>();
		try {
			Connection conn = dbSource.getConnection();
			String query = "SELECT * FROM videogiochi ORDER BY valutazione DESC LIMIT 10;";
			PreparedStatement st = conn.prepareStatement(query);
			ResultSet rs = st.executeQuery(); //eseguo query
			while (rs.next()) {
				res.add(
						new Videogioco(rs.getInt("id"),
								rs.getString("titolo"),
								rs.getString("descrizione"),
								rs.getString("genere"),
								rs.getInt("durata"),
								rs.getInt("anno"),
								rs.getInt("valutazione"),
								rs.getString("trailer"),
								rs.getString("casamadre")
						)
				);
			}
			//chiudo tutte le varie connessioni
			rs.close();
			st.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	//titolo, descrizione, genere, durata, anno, valutazione, trailer, casamadre

	@Override
	public List<Videogioco> get10() {
		List<Videogioco> res = new LinkedList<>();

		try {
			Connection conn = dbSource.getConnection();
			String query =
					"WITH RankedGames AS (select *, ROW_NUMBER() OVER (PARTITION BY genere ORDER BY valutazione DESC) AS rn\n" +
					"    from videogiochi ) select * from RankedGames where rn <= 10 ORDER by genere, rn;";

			PreparedStatement st = conn.prepareStatement(query);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				res.add(
						new Videogioco(rs.getInt("id"),
								rs.getString("titolo"),
								rs.getString("descrizione"),
								rs.getString("genere"),
								rs.getInt("durata"),
								rs.getInt("anno"),
								rs.getInt("valutazione"),
								rs.getString("trailer"),
								rs.getString("casamadre")
						)
				);
			}

			rs.close();
			st.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public Videogioco getRandomVideogame() {

		Videogioco res = null;

		try {
			Connection conn = dbSource.getConnection();
			String query = "SELECT * FROM videogiochi ORDER BY RANDOM() LIMIT 1;";

			PreparedStatement st = conn.prepareStatement(query);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				res = new Videogioco(rs.getInt("id"),
								rs.getString("titolo"),
								rs.getString("descrizione"),
								rs.getString("genere"),
								rs.getInt("durata"),
								rs.getInt("anno"),
								rs.getInt("valutazione"),
								rs.getString("trailer"),
								rs.getString("casamadre")
				);
			}
			rs.close();
			st.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

}
