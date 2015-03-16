package com.excilys.computerdb.printing;

import java.sql.SQLException;
import java.util.List;

import com.excilys.computerdb.beans.Computer;
import com.excilys.computerdb.dao.CompanyDAO;
import com.excilys.computerdb.dao.ComputerDAO;
import com.excilys.computerdb.dao.RepositoryDAO;

public class Main {
	private static final String COMP_NAME = "name";
	private static final String COMP_INTRO = "introduced";
	private static final String COMP_DISCO = "discontinued";
	private static final String COMP_COMPANY_ID = "companyId";
	
	private enum Choice {ALL_COMPUTERS, ALL_COMPANIES, ONE_COMPUTER, }

	public static void main(String[] args) throws SQLException {
		RepositoryDAO rep = RepositoryDAO.getInstance();
		//CompanyDAO companyDAO = rep.getCompanyDAO();
		ComputerDAO computerDAO = rep.getComputerDAO();
		List<Computer> listComputer = computerDAO.getComputers();
		Paging pageing = new Paging(listComputer);
	}

}
