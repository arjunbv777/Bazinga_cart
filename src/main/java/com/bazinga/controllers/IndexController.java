package com.bazinga.controllers;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

	@RequestMapping(value={"/index","/"})
	public ModelAndView defaultHome(Principal principal) {
		final String currentUser = principal.getName();//SecurityContextHolder.getContext().getAuthentication().getName();
		System.out.println(currentUser);
		return new ModelAndView("index").addObject("username", currentUser);
	}

	@RequestMapping("/login")
	public ModelAndView login() {
		return new ModelAndView("login");
	}
	 
}
