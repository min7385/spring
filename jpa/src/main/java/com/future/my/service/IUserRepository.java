package com.future.my.service;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.future.my.domain.User;

public interface IUserRepository extends JpaRepository<User, Long>{
	
	Optional<User>findByUsername(String username);
}
