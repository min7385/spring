package com.future.my.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.future.my.domain.User;
import com.future.my.service.UserService;

@Controller
public class HomeController {
	
	@Autowired
	UserService service;
	@GetMapping("/")
	public String loginForm() {
		return "login";
	}
	@PostMapping("/login")	
	public String login(User user, Model model) {
		Optional<User>foundUser = service.findByUsername(user.getUsername());
		if(foundUser.isPresent() && foundUser.get().getPassword().equals(user.getPassword())) {
			return "redirect:/home";
		}else {
			model.addAttribute("error", "Invalid username or password");
			return "login";
		}
	}
	@GetMapping("/home")
	public String home() {
		return "home";
	}
	
	@GetMapping("/register")
	public String registerForm() {
		return "register";
	}
	
	@PostMapping("/register")
	public String register(User user) {
		service.save(user);
		return "redirect:/";
	}
	
}
