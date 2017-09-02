package com.niravra.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.niravra.DAO.JobsDAO;
import com.niravra.pojo.JobList;
import com.niravra.pojo.User;

public class EmployerInterceptor extends HandlerInterceptorAdapter{

	String errorPage;

	public String getErrorPage() {
		return errorPage;
	}

	public void setErrorPage(String errorPage) {
		this.errorPage = errorPage;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		HttpSession session = (HttpSession) request.getSession();
		System.out.println("the URL is " +request.getRequestURL());
		System.out.println("the URI is " +request.getRequestURI());
		JobsDAO jobsDao = new JobsDAO();

		
//		if(session.getAttribute("user") != null){
//			System.out.println("in interceptor");
//			return true;
//		}
		if (session.getAttribute("user") != null){
		User user = (User) session.getAttribute("user");
		
		if (user.getRole().equalsIgnoreCase("employer") ){
			if (request.getRequestURI().equalsIgnoreCase("/controller/jobs/update.htm")){
				int id = Integer.parseInt(request.getParameter("jobId"));
				JobList job = jobsDao.getJob(id);
				
				if (user.getUsername().equals(job.getPostedID())){
					System.out.println("User Authorized to change the job");
					return true;
				} else {
					response.sendRedirect(request.getContextPath() +"/authorize/JobUpdateError.htm");
					return false;
				}
			}
			System.out.println("in Employer interceptor");
			return true;
		}
		}
		System.out.println("not Employer");
		response.sendRedirect(request.getContextPath() +"/authorize/error.htm");
		return false;
		
		
		
		
	}
	
}
