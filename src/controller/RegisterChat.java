package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import config.MyConnection;
import model.Chat;
import model.User;

/**
 * Servlet implementation class RegisterChat
 */
@WebServlet("/RegisterChat")
public class RegisterChat extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterChat() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// Read parameters sended by the user
		Vector<String> members = new Vector<String>();
		int id = 0;
		Map m = request.getParameterMap();
		Set s = m.entrySet();
		Iterator it = s.iterator();

		while (it.hasNext()) {

			Map.Entry<String, String[]> entry = (Map.Entry<String, String[]>) it.next();

			String key = entry.getKey();
			String[] value = entry.getValue();

			if (!key.equals("title") && !key.equals("description") && !key.equals("startDate")
					&& !key.equals("endDate") && !key.equals("members")) {
				members.add(key);
			}

		}
		
		// Format to the Date information and filter data
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		String title = request.getParameter("title").replace(" ", "");
		String description = request.getParameter("description");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		
		// Create New Chat in the database
		try {
 
			Chat chat = new Chat(title, description,
					format.parse(startDate.replace("T", " ")),
					format.parse(endDate.replace("T", " ")));
			
			chat.save();

			Connection conn = MyConnection.getInstance();

			Statement stmt = conn.createStatement();

			ResultSet res = stmt.executeQuery("SELECT id FROM Chat WHERE titre = '" + title + "'");

			if (res.next()) {

				id = Integer.parseInt(res.getString("id"));

			}

			stmt = conn.createStatement();
			stmt.executeLargeUpdate("INSERT INTO owner(email, id, owner) VALUES ('"
					+ request.getSession().getAttribute("email") + "', " + id + ", 1)",
					Statement.RETURN_GENERATED_KEYS);

			for (int i = 0; i < members.size(); i++) {
				stmt = conn.createStatement();
				
				User currentUser = User.searchUserByNickname(members.elementAt(i));
				
				stmt.executeLargeUpdate(
						"INSERT INTO owner(email, id) VALUES ('" + currentUser.getEmail() + "', " + id + ")",
						Statement.RETURN_GENERATED_KEYS);
			}

			RequestDispatcher rd = request.getRequestDispatcher("myChats.jsp");

			rd.forward(request, response);

		} catch (ClassNotFoundException | SQLException |

				IOException | ParseException e) {
			e.printStackTrace();
		}

	}

}
