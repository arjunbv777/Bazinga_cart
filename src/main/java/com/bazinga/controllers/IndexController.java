package com.bazinga.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

	@RequestMapping("/index")
	public ModelAndView defaultHome() {
		return new ModelAndView("index");
	}

	@RequestMapping("/login")
	public ModelAndView login() {
		return new ModelAndView("login");
	}
	 
}
