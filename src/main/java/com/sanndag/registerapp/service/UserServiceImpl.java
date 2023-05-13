package com.sanndag.registerapp.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sanndag.registerapp.controller.dto.UserRegisterDto;
import com.sanndag.registerapp.model.Rol;
import com.sanndag.registerapp.model.UserEntity;
import com.sanndag.registerapp.repository.IUserRepository;

@Service
public class UserServiceImpl implements IUserService{
	

	private IUserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public UserServiceImpl(IUserRepository userRepository) {
		super();
        this.userRepository = userRepository;
	}		
	
	@Override
	public UserEntity save(UserRegisterDto registerDto) {
		UserEntity user = new UserEntity(registerDto.getName(), registerDto.getLastname(), registerDto.getEmail(),
				passwordEncoder.encode(registerDto.getPassword()), Arrays.asList(new Rol("ROLE_USER")));
		
		return userRepository.save(user);
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity user = userRepository.findByEmail(username);
		if(user == null) {
			throw new UsernameNotFoundException("Invalid username or password entered");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapAuthoritiesRoles(user.getRoles()));
		
	}

	private Collection<? extends GrantedAuthority> mapAuthoritiesRoles(Collection<Rol> roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}


	@Override
	public List<UserEntity> listUser() {		
		return userRepository.findAll();
	}

}
