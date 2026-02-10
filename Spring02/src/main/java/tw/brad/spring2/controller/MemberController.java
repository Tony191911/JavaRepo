package tw.brad.spring2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tw.brad.spring2.entity.Member;
import tw.brad.spring2.service.MemberService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/members")
public class MemberController {

	@Autowired
	private MemberService service;

	/*
		request: /exist?email=xxx
		response: true/false
	*/
	@GetMapping("/exist")
	public ResponseEntity<Boolean> checkEmail(@RequestParam String email) {
		boolean isExist = service.checkEmail(email);
		return ResponseEntity.ok(isExist);
	}

	/*
		request: Member => {}
		response: {"success": true/false}
	*/

	@PostMapping("/register")
	public ResponseEntity<Map<String, Boolean>> register(@RequestBody Member member) {
		System.out.println(member.getEmail());
		System.out.println(member.getPasswd());
		System.out.println(member.getName());

		Map<String, Boolean> map = Map.of("success", true);
		return ResponseEntity.ok(map);
	}
}
