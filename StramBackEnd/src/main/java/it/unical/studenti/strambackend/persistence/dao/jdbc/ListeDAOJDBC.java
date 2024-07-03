package it.unical.studenti.strambackend.persistence.dao.jdbc;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

import it.unical.studenti.strambackend.persistence.Model.Videogioco;
import it.unical.studenti.strambackend.persistence.dao.ListeDAO;
import it.unical.studenti.strambackend.persistence.DBSource;
import it.unical.studenti.strambackend.persistence.exceptions.DatabaseException;

public class ListeDAOJDBC implements ListeDAO{

	private DBSource dbSource;
	
	public ListeDAOJDBC(DBSource dbSource) {
		this.dbSource= dbSource;
	}

    public boolean existVideogiocoInUserList(int idVideogioco, String User, String NomeLista){
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            conn = dbSource.getConnection(); //utilizzo la connessione singleton con il db ed eseguo la query sottostante
            String query = "SELECT * FROM videogiochiinliste WHERE nome = ? AND videogioco = ? AND username = ?";
            st = conn.prepareStatement(query);
            st.setString(1, NomeLista);
            st.setInt(2, idVideogioco);
            st.setString(3, User);
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

    public void InsertVideogameInList(int idVideogioco, String User, String NomeLista) throws DatabaseException{
        Connection conn;
        try {
            conn = dbSource.getConnection(); //utilizzo la connessione singleton con il db ed eseguo la query sottostante
            String query = "INSERT INTO public.videogiochiinliste (videogioco, username, nome) VALUES (?,?,?);";
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(2, User);
            st.setString(3, NomeLista);
            st.setInt(1, idVideogioco);
            st.executeUpdate();//eseguo query
            //chiudo tutte le varie connessioni
            st.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException();
        }
    }

    public void RemoveVideogameFromList(int idVideogioco, String User, String NomeLista) throws DatabaseException{
        try {
            Connection con = dbSource.getConnection(); //utilizzo la connessione singleton con il db ed eseguo la query sottostante
            String query = "delete from videogiochiinliste WHERE nome =? and videogioco = ? and username=?";
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1, NomeLista);
            st.setInt(2, idVideogioco);
            st.setString(3, User);
            st.executeUpdate();//eseguo query
            //chiudo tutte le varie connessioni
            st.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException();
        }
    }


    public List<Videogioco> OpenUserList(String NomeLista, String user) // apro una lista di un utente contenente vari videogiochi
    {
        List<Videogioco> videogiochi= new ArrayList<>(); //creo una lista di oggetti videogioco

        if(user != null) {
            try {
                Connection con = dbSource.getConnection(); //utilizzo la connessione singleton con il db ed eseguo la query sottostante

                String query = "SELECT * FROM public.videogiochi f INNER JOIN public.videogiochiinliste fl ON f.id = fl.videogioco WHERE fl.username = ? AND fl.nome = ?";
                PreparedStatement st = con.prepareStatement(query);
                st.setString(2, NomeLista);
                st.setString(1, user);
                ResultSet rs = st.executeQuery(); //eseguo query
                while (rs.next()) {
                    Videogioco game = new Videogioco(
                            rs.getInt("id"),
                            rs.getString("titolo"),
                            rs.getString("descrizione"),
                            rs.getString("genere"),
                            rs.getInt("durata"),
                            rs.getInt("anno"),
                            rs.getInt("valutazione"),
                            rs.getString("trailer"),
                            rs.getString("casamadre")
                    );
                    videogiochi.add(game);
                }

                //chiudo tutte le varie connessioni
                rs.close();
                st.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return videogiochi; //ritorno la lista di oggetti popolata con i videogiochi.
    }



}
