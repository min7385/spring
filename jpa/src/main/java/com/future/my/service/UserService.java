package com.future.my.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.future.my.domain.User;

@Service
public class UserService {
	@Autowired
	private IUserRepository repo;
	
	public Optional<User> findByUsername(String username){
		return repo.findByUsername(username);
	}
	// save, delete, findById, findAll 기본으로 메서드가 있음.
	public User save(User user) {
		return repo.save(user);
	}
}
