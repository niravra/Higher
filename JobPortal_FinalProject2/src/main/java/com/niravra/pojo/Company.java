package com.niravra.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "companytable")
public class Company {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="companyid")
	private int companyId;
	
	@Column(name="companyname")
	private String companyName;
	
	@Column(name="companyaddress")
	private String companyAddress;
	
	@Column(name="companydesc")
	private String companyDescription;

	
	public Company(){
		
	}
	
	public Company( String companyName, String companyAddress, String companyDescription){
		this.companyName = companyName;
		this.companyAddress = companyAddress;
		this.companyDescription = companyDescription;
	}
	
	
	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public String getCompanyDescription() {
		return companyDescription;
	}

	public void setCompanyDescription(String companyDescription) {
		this.companyDescription = companyDescription;
	}
	
	
}
