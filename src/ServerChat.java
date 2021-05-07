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

/**
 * ServerChat class
 * 
 * This class make the socket connection between every Client and the Server
 * @author Uriel
 *
 */
@ServerEndpoint(value = "/echo/{chatId}/{nickname}")
public class ServerChat {
	
	/**
	 * Chat Map
	 */
	private static Map<String, CopyOnWriteArraySet<ServerChat>> chatRooms = new HashMap();
	
	/**
	 * Current User connected
	 */
	private Session sessionUser = null;
	
	/**
	 * Nickname of client
	 */
	private String nickname = "";
	
	/**
	 * Id Client
	 */
	private String chatId = "";
	
	/**
	 * Current User Object connected
	 */
	private User currentUser = null;
	
	/**
	 * Event started when a client starts a connection
	 * 
	 * @param session: Current Session
	 * @param nickname: Current nickname client
	 * @param chatId: Current id Chat
	 */
	@OnOpen
	public void onOpen(Session session, @PathParam("nickname") String nickname, @PathParam("chatId") String chatId)
			throws ClassNotFoundException, SQLException, IOException {

		this.sessionUser = session;
		this.nickname = nickname;
		this.chatId = chatId;
		String joinMessage = "";
		CopyOnWriteArraySet<ServerChat> listConnectedUsers = chatRooms.get(this.chatId);
		if (listConnectedUsers == null) {
			synchronized (chatRooms) {
				if (!chatRooms.containsKey(this.chatId)) {
					listConnectedUsers = new CopyOnWriteArraySet<ServerChat>();
					chatRooms.put(this.chatId, listConnectedUsers);
				}
			}
		}
		listConnectedUsers.add(this);
		currentUser = User.searchUserByNickname(nickname);
		joinMessage = currentUser.getNickname() + " has joined the chat! ";
		receivingMessage(session, joinMessage);

	}
	
	/**
	 * Event started when a message is received by the Server
	 * 
	 * @param session: Session who has sended a message
	 * @param message: Message sended by the client
	 */
	@OnMessage
	public void receivingMessage(Session session, String message) {

		CopyOnWriteArraySet<ServerChat> listConnectedUsers = chatRooms.get(this.chatId);
		String messsageUser = "";
		if (listConnectedUsers != null) {
			for (ServerChat user : listConnectedUsers) {
				if (message.contains("has joined to the conversation")) {
					messsageUser = message;
				} else if (message.contains("has joined the chat")) {

					messsageUser = "Server:" + message;

				} else {
					messsageUser = currentUser.getNickname() + ": " + message;
				}

				user.sessionUser.getAsyncRemote().sendText(messsageUser);
			}
		}
	}
	
	/**
	 * Event Started when someone close the session
	 */
	@OnClose
	public void closedConnection() {

		CopyOnWriteArraySet<ServerChat> listConnectedUsers = chatRooms.get(this.chatId);
		if (listConnectedUsers != null) {

			listConnectedUsers.remove(this);
		}
	}
	
	/**
	 * Getter of all the users connected in some chat room
	 * 
	 * @param nameChatRoom
	 * @return the list of all users connected in a chatroom
	 */
	public static List<User> getNameConnectedUsers(String nameChatRoom)
			throws ClassNotFoundException, SQLException, IOException {

		List<User> listUsers = new ArrayList<User>();
		CopyOnWriteArraySet<ServerChat> listConnectedUsers = chatRooms.get(nameChatRoom);
		for (ServerChat user : listConnectedUsers) {
			listUsers.add(User.searchUserByNickname(user.nickname));
		}

		return listUsers;
	}

}
