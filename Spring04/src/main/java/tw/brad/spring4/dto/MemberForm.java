package tw.brad.spring4.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class MemberForm {
	private String email;
	private String passwd;
	private String name;
	private MultipartFile iconFile;
}
