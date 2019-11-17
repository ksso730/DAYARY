package us.flower.dayary.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Component;
import org.springframework.web.server.WebSession;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import us.flower.dayary.controller.people.SessionNames;
import us.flower.dayary.domain.People;

@Component
public class EchoHandler extends TextWebSocketHandler{
	
		List<WebSocketSession> sessions = new ArrayList<>();
		Map<String, WebSocketSession> userSessions = new HashMap<>();

		@Override
		public void afterConnectionEstablished(WebSocketSession session) throws Exception {
			System.out.println("afterConnectionEstablished : " + session);
			sessions.add(session);
			String senderId = getId(session);
			userSessions.put(senderId, session);
		}
		
		@Override
		protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
			System.out.println("handleTextMessage : " + session + " : " + message);
			//String senderId = getId(session);
//			for (WebSocketSession sess : sessions) {
//				sess.sendMessage(new TextMessage(senderId + " : " + message.getPayload()));
//			}
			
			//protocol : cmd, 모임id, 모임명, 가입유저명 (regist, 모임id, 모임명, 가입유저명)
			String msg = message.getPayload();
			if(StringUtils.isNotEmpty(msg)) {
				String[] strs = msg.split(",");
				
				if(strs != null) {
					String cmd = strs[0];
					String moimId = strs[1];
					String moimTitle = strs[2];
					String registUser = strs[3];
					
					for(int cnt=4; cnt<strs.length; cnt++) {
						
						String recieveUser = strs[cnt];
						WebSocketSession moimPeopleSession = userSessions.get(recieveUser);
						
						System.out.println("*****#$!@#$!@#" + moimPeopleSession);
						if("regist".equals(cmd) && moimPeopleSession != null) {
							//	TextMessage tmpMsg = new TextMessage(replyWriter + "님이 "
							//			+ "<a href='/board/read?bno=" + bno + "'>" + bno + "</a>번 게시글에 댓글을 달았습니다!");
							TextMessage tmpMsg = new TextMessage(registUser + "님이  '" + moimTitle + "'  에 가입하셨습니다 !");
							System.out.println("*****rrrrrr" + registUser);
							moimPeopleSession.sendMessage(tmpMsg);
						}
						
					}
				}
			}
		}
		
		private String getId(WebSocketSession session) {
			Map<String, Object> httpSession = session.getAttributes();
			People loginUser = (People)httpSession.get(SessionNames.LOGIN);
			
			if (null == loginUser)
				return session.getId();
			else
				return String.valueOf(loginUser.getId()); 	
				//Long.toString(loginUser.getId()); 	
		}
	
		@Override
		public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
			System.out.println("afterConnectionEstablished:" + session + ":" + status);
    	}
}
