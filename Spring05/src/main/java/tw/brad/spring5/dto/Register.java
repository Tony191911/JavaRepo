package tw.brad.spring5.dto;

import lombok.Data;

@Data
public class Register {
	private String account;
	private String passwd;
	private String name;
}
