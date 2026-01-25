package websocket;

import java.io.IOException;
import java.util.HashSet;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint("/mycenter")
public class MyCenter {
	private static HashSet<Session> sessions;
	private static boolean isExistTeacher = false;
	private static Session teacherSession;
	
	public MyCenter() {
		if (sessions == null) {
			sessions = new HashSet<>();
		}
	}
	
	@OnOpen
	public void onOpen(Session session) {
		if (sessions.add(session)) {
			System.out.println("New Session...");
		};
	}
	
	@OnMessage
	public void onMessage(String mesg, Session session) {
		// 第一個if判斷是否有 Teacher ，沒有的話把第一個連進來的設為 Teacher
		// 第二個if判斷如果是 Teacher 在傳送訊息，就發送給所有人包含 Teacher 自己
		if (!isExistTeacher && mesg.contains("isTeacher")) {
			// 第一個連進來的是老師(只能有一位)
			isExistTeacher = true;
			teacherSession = session;
		}else if (session == teacherSession) {
			for (Session userSession : sessions) {
				try {
					// 取得傳送的管道並把訊息傳送給每個student
					userSession.getBasicRemote().sendText(mesg);
				} catch (IOException e) {
				}
			}
		}
	}
	
	@OnClose
	public void onClose(Session session) {
		sessions.remove(session);
	}
	
	@OnError
	public void onError(Session session, Throwable t) {
		System.out.println("onError()");
	}
	
}
