package com.sanndag.registerapp.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.sanndag.registerapp.controller.dto.UserRegisterDto;
import com.sanndag.registerapp.model.UserEntity;

public interface IUserService extends UserDetailsService{
	
	public UserEntity save(UserRegisterDto registerDto);
	
	public List<UserEntity> listUser();

}
