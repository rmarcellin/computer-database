package com.excilys.computerdb.beans;

import org.joda.time.LocalDate;


public class Computer {
	
	private long id;
	private String name;
	private LocalDate introduced;
	private LocalDate discontinued;
	private long companyId;
	
	public Computer() {}
	 
	public Computer(long id, String name, LocalDate introduced, LocalDate discontinued,
			long companyId) {
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.companyId = companyId;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public LocalDate getIntroduced() {
		return introduced;
	}

	public LocalDate getDiscontinued() {
		return discontinued;
	}

	public long getCompanyId() {
		return companyId;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setIntroduced(LocalDate introduced) {
		this.introduced = introduced;
	}

	public void setDiscontinued(LocalDate discontinued) {
		this.discontinued = discontinued;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	@Override
	public int hashCode() {
		return new Long(id).intValue();
	}

	@Override
	public boolean equals(Object obj) {
		return this.id == ((Computer)obj).id;
	}

	@Override
	public String toString() {
		return "[id = " + id + ", "
				+ (name != null ? "Computer : " + name + ", " : "") 
				+ " introduced : " + introduced + ", discontinued : " 
				+ discontinued + ", company_id : " + companyId + "]";
	}	 
	 	

}
