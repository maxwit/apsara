package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping(value="/web")
public class WebDemo {

	@RequestMapping(value="/hello", method=RequestMethod.GET)
	public String sayHello() {
		return "Hello";
	}
}
