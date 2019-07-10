package com.karma.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HelloController {
	
	// tat ca method se nhay vao
	@GetMapping(value= {"/hi"})
	public String hello(HttpServletRequest request) {
		request.setAttribute("name", "Java Spring Boot");
		return "hello";
	}
	//Mapping cho tung method 
	@RequestMapping(value="/hello",method = RequestMethod.GET)
	public String hi(Model model) {
		model.addAttribute("name", "Model Spring");
		return "hello";
	}
	
}
