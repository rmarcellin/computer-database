package com.excilys.computerdb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.joda.time.LocalDate;

import com.excilys.computerdb.beans.Computer;
import com.excilys.computerdb.exception.DAOException;

// TODO: Auto-generated Javadoc
/**
 * The Class ComputerDAO.
 */
public class ComputerDAO {
	
	/** The repository. */
	private RepositoryDAO repository;
	
	/** The Constant SQL_SELECT_ALL_COMPUTERS. */
	private static final String SQL_SELECT_ALL_COMPUTERS = 
			"SELECT * FROM computer";
	
	/** The Constant SQL_SELECT_ONE_COMPUTER_BY_ID. */
	private static final String SQL_SELECT_ONE_COMPUTER_BY_ID = 
			"SELECT * FROM computer WHERE id = ?";
	
	/** The Constant SQL_SELECT_ONE_COMPUTER_BY_NAME. */
	private static final String SQL_SELECT_ONE_COMPUTER_BY_NAME = 
			"SELECT * FROM computer WHERE name = ?";
	
	/** The Constant SQL_CREATE_COMPUTER. */
	private static final String SQL_CREATE_COMPUTER = 
			"INSERT INTO computer (name, introduced, discontinued, company_id) "
						+ "VALUES (?, ?, ?, ?)";
	
	/** The Constant SQL_UPDATE_COMPUTER. */
	private static final String SQL_UPDATE_COMPUTER = 
			"UPDATE computer SET name = ?, introduced = ?, "
			+ "discontinued = ?, company_id = ? WHERE id = ?";
	
	/** The Constant SQL_DELETE_COMPUTER. */
	private static final String SQL_DELETE_COMPUTER = 
			"DELETE FROM computer WHERE id = ?";
			

	/**
	 * Instantiates a new computer dao.
	 *
	 * @param repositoryDAO the repository dao
	 */
	public ComputerDAO(RepositoryDAO repositoryDAO) {
		// TODO Auto-generated constructor stub
		this.repository = repositoryDAO;
	}
	
	/**
	 * Delete computer.
	 *
	 * @param compId the comp id
	 * @return the computer
	 * @throws SQLException the SQL exception
	 */
	public Computer deleteComputer(long compId) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Computer deletedComp = this.getComputerById(compId);
		
		try {
			connection = repository.getConnection();
			preparedStatement = connection.prepareStatement(SQL_DELETE_COMPUTER);
			
			preparedStatement.setLong(1, new Long(compId));
			
			int status = preparedStatement.executeUpdate();
			if (status == 0) {
				throw new DAOException("Failed to delete the computer");
			}
			
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			closeRessources(connection, preparedStatement);
		}
		
