package tw.brad.spring4.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.brad.spring4.test.User;

/*
 * request => Controller -> Model 網頁所需要的資料
 * 						 -> View (Resolver) Thymeleaf -> HTML
 * 						 -> Response
 * request => RestController
 * 		   -> return String(Web Page Content) or ResponseEntity.ok(物件)
 */

//@RestController
@Controller
@RequestMapping("/")
public class WebController {
	
	/*
	 * ThymeleafViewResolver: prefix + viewName + suffix
	 * 預設值:
	 * spring.thymeleaf.prefix=classpath:/template
	 * spring.thymeleaf.suffix=.html
	 */
	
	@RequestMapping("/index")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/member/index")
	public String memberIndex() {
		return "member/index";
	}
	
	@RequestMapping("/page1")
	public String page1(Model model) {
		model.addAttribute("companyName", "Brad Big Company");
		model.addAttribute("userName", "Brad");
		
		User user = new User();
		user.setName("Eric");
		user.setGender(true);
		user.setAge(18);
		model.addAttribute("user", user);
		
		System.out.println(user);
		
		String now = LocalDateTime.now().format(
				DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
		model.addAttribute("now", now);
		
		return "page1";
	}
	
}
