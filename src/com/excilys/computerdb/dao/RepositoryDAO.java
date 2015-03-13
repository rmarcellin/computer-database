/**
 * 
 */
package com.excilys.computerdb.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.excilys.computerdb.exception.DAOConfException;

/**
 * @author excilys This class instanciate our various DAO
 */
public class RepositoryDAO {

	private static final String PROPERTIES_FILE = 
				"com/excilys/computerdb/dao/conf/dao.properties";
	private static final String PROPERTY_URL = "url";
	private static final String PROPERTY_DRIVER = "driver";
	private static final String PROPERTY_LOGIN = "login";
	private static final String PROPERTY_PASSWD = "passwd";
	
	private String url;
    private String login;
    private String passwd;
    
	/**
	 * @param url : mysql db url
	 * @param login : login to access the db
	 * @param passwd : password to access the db
	 */
	private RepositoryDAO(String url, String login, String passwd) {
		this.url = url;
		this.login = login;
		this.passwd = passwd;
	}
    
    public static RepositoryDAO getInstance() throws DAOConfException {
    	String url, login, passwd, driver;
    	Properties properties = new Properties();
    	
    	ClassLoader cl = Thread.currentThread().getContextClassLoader();
    	InputStream propertiesFile = cl.getResourceAsStream(PROPERTIES_FILE);
    	
    	if (propertiesFile == null) {
    		throw new DAOConfException("Can't find " + PROPERTIES_FILE + " file");
    	}
    	
    	try {
    		properties.load(propertiesFile);
    		url = properties.getProperty(PROPERTY_URL);
    		login = properties.getProperty(PROPERTY_LOGIN);
    		passwd = properties.getProperty(PROPERTY_PASSWD);
    		driver = properties.getProperty(PROPERTY_DRIVER);
    	} catch (IOException e) {
    		throw new DAOConfException
    				("Can't load " + PROPERTIES_FILE + " properties file");
    	}
    	
    	try {
    		Class.forName(driver);
    	} catch (ClassNotFoundException e) {
    		throw new DAOConfException
    				("Can't load file properties " + PROPERTIES_FILE, e);
    	}
    	
    	RepositoryDAO repository = new RepositoryDAO(url, login, passwd);
    	return repository;
    }
    
    Connection getConnection () throws SQLException {
    	return DriverManager.getConnection(url, login, passwd);
    }

}
