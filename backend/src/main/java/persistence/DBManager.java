package com.project.persistence;

import com.project.persistence.dao.VideogiocoDAO;
import com.project.persistence.dao.ListeDAO;
import com.project.persistence.dao.RecensioneDAO;
import com.project.persistence.dao.SegnalazioniDAO;
import com.project.persistence.dao.UserDAO;
import com.project.persistence.dao.jdbc.VideogiocoDAOJDBC;
import com.project.persistence.dao.jdbc.ListeDAOJDBC;
import com.project.persistence.dao.jdbc.RecensioneDAOJDBC;
import com.project.persistence.dao.jdbc.SegnalazioniDAOJDBC;
import com.project.persistence.dao.jdbc.UserDAOJDBC;

public class DBManager {

	private static DBManager instance = null;
	static DBSource dataSource;
	
	static {
		try {
			Class.forName("org.postgresql.Driver");
			dataSource = new DBSource("jdbc:postgresql://localhost:5432/postgres","postgres","postgres");
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
		return new VideogiocoDAOJDBC(dataSource);
	}
	
	public RecensioneDAO recensioneDAO() {
		return new RecensioneDAOJDBC(dataSource);
	}
		public ListeDAO listeDAO()
	{
		return new ListeDAOJDBC(dataSource);
	}
		public SegnalazioniDAO segnalazioniDAO()
	{
		return new SegnalazioniDAOJDBC(dataSource);
	}
}
