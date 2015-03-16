package com.excilys.computerdb.command;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.excilys.computerdb.beans.Computer;
import com.excilys.computerdb.dao.*;

public class ComputerCommand {
	private static final String COMP_NAME = "name";
	private static final String COMP_INTRO = "introduced";
	private static final String COMP_DISCO = "discontinued";
	private static final String COMP_COMPANY_ID = "companyId";
	
	private RepositoryDAO rep = RepositoryDAO.getInstance();
	private CompanyDAO companyDAO = rep.getCompanyDAO();
	private ComputerDAO computerDAO = rep.getComputerDAO();
	private Map<String, String> values = new HashMap<>();
	
	public void printAllComputers() throws SQLException {
		List<Computer> listC = computerDAO.getComputers();
		
		int pageSize = 5;
		Scanner sc = new Scanner(System.in);
		while (true) {
			String action = sc.next();
			if (action.equals("n") || action.equals("next")) {
				
			}
		}
	}
	

}
