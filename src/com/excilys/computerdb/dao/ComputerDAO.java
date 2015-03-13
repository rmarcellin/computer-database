package com.excilys.computerdb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;

import com.excilys.computerdb.beans.Computer;
import com.excilys.computerdb.exception.DAOException;

public class ComputerDAO {
	
	private RepositoryDAO repository;
	private static final String SQL_SELECT_ALL_COMPUTERS = "SELECT * FROM computer";
	private static final String SQL_SELECT_ONE_COMPUTER = 
			"SELECT * FROM computer WHERE id = ?";
			/*+ "OR name = ?"
			+ "OR company_id = ?"*/
	//private static final int NBR_SQL_GAPS = 3;

	public ComputerDAO(RepositoryDAO repositoryDAO) {
		// TODO Auto-generated constructor stub
		this.repository = repositoryDAO;
	}
	
	public Computer getComputer(long criteria) throws SQLException {
		Computer comp = null;
		ResultSet resultSet = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = repository.getConnection();
			preparedStatement = connection.prepareStatement(SQL_SELECT_ONE_COMPUTER);
			preparedStatement.setObject(1, criteria);
			//System.out.println(preparedStatement.g);
			resultSet = preparedStatement.executeQuery();
			System.out.println("On ici " + resultSet.getCursorName());
			comp = computerMap (resultSet);
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			connection.close();
			preparedStatement.close();
		}
				
		return comp;
	}
	
	public List<Computer> getComputers() throws SQLException {
		List<Computer> listComp = new ArrayList<>();
		Computer computer = null;
		ResultSet resultSet = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = repository.getConnection();
			preparedStatement = connection.prepareStatement(SQL_SELECT_ALL_COMPUTERS);
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				computer = computerMap (resultSet);
				listComp.add(computer);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			connection.close();
			preparedStatement.close();
		}
		
		return listComp;
	}
	
	
	//////////////////////// UTILS ////////////////////////
	private static Computer computerMap (ResultSet resultSet) throws SQLException {
		Computer computer = new Computer();
		computer = new Computer();
		computer.setId(resultSet.getLong("id"));
		computer.setName(resultSet.getString("name"));				
		
		Timestamp tmp1 = resultSet.getTimestamp("introduced");
		if (tmp1 != null) {
			long tstampInt = tmp1.getTime();				
			LocalDate ldInt = new LocalDate(tstampInt);
			computer.setIntroduced(ldInt);
		}
		
		Timestamp tmp2 = resultSet.getTimestamp("discontinued");
		if (tmp2 != null) {
			long tstampDis = tmp2.getTime();
			LocalDate ldDis = new LocalDate(tstampDis);
			computer.setIntroduced(ldDis);
		}				
		
		computer.setCompanyId(resultSet.getLong("company_id"));
		return computer;
	}

}
