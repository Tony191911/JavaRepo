package tw.brad.spring2.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.brad.spring2.dto.User;

@RestController
@RequestMapping("/api/user")
public class UserController {
	@RequestMapping("/test1")
	public void test1() {
		User user = new User();
			user.setId(123);
			user.setName("brad");
			user.setGender(false);
			System.out.println(user);
	}
}
