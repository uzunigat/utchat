package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Chat;

/**
 * Servlet implementation class EditChat
 */
@WebServlet("/EditChat")
public class EditChat extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditChat() {
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// Read parameters to update
		String title = request.getParameter("title2").replace(" ", "");
		String description = request.getParameter("description2");
		String startDate = request.getParameter("startDate2").replace("T", " ");
		String endDate = request.getParameter("endDate2").replace("T", " ");
		
		// Create Chat object
		DateFormat format = new SimpleDateFormat("YYYY-MM-dd HH:mm");
		Chat chat = Chat.searchChat(title);

		chat.setDescription(description);
		
		// Update Info in the Database
		try {
			chat.setStartDate(format.parse(startDate));
			chat.setEndDate(format.parse(endDate));
			chat.save();
		} catch (ParseException | ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}
		
		// Redirect user to MyChats page
		RequestDispatcher rd = request.getRequestDispatcher("myChats.jsp");
		rd.include(request, response);

	}

}
