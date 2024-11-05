package com.future.my;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@Value("${vapid.publicKey}")
	private String publicKey;
	
	@GetMapping("/")
	public String index(Model model) {
		System.out.println(publicKey);
		model.addAttribute("vapidPublicKey", publicKey);
		return "index";
	}
	
	@GetMapping("/admin")
	public String admin() {
		return "admin";
	}
	
}
