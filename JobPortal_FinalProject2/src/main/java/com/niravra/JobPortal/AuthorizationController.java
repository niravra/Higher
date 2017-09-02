package com.niravra.JobPortal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.niravra.pojo.JobList;
import com.niravra.pojo.User;

@Controller
@RequestMapping("/authorize/*")
public class AuthorizationController {

	
	@RequestMapping(value = "/authorize/error.htm", method = RequestMethod.GET)
	protected ModelAndView addjobs(HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("errorpage");

		return mv;
	}
	
	@RequestMapping(value = "/authorize/JobUpdateError.htm", method = RequestMethod.GET)
	protected ModelAndView errorjobs(HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("error-jobupdate");

		return mv;
	}
}
