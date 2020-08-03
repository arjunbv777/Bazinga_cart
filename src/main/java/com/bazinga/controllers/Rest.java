package com.bazinga.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Rest {

	@GetMapping("api")
	String spi() {
		return "yep";
	}
}
