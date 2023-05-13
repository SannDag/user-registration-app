	package com.sanndag.registerapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.sanndag.registerapp.service.IUserService;

@Controller
public class RegisterController {
	
	@Autowired
	private IUserService userService;
	
	@GetMapping("/login")
	public String signIn() {
		return "login";
	}
	
	@GetMapping
	public String showIndex(Model model) {
		model.addAttribute("users", userService.listUser());
		return "index";
	}

}
