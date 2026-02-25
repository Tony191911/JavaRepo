package tw.brad.spring7.response;

import lombok.Data;

@Data
public class ChatMessage {
	private String email, content, time;
}
