package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;

@WebServlet("/ValidationRegister")
public class ValidationRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ValidationRegister() {
        super();

    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("email");
		String nickname = request.getParameter("username");
		String password = request.getParameter("password");
		
		System.out.println(User.searchUser(email).toString());
		
		if(User.searchUser(email) == null) {
			
			User user = new User(email, nickname, password, "Other");
			
			try {
				user.save();
				RequestDispatcher rd = request.getRequestDispatcher("./home.jsp");
				
				HttpSession session=request.getSession();  
				session.setAttribute("email", user.getEmail()); 
		        session.setAttribute("username", nickname);  
		        session.setAttribute("password", password);
		        session.setAttribute("role", user.getRole()); 

				rd.forward(request, response);
				
			} catch (ClassNotFoundException | SQLException | IOException e) {

				e.printStackTrace();
			}
			
		}
		
		else {
			
			request.setAttribute("response", "Already_Added");
			
			RequestDispatcher rd = request.getRequestDispatcher("/register.jsp");
			rd.forward(request, response);
			
		}
	}

}
