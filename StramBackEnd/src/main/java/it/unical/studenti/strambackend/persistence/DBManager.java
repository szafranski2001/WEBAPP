package it.unical.studenti.strambackend.persistence;

import it.unical.studenti.strambackend.persistence.dao.VideogiocoDAO;
import it.unical.studenti.strambackend.persistence.dao.ListeDAO;
import it.unical.studenti.strambackend.persistence.dao.RecensioneDAO;
import it.unical.studenti.strambackend.persistence.dao.SegnalazioniDAO;
import it.unical.studenti.strambackend.persistence.dao.UserDAO;
import it.unical.studenti.strambackend.persistence.dao.jdbc.*;

public class DBManager {

	private static DBManager instance = null;
	static DBSource dataSource;
	
	static {
		try {
			Class.forName("org.postgresql.Driver");
			dataSource = new DBSource("jdbc:postgresql://localhost:5432/WebApp","postgres","postgres");
		} 
		catch (Exception e) {
			System.err.println("PostgresDAOFactory.class: failed to load MySQL JDBC driver\n"+e);
			e.printStackTrace();
		}
	}
	
	public static DBManager getInstance() {
		if (instance == null) {
			instance = new DBManager();
		}
		return instance;
	}
	
	private DBManager() {
	}
	
	public static DBSource getDataSource() {
		return dataSource;
	}
	
	public UserDAO userDAO() {
		return new UserDAOJDBC(dataSource);
	}
	
	public VideogiocoDAO VideogiocoDAO() {
		return new VideogiocoProxy(dataSource);
	}
	
	public RecensioneDAO recensioneDAO() {
		return new RecensioneDAOJDBC(dataSource);
	}
	public ListeDAO listeDAO()
	{
		return new ListeDAOJDBC(dataSource);
	}
	public SegnalazioniDAO segnalazioniDAO() { return new SegnalazioniDAOJDBC(dataSource); }




}
