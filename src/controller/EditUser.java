package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;

/**
 * Servlet implementation class EditUser
 */
@WebServlet("/EditUser")
public class EditUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Read information sended by the user
		String lastEmail = request.getParameter("lastEmail");
		String newEmail = request.getParameter("email");
		String nickname = request.getParameter("nickname");
		String role = request.getParameter("role");
		
		// Create new user and set the information read before
		User user = User.searchUser(lastEmail);
		user.setEmail(newEmail);
		user.setNickname(nickname);
		user.setRole(role);
		
		// Update the database
		try {
			user.save();
		} catch (ClassNotFoundException | SQLException | IOException e) {

			e.printStackTrace();
		}
		
		// Redirect user to the Admin page
		RequestDispatcher rd = request.getRequestDispatcher("admin.jsp");
		rd.include(request, response);

	}

}
