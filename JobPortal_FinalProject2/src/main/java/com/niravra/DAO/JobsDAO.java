package com.niravra.DAO;
import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.niravra.exception.JobsException;
import com.niravra.exception.UserException;
import com.niravra.pojo.Company;
import com.niravra.pojo.JobList;
import com.niravra.pojo.User;
public class JobsDAO extends DAO{

public JobsDAO(){
	
}
	
	
public JobList register(JobList j) throws JobsException{
		
		try {
            begin();
            JobList job = new JobList(j.getJobName(),j.getJobDesc());
            job.setCompany(j.getCompany());
            job.setPostedName(j.getPostedName());
            job.setPostedID(j.getPostedID());
            getSession().save(job);
            commit();
            return job;
        } catch (HibernateException e) {
            rollback();
            //throw new AdException("Could not create the category", e);
            throw new JobsException("Exception while creating compnay: " + e.getMessage());
        }
	}

public List<JobList> list() throws JobsException {
    try {
        begin();
        Query q = getSession().createQuery("from JobList");
        
        List<JobList> list = q.list();
        commit();
        return list;
    } catch (HibernateException e) {
        rollback();
        throw new JobsException("Could not list the Jobs", e);
    }
}

public List<JobList> listCompany(Company company) throws JobsException {
    try {
        begin();
        Query q = getSession().createQuery("from JobList where company= :comp");
        q.setInteger("comp", company.getCompanyId());
        List<JobList> list = q.list();
        commit();
        return list;
    } catch (HibernateException e) {
        rollback();
        throw new JobsException("Could not list the Jobs", e);
    }
}


public List<JobList> listJobname(String name) throws JobsException {
    try {
        begin();
        Query q = getSession().createQuery("from JobList where lower(jobName) LIKE lower(:jobname)");
        q.setString("jobname", "%"+name+"%");
        List<JobList> list = q.list();
        commit();
        return list;
    } catch (HibernateException e) {
        rollback();
        throw new JobsException("Could not list the Jobs with job name", e);
    }
}
//entityManager.createQuery("select at from AttendeesVO at where lower(at.user.firstName) LIKE lower(:searchKeyword)",AttendeesVO.class);
public JobList getJob(int id) throws JobsException{
	try{
		begin();
		Query q = getSession().createQuery("from JobList where jobID= :id");
		q.setInteger("id", id);
		JobList job = (JobList)q.uniqueResult();
		commit();
		return job;
	} catch(HibernateException e) {
		rollback();
		throw new JobsException("could not find the job",e);
	}
}


public void deleteJob(JobList job) throws JobsException {
	try {
//		Serializable id = new Long(job.getJobID());
//		Object persistentInstance = getSession().load(JobList.class, id);
		begin();
//		if (persistentInstance != null) {
//		    getSession().delete(persistentInstance);
//		}
//		etSession().delete(job);
//		commit();
		Query q = getSession().createQuery("delete from JobList where jobID= :id");
		q.setInteger("id", job.getJobID());
		JobList job1 = (JobList)q.uniqueResult();
		commit();
	} catch (HibernateException e) {
		rollback();
		throw new JobsException("cannot delete job", e.getCause());
	}
 }

public void update(JobList job) throws JobsException {
    try {
        begin();
        getSession().update(job);
        commit();
        System.out.println("Updated the job");
    } catch (HibernateException e) {
        rollback();
        throw new JobsException("Could not update the job", e);
    }
}

//public JobList findJob(int id, int) throws JobsException{
//	try{
//		begin();
//		Query q = getSession().createQuery("from JobList where jobID= :id");
//		q.setInteger("id", id);
//		JobList job = (JobList)q.uniqueResult();
//		commit();
//		return job;
//	} catch(HibernateException e) {
//		rollback();
//		throw new JobsException("could not find the job",e);
//	}
//}


}
