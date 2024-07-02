package it.unical.studenti.strambackend.persistence.dao.jdbc;

import it.unical.studenti.strambackend.persistence.DBSource;
import it.unical.studenti.strambackend.persistence.Model.Lists;
import it.unical.studenti.strambackend.persistence.Model.User;
import it.unical.studenti.strambackend.persistence.dao.UserDAO;
import it.unical.studenti.strambackend.persistence.exceptions.DatabaseException;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UserDAOJDBC implements UserDAO {
	DBSource dbSource;

	public UserDAOJDBC(DBSource dbSource) {
		this.dbSource = dbSource;
	}

	@Override
	public boolean existsUser(String username) { //controllo se un username è presente del DB
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			conn = dbSource.getConnection(); //utilizzo la connessione singleton con il db ed eseguo la query sottostante
			String query = "select * from users where username=?";
			st = conn.prepareStatement(query);
			st.setString(1, username);
			rs = st.executeQuery();
			return rs.next(); //rs.next() ci da true se abbiamo almeno un elemento, false altrimenti

		} catch (SQLException e) {
			e.printStackTrace();
			return false; // In caso di eccezione, ritorno false
		} finally {            //chiudo tutte le varie connessioni
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
	public boolean checkPassword(String username, String password) { //controllo se la password inserita dall'utente è corretta
		String password_hash = null;

		try {
			Connection conn = dbSource.getConnection(); //utilizzo la connessione singleton con il db ed eseguo la query sottostante
			String query = "select password from users where username=?";
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, username);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				password_hash = rs.getString("password");
			}
			//chiudo tutte le varie connessioni
			rs.close();
			st.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return BCrypt.checkpw(password, password_hash); //restituisco true se la password inserita combacia con quella salvata
	}


	@Override
	public User findByPrimaryKey(String username) { //creo un oggetto user utilizzando la chiave username
		User user = null;

		try {
			Connection conn = dbSource.getConnection(); //utilizzo la connessione singleton con il db ed eseguo la query sottostante
			String query = "select * from users where username=?";
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, username);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				user = new User(rs.getString("username"), rs.getString("password"), rs.getString("email"), rs.getString("nome"), rs.getString("cognome"),
						rs.getString("domanda"), rs.getString("risposta")); //instanzio l'oggetto user con tutti i dati
			}
			//chiudo tutte le varie connessioni
			rs.close();
			st.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user; //restituisco l'oggetto user
	}

	@Override
	public boolean save(User user) { //salvo nel DB un nuovo user
		Connection conn;

		try {
			conn = dbSource.getConnection(); //utilizzo la connessione singleton con il db ed eseguo la query sottostante

			String query = "INSERT INTO public.users (username, password, nome, cognome, email, tipo, domanda, risposta) VALUES(?,?,?,?,?,?,?,?);";
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, user.getUsername());
			st.setString(2, BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12))); //crittografo la password	
			st.setString(3, user.getNome());
			st.setString(4, user.getCognome());
			st.setString(5, user.getEmail());
			st.setInt(6, 0);
			st.setString(7, user.getDomanda());
			st.setString(8, BCrypt.hashpw(user.getRisposta(), BCrypt.gensalt(12)));//crittografo la risposta di sicurezza

			st.executeUpdate(); //eseguo la query per salvare nel db l'user appena creato
			//chiudo tutte le varie connessioni
			st.close();
			conn.close();

		} catch (SQLException e) {

			return false; // In caso di eccezione, ritorno false

		}

		return true; //in caso di riuscita restituisco true
	}


	@Override
	public boolean update(User old, User newu) { //aggiorno i dati relativi ad un user
		try {
			Connection conn = this.dbSource.getConnection(); //utilizzo la connessione singleton con il db ed eseguo la query sottostante
			PreparedStatement st;
			if(newu.getPassword().isEmpty()) {
				String update = "update users SET nome = ?, cognome = ?, email = ? WHERE username=?";
				st = conn.prepareStatement(update);
				st.setString(1, newu.getNome());
				st.setString(2, newu.getCognome());
				st.setString(3, newu.getEmail());
				st.setString(4, old.getUsername());
				st.executeUpdate(); // aggiorno i vari paramentri
				//chiudo tutte le varie connessioni
			}
			else {
				String update = "update users SET password = ?, nome = ?, cognome = ?, email = ? WHERE username=?";
				st = conn.prepareStatement(update);
				st.setString(1, BCrypt.hashpw(newu.getPassword(), BCrypt.gensalt(12)));
				st.setString(2, newu.getNome());
				st.setString(3, newu.getCognome());
				st.setString(4, newu.getEmail());
				st.setString(5, old.getUsername());
				st.executeUpdate(); // aggiorno i vari paramentri
				//chiudo tutte le varie connessioni
			}
			st.close();
			conn.close();

			return true; //restutuisco true se il BD è stato aggirnato

		} catch (SQLException e) {
			return false;    // In caso di eccezione, ritorno false
		}

	}

	@Override
	public boolean existsUserEmail(String email) { //cotrollo se esiste un'email nel DB
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			conn = dbSource.getConnection(); //utilizzo la connessione singleton con il db ed eseguo la query sottostante
			String query = "select * from users where email=?";
			st = conn.prepareStatement(query);
			st.setString(1, email);
			rs = st.executeQuery();
			return (rs.next());     //rs.next() ci da true se abbiamo almeno un elemento, false altrimenti

		} catch (SQLException e) {
			e.printStackTrace();
			return false;// In caso di eccezione, ritorno false
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
	public void setPassword(String username, String password) { //aggiorno la password di un utente

		try {
			Connection conn = this.dbSource.getConnection(); //utilizzo la connessione singleton con il db ed eseguo la query sottostante
			String update = "update users SET password=? WHERE email=?";
			PreparedStatement st = conn.prepareStatement(update);

			st.setString(1, BCrypt.hashpw(password, BCrypt.gensalt(12)));    //crittografo la password
			st.setString(2, username);
			st.executeUpdate();
			//chiudo tutte le varie connessioni
			st.close();
			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public List<User> findAll() { //prendo tutti gli user presenti nel DB e li aggiungo alla lista di oggetti "user"
		List<User> utenti = new ArrayList<User>();

		try {
			Connection conn = dbSource.getConnection();
			String query = "select * from users WHERE username != ?";
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, "admin");
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				User user = new User(rs.getString("username"), rs.getString("password"), rs.getString("email"), rs.getString("nome"), rs.getString("cognome"),
						rs.getString("domanda"), rs.getString("risposta")); //creo l'oggetto user
				utenti.add(user); //aggiungo l'user creato nella lista di oggetti user
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return utenti; //restituisco la lista di user
	}

	@Override
	public void delete(String username) throws DatabaseException { //elimino un user
		Connection connection = null;

		try {
			User user = findByPrimaryKey(username);
			//elimino tutte i like dalle recensioni
			connection = this.dbSource.getConnection(); //utilizzo la connessione singleton con il db ed eseguo la query sottostante
			String query = "delete from recensioni_likes WHERE usernameDestinatario = ? or usernameMittente=?";
			PreparedStatement st = connection.prepareStatement(query);
			st.setString(1, user.getUsername());
			st.setString(2, user.getUsername());
			st.executeUpdate();
			st.close();

			//elimino le varie segnalazioni
			query = "delete from segnalazioni WHERE destinatario = ? or mittente = ?";
			st = connection.prepareStatement(query);
			st.setString(1, user.getUsername());
			st.setString(2, user.getUsername());
			st.executeUpdate();
			st.close();


			//elimino tutte le recensioni
			connection = this.dbSource.getConnection();
			query = "delete from recensioni WHERE username = ?";
			st = connection.prepareStatement(query);
			st.setString(1, user.getUsername());
			st.executeUpdate();
			st.close();

			query = "delete from videogiochiinliste WHERE username = ?";
			st = connection.prepareStatement(query);
			st.setString(1, user.getUsername());
			st.executeUpdate();
			st.close();

			//elimino le liste
			query = "delete from liste WHERE username = ?";
			st = connection.prepareStatement(query);
			st.setString(1, user.getUsername());
			st.executeUpdate();
			st.close();

			//elimino user
			query = "delete from users WHERE username = ?";
			st = connection.prepareStatement(query);
			st.setString(1, user.getUsername());
			st.executeUpdate();
			st.close();


			//inserisco email nella black list
			query = "INSERT INTO usersespulsi (email) VALUES (?)";
			st = connection.prepareStatement(query);
			st.setString(1, user.getEmail());
			st.executeUpdate();
			//chiudo tutte le varie connessioni
			st.close();

			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatabaseException();
		}
	}


	@Override
	public boolean bannedEmail(String email) { //controllo se un'email è stata bannata
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			conn = dbSource.getConnection(); //utilizzo la connessione singleton con il db ed eseguo la query sottostante
			String query = "select * from usersespulsi WHERE email = ?";
			st = conn.prepareStatement(query);
			st.setString(1, email);
			rs = st.executeQuery();
			return rs.next(); //rs.next() ci da true se abbiamo almeno un elemento, false altrimenti
		} catch (SQLException e) {
			e.printStackTrace();
			return false;// In caso di eccezione, ritorno false
		} finally {
			try { //chiudo tutte le varie connessioni
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
	public String existsDomanda(String email) { //cotrollo se una domanda di recupero password è presente nel DB
		try {
			Connection conn = dbSource.getConnection(); //utilizzo la connessione singleton con il db
			String query = "SELECT domanda from users WHERE email = ?";
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, email);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				return rs.getString("domanda"); //se rs.next () è true restituisco la domanda di sicurezza
			}
			//chiudo tutte le varie connessioni
			rs.close();
			st.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; // se non esiste restituisco null
	}

	@Override
	public boolean checkRisposta(String email, String risposta) { //controllo se la risposta data corrisponde alla rispota data dall'utente
		String risposta_hash = null;
		try {
			Connection conn = dbSource.getConnection(); //utilizzo la connessione singleton con il db ed eseguo la query sottostante
			String query = "SELECT risposta FROM users WHERE email = ?";
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, email);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				risposta_hash = rs.getString("risposta"); //prendo la risposta dal DB per poi cofrontarla con quella data
			}
			//chiudo tutte le varie connessioni
			rs.close();
			st.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return BCrypt.checkpw(risposta, risposta_hash); //controllo se la risposta data è corretta

	}

	public int getTypeUser(String user) {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			conn = dbSource.getConnection();
			String query = "SELECT * FROM users WHERE username=?";
			st = conn.prepareStatement(query);
			st.setString(1, user);
			rs = st.executeQuery();
			if (rs.next()) {
				return rs.getInt("tipo");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
		return 0;
	}

	@Override
	public void setToken(String user, String token) {
		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = dbSource.getConnection();
			String query = "UPDATE users SET token = ? WHERE username = ?";
			st = conn.prepareStatement(query);
			st.setString(1, token);
			st.setString(2, user);
			st.executeUpdate();

		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
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
	public String getToken(String user) {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			conn = dbSource.getConnection();
			String query = "SELECT token FROM users WHERE username=?";
			st = conn.prepareStatement(query);
			st.setString(1, user);
			rs = st.executeQuery();
			if (rs.next()) {
				return rs.getString("token");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
        return null;
    }



}
