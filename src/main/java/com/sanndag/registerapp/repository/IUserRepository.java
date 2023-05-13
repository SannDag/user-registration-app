package com.sanndag.registerapp.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;

import com.sanndag.registerapp.model.UserEntity;

@Repository
public interface IUserRepository extends JpaRepository<UserEntity, Long>{
	
	public UserEntity findByEmail(String email);

	public UserEntity save(User user);

}
