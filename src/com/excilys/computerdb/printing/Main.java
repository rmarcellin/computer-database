package com.excilys.computerdb.printing;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

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
		Paging paging = new Paging(listComputer);
		
		System.out.println("+---------------------------------------------------------------+");
		System.out.println("|     Pour interagir avec le CL-UI, suivez les instructions     |");
		System.out.println("|     1 => pour afficher les \"Companies\"                        |");
		System.out.println("|     2 => pour afficher les \"Computers\"                        |");
		System.out.println("|     3 => pour afficher les détails sur un computer            |");
		System.out.println("|     4 => pour ajouter un computer                             |");
		System.out.println("|     5 => pour modifier les informations d'un computer         |");
		System.out.println("|     6 => pour supprimer un computer                           |");
		System.out.println("|    /!\\ Pour les étapes 4, 5, et 6, suivez les instructions    |");
		System.out.println("+---------------------------------------------------------------+");
		
		
		Scanner sc = new Scanner(System.in);
		int action;
		while (true) {
			action = sc.nextInt();
			switch (action) {
			case 1:
				
				break;
			case 2:
							
				break;
			case 3:
				
				break;
			case 4:
				
				break;
			case 5:
				
				break;

			default:
				break;
			}
			
		}
	}

}
