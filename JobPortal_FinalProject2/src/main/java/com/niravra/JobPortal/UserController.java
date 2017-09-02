package com.niravra.JobPortal;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.niravra.DAO.CompanyDAO;
import com.niravra.DAO.JobsDAO;
import com.niravra.DAO.UserDAO;
import com.niravra.exception.UserException;
import com.niravra.pojo.Company;
import com.niravra.pojo.JobList;
import com.niravra.pojo.User;
import com.niravra.validator.UserValidator;

@Controller
@RequestMapping("/user/*")
public class UserController {

	@Autowired
	@Qualifier("userDao")
	UserDAO userDao;

	@Autowired
	@Qualifier("userValidator")
	UserValidator validator;

	@Autowired
	@Qualifier("companyDao")
	CompanyDAO companyDao;
	
	@Autowired
	@Qualifier("jobsDao")
	JobsDAO jobsDao;
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}
	
	@RequestMapping(value = "/user/login.htm", method = RequestMethod.GET)
	protected String goToUserHome(HttpServletRequest request) throws Exception {
		return "home";
	}
	
	
	@RequestMapping(value = "/user/login.htm", method = RequestMethod.POST)
	protected ModelAndView loginUser(HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		HttpSession session = (HttpSession) request.getSession();
		
		try {

			System.out.print("loginUser");

			User u = userDao.get(request.getParameter("username"), request.getParameter("password"));
			
			if(u == null){
				System.out.println("UserName/Password does not exist");
				session.setAttribute("errorMessage", "UserName/Password does not exist");
				mv.setViewName("error");
				return mv;
			}
			session.setAttribute("user", u);
			String role = u.getRole().trim();
			
			if (role.equals("employee")){
			System.out.println("Entered here");
//			Set<JobList> job = u.getJoblists();
			
//			System.out.println("The email addrees of Employee : " +u.getFirstName()+" is "+u.getEmail().getEmailAddress());
				mv.addObject("jobs", jobsDao.list());
				mv.setViewName("employee-home");
				return mv ;
			}
			
			else if (role.equals("employer")){
//				System.out.println("Entered in the region");
//				System.out.println("The company id of the user is : " +u.getCompany().getCompanyId());
//				mv.addObject("company", u.getCompany().getCompanyName());
//				System.out.println("The email addrees of Employer : " +u.getFirstName()+" is "+u.getEmail().getEmailAddress());
				mv.addObject("jobs", jobsDao.listCompany(u.getCompany()));
				mv.setViewName("employer-home");
				return mv;
			}
			
			else if(role.equals("admin")){
//				System.out.println("The email addrees of Admin : " +u.getFirstName()+" is "+u.getEmail().getEmailAddress());
				mv.addObject("companies", companyDao.list());
				mv.setViewName("admin-home");
				return mv;	
				}
			
			else
				return null;
			
			

		} catch (UserException e) {
			System.out.println("Exception: " + e.getMessage());
			session.setAttribute("errorMessage", "error while login");
			mv.setViewName("error");
			return mv;
		}

	}
	
	@RequestMapping(value = "/user/register.htm", method = RequestMethod.GET)
	protected ModelAndView registerUser() throws Exception {
		System.out.print("registerUser");
		ModelAndView mv = new ModelAndView();
		mv.addObject("companies", companyDao.list());
		mv.addObject("user",new User());
		mv.setViewName("register-user");
//		return new ModelAndView("register-user", "user", new User());
		return mv;

	}
	
	@RequestMapping(value = "/user/employee.htm", method = RequestMethod.GET)
	protected ModelAndView employeeHome() throws Exception {
		System.out.print("Employee Home");
		ModelAndView mv = new ModelAndView();
		mv.addObject("jobs", jobsDao.list());
		mv.setViewName("employee-home");
//		return new ModelAndView("register-user", "user", new User());
		return mv;

	}
	
	@RequestMapping(value = "/user/employer.htm", method = RequestMethod.GET)
	protected ModelAndView employerHome(HttpServletRequest request) throws Exception {
		System.out.print("Employeer Home");
		HttpSession session = (HttpSession) request.getSession();
		User u = (User) session.getAttribute("user");
		ModelAndView mv = new ModelAndView();
		mv.addObject("jobs", jobsDao.listCompany(u.getCompany()));
		mv.setViewName("employer-home");
		return mv;
//		return new ModelAndView("register-user", "user", new User());
		

	}
	
	@RequestMapping(value = "/user/admin.htm", method = RequestMethod.GET)
	protected ModelAndView adminHome() throws Exception {
		System.out.print("Admin Home");
		ModelAndView mv = new ModelAndView();
		mv.addObject("companies", companyDao.list());
		mv.setViewName("admin-home");
//		return new ModelAndView("register-user", "user", new User());
		return mv;

	}
	
	@RequestMapping(value = "/user/register.htm", method = RequestMethod.POST)
	protected ModelAndView registerNewUser(HttpServletRequest request,  @ModelAttribute("user") User user, BindingResult result) throws Exception {

		validator.validate(user, result);

		if (result.hasErrors()) {
//			return new ModelAndView("register-user", "user", user);
			ModelAndView mv = new ModelAndView();
			mv.addObject("companies", companyDao.list());
			mv.addObject("user", user);
			mv.setViewName("register-user");
			return mv;
		}

		try {

			System.out.print("registerNewUser");
			String role = request.getParameter("myRole");
			user.setRole(role);
			String company = request.getParameter("myCompany");
			System.out.println("The Company is :" +company);
			
			Company c = companyDao.get(company);
			
			System.out.println("The company ID : " +c.getCompanyId());
			user.setCompany(c);
			User u = userDao.register(user);
			
			request.getSession().setAttribute("user", u);
			
			return new ModelAndView("user-home", "user", u);
			
	
		} catch (UserException e) {
			System.out.println("Exception: " + e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while login");
		}
	}

}
