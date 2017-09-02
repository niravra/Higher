package com.niravra.JobPortal;

import java.util.List;

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
import com.niravra.exception.CompanyException;
import com.niravra.pojo.Company;
import com.niravra.pojo.JobList;
import com.niravra.pojo.User;
import com.niravra.validator.CompanyValidator;

@Controller
@RequestMapping("/company/*")
public class CompanyController {

	@Autowired
	@Qualifier("companyDao")
	CompanyDAO companyDao;
	@Autowired
	@Qualifier("companyValidator")
	CompanyValidator validator;
	
	@Autowired
	@Qualifier("jobsDao")
	JobsDAO jobsDao;
	
	@Autowired
	@Qualifier("userDao")
	UserDAO userDao;
	
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}
	
	@RequestMapping(value = "/company/register.htm", method = RequestMethod.POST)
	protected ModelAndView registerNewUser(HttpServletRequest request,  @ModelAttribute("company") Company company, BindingResult result) throws Exception {
		
		validator.validate(company, result);
		if (result.hasErrors()) {
			return new ModelAndView("register-company", "company", company);
		}

		try {

			System.out.print("registerNewCompany");
			
			Company c = companyDao.register(company);
			
			return new ModelAndView("company-add", "company", c);

		} catch (CompanyException e) {
			System.out.println("Exception: " + e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while registering company");
		}
	}
	
	@RequestMapping(value = "/company/register.htm", method = RequestMethod.GET)
	protected ModelAndView registerUser() throws Exception {
		System.out.print("registerUser");

		return new ModelAndView("register-company", "company", new Company());

	}
	
	@RequestMapping(value = "/company/delete.htm", method = RequestMethod.POST)
	protected ModelAndView searchjobs(HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		HttpSession session = (HttpSession) request.getSession();
		User u = (User) session.getAttribute("user");
		int compId = Integer.parseInt(request.getParameter("compID"));
		Company comp = companyDao.getById(compId);
//		List<JobList> jobs = jobsDao.listCompany(comp);
		companyDao.delete(comp);
		
		
		mv.addObject("companies", companyDao.list());
		mv.setViewName("admin-home");

		return mv;
	}
}
