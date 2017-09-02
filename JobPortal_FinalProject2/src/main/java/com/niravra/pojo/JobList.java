package com.niravra.pojo;

import java.util.Set;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "Jobs")
public class JobList {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "jobID", unique=true, nullable = false)
	private int jobID;
	
	@Column(name = "jobname")
	private String jobName;
	
	
	@Column(name = "jobDesc")
	private String jobDesc;
	
	
	@Column(name = "uname")
	private String postedName;
	
	
	@Column(name = "uId")
	private String postedID;
	
	@Column(name = "posteddate")
	private Date postedDate;
	
	@ManyToOne
	@PrimaryKeyJoinColumn
	private Company company;
	
	@ManyToMany(mappedBy="joblists") 
	private Set<User> users;

	public JobList(){
		
	}
	
	public JobList(String jobname, String jobdesc){
		this.jobName = jobname;
		this.jobDesc = jobdesc;
		this.postedDate = new Date();
		
	}
	public int getJobID() {
		return jobID;
	}

	public void setJobID(int jobID) {
		this.jobID = jobID;
	}

	
	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobDesc() {
		return jobDesc;
	}

	public void setJobDesc(String jobDesc) {
		this.jobDesc = jobDesc;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getPostedName() {
		return postedName;
	}

	public void setPostedName(String postedName) {
		this.postedName = postedName;
	}

	public String getPostedID() {
		return postedID;
	}

	public void setPostedID(String postedID) {
		this.postedID = postedID;
	}

	public Date getPostedDate() {
		return postedDate;
	}

	public void setPostedDate(Date postedDate) {
		this.postedDate = postedDate;
	}

	
	
	
}
