package persistence.dao.jdbc;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.project.model.Videogioco;
import com.project.model.Liste;
import persistence.dao.ListeDAO;
import com.project.persistence.DBSource;

public class ListeDAOJDBC implements ListeDAO{

	private DBSource dbSource;
	
	public ListeDAOJDBC(DBSource dbSource) {
		this.dbSource= dbSource;
	}
	
	@Override
    public void insertList(Liste lista, Videogioco videogioco)// inserisco un nuovo videogioco nella lista (le liste possono essere: preferiti / watchlist
    {
        Connection conn;
        try {
    			conn = dbSource.getConnection(); //utilizzo la connessione singleton con il db ed eseguo la query sottostante
    			String query = "INSERT INTO public.videogiochiinliste (videogioco, username, nome) VALUES (?,?,?);"; 
                PreparedStatement st = conn.prepareStatement(query);
                st.setString(2, lista.getUsername());
                st.setString(3, lista.getNome());
                st.setInt(1, videogioco.getId());
                st.executeUpdate();//eseguo query
              //chiudo tutte le varie connessioni
                st.close();
                conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	@Override
    public List <Videogioco> OpenList(Liste lista) // apro una lista di un utente contenente vari videogioco
    {
		List<Videogioco> videogiochi= new ArrayList<Videogioco>(); //creo una lista di oggetti videogioco
        try {
			Connection con = dbSource.getConnection(); //utilizzo la connessione singleton con il db ed eseguo la query sottostante
			
            String query = "SELECT f.* FROM public.videogiochi INNER JOIN public.videogiochiinliste fl ON f.id = fl.videogioco WHERE fl.username = ? AND fl.nome = ?";
            PreparedStatement st = con.prepareStatement(query);
            st.setString(2,lista.getNome());
            st.setString(1, lista.getUsername());
            ResultSet rs = st.executeQuery(); //eseguo query
            while (rs.next()) {
				Videogioco videogioco = new Videogioco (rs.getInt("id"),rs.getString("titolo"),rs.getString("descrizione"),rs.getString("genere"),
						rs.getInt("durata"),rs.getInt("anno"),rs.getInt("valutazione"),rs.getString("img"),rs.getString("trailer"));
                videogiochi.add(videogioco); //aggiungo i singoli videogioco alla lista
            }
          
          //chiudo tutte le varie connessioni
            rs.close();
            st.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return videogiochi; //ritorno la lista di oggetti popolata con i videogioco.
    }
 
   	

	@Override
	public void deleteVideogiocoInList(Liste lista,Videogioco videogioco) { // elimino un determinato videogioco da una specifica lista

        try {
        	Connection con = dbSource.getConnection(); //utilizzo la connessione singleton con il db ed eseguo la query sottostante
            String query = "delete from videogiochiinliste WHERE nome =? and videogioco = ? and username=?";
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1, lista.getNome());
            st.setInt(2, videogioco.getId());
            st.setString(3, lista.getUsername());
            st.executeUpdate();//eseguo query
          //chiudo tutte le varie connessioni
            st.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	@Override
    public boolean existVideogiocoInListe(Liste lista, Videogioco videogioco) { // controllo se un videogioco è contenuto in una lista di uno specifico utente
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
        	conn = dbSource.getConnection(); //utilizzo la connessione singleton con il db ed eseguo la query sottostante
            String query = "SELECT * FROM videogiochiinliste WHERE nome = ? AND videogioco = ? AND username = ?";
            st = conn.prepareStatement(query);
            st.setString(1, lista.getNome());
            st.setInt(2, videogioco.getId());
            st.setString(3, lista.getUsername());
            rs = st.executeQuery(); //eseguo query

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


}
