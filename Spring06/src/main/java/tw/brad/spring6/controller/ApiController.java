package tw.brad.spring6.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.brad.spring6.dto.Login;

@RestController
@RequestMapping("/api")
public class ApiController {
	
	@PostMapping("/login")
	public void login(@RequestBody Login login) {
		System.out.println(login.getEmail());
		System.out.println(login.getPasswd());
	}
}
