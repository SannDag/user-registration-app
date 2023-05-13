package com.sanndag.registerapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sanndag.registerapp.controller.dto.UserRegisterDto;
import com.sanndag.registerapp.service.IUserService;

@Controller
@RequestMapping("/register")
public class UserRegisterController {
	
	private IUserService userService;
	
	public UserRegisterController(IUserService userService) {
		super();
		this.userService = userService;
	}
	
	@ModelAttribute("user")
	public UserRegisterDto returnNewUserRegisterDto() {		
		return new UserRegisterDto();
	}
	
	@GetMapping
	public String showRegisterForm() {
		return "register";
	}
	
	@PostMapping
	public String registerAccountUser(@ModelAttribute("user") UserRegisterDto registerDto){
		userService.save(registerDto);
		return "redirect:/register?succesfull";
	}	

}
