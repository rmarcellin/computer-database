package com.excilys.computerdb.beans;

public class Company {
	
	private long id;
	private String name;
	
	public Company() {}
	
	public Company(long id, String name) {
		this.id = id;
		this.name = name;
	}


	public long getId() {
		return id;
	}


	public String getName() {
		return name;
	}


	public void setId(long id) {
		this.id = id;
	}


	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		return new Long(id).intValue();
	}

	@Override
	public boolean equals(Object obj) {		
		return this.id == ((Company)obj).id;
	}

	@Override
	public String toString() {
		return "[id = " + id + ", "
				+ (name != null ? "  name : " + name : "") + "]";
	}
	
	

}
