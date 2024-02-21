package com.project.persistence;

import persistence.dao.VideogiocoDAO;
import persistence.dao.ListeDAO;
import persistence.dao.RecensioneDAO;
import persistence.dao.SegnalazioniDAO;
import persistence.dao.UserDAO;
import persistence.dao.jdbc.VideogiocoDAOJDBC;
import persistence.dao.jdbc.ListeDAOJDBC;
import persistence.dao.jdbc.RecensioneDAOJDBC;
import persistence.dao.jdbc.SegnalazioniDAOJDBC;
import persistence.dao.jdbc.UserDAOJDBC;

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
