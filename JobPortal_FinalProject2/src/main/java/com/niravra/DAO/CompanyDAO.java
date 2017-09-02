package com.niravra.DAO;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.niravra.exception.CompanyException;
import com.niravra.exception.UserException;
import com.niravra.pojo.Company;
import com.niravra.pojo.User;

public class CompanyDAO extends DAO{

	public CompanyDAO(){
		
	}
	
	public List<Company> list() throws CompanyException {
        try {
            begin();
            Query q = getSession().createQuery("from Company");
            List<Company> list = q.list();
            commit();
            return list;
        } catch (HibernateException e) {
            rollback();
            throw new CompanyException("Could not list the companies", e);
        }
    }
	
	
	public Company register(Company c) throws CompanyException{
			
		try {
            begin();
            Company comp = new Company(c.getCompanyName(),c.getCompanyAddress(),c.getCompanyDescription());
            getSession().save(comp);
            commit();
            return comp;
        } catch (HibernateException e) {
            rollback();
            //throw new AdException("Could not create the category", e);
            throw new CompanyException("Exception while creating compnay: " + e.getMessage());
        }
	}
	
	public Company get(String name) throws UserException {
		try {
			begin();
			Query q = getSession().createQuery("from Company where companyName= :companyName");
			q.setString("companyName", name);		
			Company comp = (Company) q.uniqueResult();
			commit();
			return comp;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get company " + name, e);
		}
	}
	
	public Company getById(int id) throws UserException {
		try {
			begin();
			Query q = getSession().createQuery("from Company where companyId= :compId");
			q.setInteger("compId", id);		
			Company comp = (Company) q.uniqueResult();
			commit();
			return comp;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get company " + id, e);
		}
	}
	
	public void delete(Company comp) throws CompanyException{
		try {
			begin();
			getSession().delete(comp);
//			return comp;
		} catch (HibernateException e) {
			rollback();
			throw new CompanyException("Could not delete company " + comp.getCompanyId(), e);
		}
	}
	}

