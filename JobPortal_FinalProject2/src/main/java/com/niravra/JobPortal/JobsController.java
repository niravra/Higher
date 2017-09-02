package com.niravra.JobPortal;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpRequest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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
import com.niravra.exception.JobsException;
import com.niravra.pojo.Company;
import com.niravra.pojo.JobList;
import com.niravra.pojo.User;
import com.niravra.validator.JobsValidator;

@Controller
@RequestMapping("/jobs/*")
public class JobsController {

	@Autowired
	@Qualifier("jobsDao")
	JobsDAO jobsDao;

	@Autowired
	@Qualifier("userDao")
	UserDAO userDao;

	@Autowired
	@Qualifier("companyDao")
	CompanyDAO companyDao;

	@Autowired
	@Qualifier("jobsValidator")
	JobsValidator validator;
	
	@Autowired
    private JavaMailSender mailSender;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}

	@RequestMapping(value = "/jobs/register.htm", method = RequestMethod.GET)
	protected ModelAndView addjobs(HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		HttpSession session = (HttpSession) request.getSession();
		User u = (User) session.getAttribute("user");
		mv.addObject("companyName", u.getCompany().getCompanyName());
		mv.addObject("joblist", new JobList());
		mv.setViewName("job-add");

		return mv;
	}

	@RequestMapping(value = "/jobs/register.htm", method = RequestMethod.POST)
	protected ModelAndView registerNewUser(HttpServletRequest request, @ModelAttribute("joblist") JobList joblist,
			BindingResult result) throws Exception {

		validator.validate(joblist, result);
		if (result.hasErrors()) {
			return new ModelAndView("job-add", "joblist", joblist);
		}

		try {
			ModelAndView mv = new ModelAndView();
			HttpSession session = (HttpSession) request.getSession();
			User u = (User) session.getAttribute("user");
			System.out.print("Add New Job");
			joblist.setCompany(u.getCompany());
			joblist.setPostedName(u.getFirstName());
			joblist.setPostedID(u.getUsername());
			JobList job = jobsDao.register(joblist);
			mv.addObject("jobs", jobsDao.listCompany(u.getCompany()));
			mv.addObject("joblist", job);
			mv.setViewName("employer-home");
			return mv;

		} catch (JobsException e) {
			System.out.println("Exception: " + e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while adding job");
		}
	}

	@RequestMapping(value = "/jobs/update.htm", method = RequestMethod.GET)
	protected ModelAndView updatejobs(HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		HttpSession session = (HttpSession) request.getSession();
		int id = Integer.parseInt(request.getParameter("jobId"));
		JobList job = jobsDao.getJob(id);
		System.out.println("THE PRODUCT ID IS :" + id);
		User u = (User) session.getAttribute("user");
		mv.addObject("companyName", u.getCompany().getCompanyName());
		// mv.addObject("joblist", new JobList());

		mv.addObject("jobList", job);
		mv.setViewName("job-update");

		return mv;
	}

	@RequestMapping(value = "/jobs/update.htm", method = RequestMethod.POST)
	protected ModelAndView updatejob(HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		HttpSession session = (HttpSession) request.getSession();
		int id = Integer.parseInt(request.getParameter("jobId"));
		JobList job = jobsDao.getJob(id);
		job.setJobName(request.getParameter("jobName"));
		job.setJobDesc(request.getParameter("jobDesc"));
		System.out.println("THE PRODUCT ID IS :" + id);
		jobsDao.update(job);
		User u = (User) session.getAttribute("user");
		// mv.addObject("companyName", u.getCompany().getCompanyName());
		// mv.addObject("joblist", new JobList());

		mv.addObject("jobList", job);
		mv.setViewName("update-success");

		return mv;
	}

	@RequestMapping(value = "/jobs/modify.htm", method = RequestMethod.POST)
	protected ModelAndView modifyJob(HttpServletRequest request, @ModelAttribute("joblist") JobList joblist,
			BindingResult result) throws Exception {

		// validator.validate(joblist, result);
		// if (result.hasErrors()) {
		// return new ModelAndView("job-add", "joblist", joblist);
		// }

		try {
			ModelAndView mv = new ModelAndView();
			String action = request.getParameter("action");
			int job_id = Integer.parseInt(request.getParameter("productID"));
			System.out.println("the action is : " + action);
			if (action.equals("Delete")) {
				JobList job = jobsDao.getJob(job_id);
				jobsDao.deleteJob(job);
				mv.setViewName("employer-home");

			}
			return mv;

			// HttpSession session = (HttpSession) request.getSession();
			// User u = (User) session.getAttribute("user");
			// System.out.print("Add New Job");
			// joblist.setCompany(u.getCompany());
			//
			// JobList job = jobsDao.register(joblist);
			// mv.addObject("jobs", jobsDao.listCompany(u.getCompany()));
			// mv.addObject("joblist", job);
			// mv.setViewName("employer-home");
			// return mv;

		} catch (JobsException e) {
			System.out.println("Exception: " + e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while adding job");
		}
	}

	@RequestMapping(value = "/jobs/add.htm", method = RequestMethod.POST)
	protected ModelAndView addJob(HttpServletRequest request) throws Exception {

		try {
			boolean flag = false;
			ModelAndView mv = new ModelAndView();
			HttpSession session = (HttpSession) request.getSession();
			User u = (User) session.getAttribute("user");
			int job_id = Integer.parseInt(request.getParameter("jobID"));
			for (JobList job1 : u.getJoblists()) {
				if (job1.getJobID() == job_id) {
					flag = true;
				}
			}
			System.out.print("Add New Job");
			if (!flag) {
				JobList job = jobsDao.getJob(job_id);
				// u.getJoblists().add(job);
				userDao.update(u, job);
				System.out.println("Entered the job addition new para");
				job.getUsers().add(u);
				jobsDao.update(job);
				String address = u.getEmail().getEmailAddress();
				String subject = "Job Applied Notification for " +job.getJobName();
				String message = "Dear "+u.getFirstName()+","+"\n"+"\nYou Applied for the position : "+job.getJobName()+"\n"+"\nJob Desc : " +job.getJobDesc() 
				+"\non : " +new Date() +"\n"+"\n"+"\nThank you for using our online Job Portal"+"\n"+"\nRegards"+"\nAdmin";
				SimpleMailMessage email = new SimpleMailMessage();
		        email.setTo(address);
		        email.setSubject(subject);
		        email.setText(message);
		        
		        mailSender.send(email);
		        
		        
				mv.addObject("jobs", jobsDao.listCompany(u.getCompany()));
				mv.addObject("joblist", job);
				mv.setViewName("add-success");
				flag = false;
				return mv;
			} else {
				System.out.println("Entered the other para where its already added");
				mv.addObject("jobs", null);
				mv.setViewName("add-already");
				return mv;
			}
		} catch (JobsException e) {
			System.out.println("Exception: " + e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while adding job");
		}
	}

	@RequestMapping(value = "/jobs/search.htm", method = RequestMethod.GET)
	protected ModelAndView searchjob(HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		HttpSession session = (HttpSession) request.getSession();
		User u = (User) session.getAttribute("user");
		// mv.addObject("companyName", u.getCompany().getCompanyName());
		// mv.addObject("joblist", new JobList());
		mv.setViewName("search-job");

		return mv;
	}

	@RequestMapping(value = "/jobs/search.htm", method = RequestMethod.POST)
	protected ModelAndView searchjobs(HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		HttpSession session = (HttpSession) request.getSession();
		User u = (User) session.getAttribute("user");
		String name = request.getParameter("inputtext");
		String search = request.getParameter("searchkey");

		if (search.equalsIgnoreCase("jobname")) {
			List<JobList> jobs = jobsDao.listJobname(name);
			mv.addObject("jobs", jobs);
			if (jobs.isEmpty())
				System.out.println("The returned list is empty");
			for (JobList job : jobs) {
				System.out.println("The matching jobs are : " + job.getJobName());
			}
		} else if (search.equalsIgnoreCase("company")) {
			Company c = companyDao.get(name);

			if (c == null) {
				mv.setViewName("search-fail");
				return mv;
			}
			List<JobList> jobs = jobsDao.listCompany(c);
			if (jobs.isEmpty()) {
				mv.setViewName("search-failjobs");
				return mv;
			}
			mv.addObject("jobs", jobs);
		}

		mv.setViewName("search-success");

		return mv;
	}

	@RequestMapping(value = "/jobs/applied.htm", method = RequestMethod.GET)
	protected ModelAndView appliedjob(HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		// HttpSession session = (HttpSession) request.getSession();
		// User u = (User) session.getAttribute("user");
		// mv.addObject("companyName", u.getCompany().getCompanyName());
		// mv.addObject("joblist", new JobList());
		mv.setViewName("jobs-applied");

		return mv;
	}
	
	@RequestMapping(value = "/jobs/viewalljobs.htm", method = RequestMethod.GET)
	protected ModelAndView viewAllJob(HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		// HttpSession session = (HttpSession) request.getSession();
		// User u = (User) session.getAttribute("user");
		// mv.addObject("companyName", u.getCompany().getCompanyName());
		// mv.addObject("joblist", new JobList());
		mv.addObject("jobs", jobsDao.list());
		mv.setViewName("viewalljobs");

		return mv;
	}
}
