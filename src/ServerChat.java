import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.server.ServerEndpointConfig.Configurator;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import model.User;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

@ServerEndpoint(value = "/echo/{chatId}/{nickname}")
public class ServerChat {


    private static Map<String,CopyOnWriteArraySet<ServerChat>> chatRooms= new HashMap();
    private Session sessionUser=null;
    private String nickname="";
    private String chatId="";
    private User currentUser=null;
    

	@OnOpen
	public void onOpen(Session session, @PathParam("nickname") String nickname,
			@PathParam("chatId") String chatId) throws ClassNotFoundException, SQLException, IOException {
		
		
		this.sessionUser=session;
		this.nickname=nickname;
		this.chatId=chatId;
		String joinMessage="";
		CopyOnWriteArraySet<ServerChat> listConnectedUsers=chatRooms.get(this.chatId);
		if(listConnectedUsers==null) {
			synchronized(chatRooms) {
				if(!chatRooms.containsKey(this.chatId)) {
					listConnectedUsers=new CopyOnWriteArraySet<ServerChat>();
					chatRooms.put(this.chatId, listConnectedUsers);
				}
			}
		}
		listConnectedUsers.add(this);
		currentUser = User.searchUserByNickname(nickname);
		joinMessage=currentUser.getNickname() + " has joined the chat! ";
		receivingMessage( session, joinMessage );

	}

	@OnMessage
	public void receivingMessage(Session session, String message) {

		CopyOnWriteArraySet<ServerChat> listConnectedUsers=chatRooms.get(this.chatId);
		String messsageUser="";
		if(listConnectedUsers!=null) {
			for(ServerChat user:listConnectedUsers) {
				if(message.contains("has joined to the conversation")) {
					messsageUser=message;
				}else {
					messsageUser=currentUser.getNickname()+": "+message;
				}

				user.sessionUser.getAsyncRemote().sendText(messsageUser);
			}
		}
	}

	@OnClose
	public void closedConnection() {

		CopyOnWriteArraySet<ServerChat> listConnectedUsers=chatRooms.get(this.chatId);
		if(listConnectedUsers!=null) {

			listConnectedUsers.remove(this);
		}
	}
	
	public static  List <User> getNameConnectedUsers(String nameChatRoom) throws ClassNotFoundException, SQLException, IOException {

		List <User> listUsers = new ArrayList<User>();
		CopyOnWriteArraySet<ServerChat> listConnectedUsers=chatRooms.get(nameChatRoom);
		for (ServerChat user: listConnectedUsers) {
			listUsers.add(User.searchUserByNickname(user.nickname));
		}

		return listUsers;
	}
	

}

                    