		return deletedComp;
		
	}
	
	/**
	 * Update computer db.
	 *
	 * @param compId the comp id
	 * @param values the values
	 * @return the computer
	 * @throws SQLException the SQL exception
	 */
	public Computer updateComputer(long compId, Map<String, String> values) throws SQLException {
		Computer updatedComp = new Computer();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = repository.getConnection();
			preparedStatement = connection.prepareStatement(SQL_UPDATE_COMPUTER);
						
			// id
			updatedComp.setId(compId);
			
			// name
			updatedComp.setName(values.get("name"));
			preparedStatement.setString(1, updatedComp.getName());
			
			// instroduction year
			String[] introStr = values.get("introduced").split("-");
			LocalDate introduced = new LocalDate();
			introduced.withYear(Integer.parseInt(introStr[0]));
			introduced.withMonthOfYear(Integer.parseInt(introStr[1]));
			introduced.withDayOfMonth(Integer.parseInt(introStr[2]));		
			updatedComp.setIntroduced(introduced);
			preparedStatement.setTimestamp(2, 
					getTimeStampFromLocalDate(updatedComp.getIntroduced()));
			
			// discontinued year
			String[] discoStr = values.get("discontinued").split("-");
			LocalDate discontinued = new LocalDate();
			discontinued.withYear(Integer.parseInt(discoStr[0]));
			discontinued.withMonthOfYear(Integer.parseInt(discoStr[1]));
			discontinued.withDayOfMonth(Integer.parseInt(discoStr[2]));
			updatedComp.setDiscontinued(discontinued);
			preparedStatement.setTimestamp(3, 
					getTimeStampFromLocalDate(updatedComp.getDiscontinued()));
			
			// companyId
			updatedComp.setCompanyId(Long.parseLong(values.get("companyId")));
			preparedStatement.setLong(4, updatedComp.getCompanyId());
			
			preparedStatement.setLong(5, new Long(compId));

			int status = preparedStatement.executeUpdate();
			if (status == 0) {
				throw new DAOException(
						"Failed to update the computer : " 
								+ updatedComp.getName());
			}
			
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			closeRessources(connection, preparedStatement);
		}
		
		return updatedComp;
	}
	
	/**
	 * Adds the computer to db.
	 *
	 * @param comp the comp
	 * @throws SQLException the SQL exception
	 */
	public void addComputer(Computer comp) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = repository.getConnection();
			preparedStatement = connection.prepareStatement(SQL_CREATE_COMPUTER);
			preparedStatement.setString(1, comp.getName());
			
			Timestamp introduced = getTimeStampFromLocalDate(comp.getIntroduced());
			preparedStatement.setTimestamp(2, introduced);			
			
			Timestamp discontinued = getTimeStampFromLocalDate(comp.getDiscontinued());		
			preparedStatement.setTimestamp(3, discontinued);
			
			preparedStatement.setLong(4, comp.getCompanyId());
			
			int status = preparedStatement.executeUpdate();
			if (status == 0) {
				throw new DAOException("Failed to add the new computer");
			}
			
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			closeRessources(connection, preparedStatement);
		}

	}
		
	/**
	 * Gets the computer by id.
	 *
	 * @param criteria the criteria
	 * @return the computer by id
	 * @throws SQLException the SQL exception
	 */
	public Computer getComputerById(long criteria) throws SQLException {
		Computer comp = null;
		ResultSet resultSet = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = repository.getConnection();
			preparedStatement = connection.prepareStatement(SQL_SELECT_ONE_COMPUTER_BY_ID);
			preparedStatement.setLong(1, new Long(criteria));
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			comp = computerMap (resultSet);
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			closeRessources(connection, preparedStatement);
		}
				
		return comp;
	}
	
	/**
	 * Gets the computer by name.
	 *
	 * @param criteria the criteria
	 * @return the computer by name
	 * @throws SQLException the SQL exception
	 */
	public Computer getComputerByName(String criteria) throws SQLException {
		Computer comp = null;
		ResultSet resultSet = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = repository.getConnection();
			preparedStatement = connection.prepareStatement(SQL_SELECT_ONE_COMPUTER_BY_NAME);
			preparedStatement.setString(1, criteria);
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			comp = computerMap (resultSet);
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			closeRessources(connection, preparedStatement);
		}
				
		return comp;
	}
	
	/**
	 * Gets the computers.
	 *
	 * @return the computers
	 * @throws SQLException the SQL exception
	 */
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
			closeRessources(connection, preparedStatement);
		}
		
		return listComp;
	}
	
	
	//////////////////////// UTILS ////////////////////////
	/**
	 * Computer map.
	 *
	 * @param resultSet the result set
	 * @return the computer
	 * @throws SQLException the SQL exception
	 */
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
			computer.setDiscontinued(ldDis);
		}				
		
		computer.setCompanyId(resultSet.getLong("company_id"));
		return computer;
	}
	
	/**
	 * Gets the time stamp from local date.
	 *
	 * @param ld the ld
	 * @return the time stamp from local date
	 */
	private static Timestamp getTimeStampFromLocalDate(LocalDate ld) {
		return Timestamp.valueOf(
				ld.getYear() + "-" + 
				ld.getMonthOfYear() + "-" + 
				ld.getDayOfMonth() + 
				" 00:00:00.0");
	}
	
	/**
	 * Close ressources.
	 *
	 * @param conn the conn
	 * @param p the p
	 * @throws SQLException the SQL exception
	 */
	private static void closeRessources(Connection conn, PreparedStatement p) throws SQLException {
		conn.close();
		p.close();
	}

}
