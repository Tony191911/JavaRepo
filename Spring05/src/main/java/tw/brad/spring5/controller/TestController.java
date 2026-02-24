package tw.brad.spring5.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
	
	@RequestMapping("/test1")
	public void test1() {
		System.out.println("TestController:test1()");
	}
	
	@RequestMapping("/test2")
	public void test2() {
		System.out.println("TestController:test2()");
	}
}
