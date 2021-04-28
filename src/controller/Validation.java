package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;

@WebServlet("/Validation")
public class Validation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Validation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		User user = User.searchUser(email);	
			
			if(user != null) {
				
				// Wrong Password
				if(password.equals(user.getPassword()) == false) {
					
					System.out.println("Wrong");
					request.setAttribute("response", "Wrong_Password");
					
					RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
					rd.include(request, response);
					
				}
				
				// OK Credentials
				else {
					
					RequestDispatcher rd = request.getRequestDispatcher("/home.jsp");
					
					HttpSession session=request.getSession();  
			        session.setAttribute("username", user.getNickname());  
			        session.setAttribute("password", user.getPassword());
			        session.setAttribute("email", user.getEmail());
			        session.setAttribute("role", user.getRole());
			        
					rd.forward(request, response);
					
				}
				
			}
			
			// Not on the DB
			else {
				
				request.setAttribute("response", "Not_DB");
				
				RequestDispatcher rd = request.getRequestDispatcher("/home.jsp");
				
				rd.forward(request, response);
				
			}
			

	}

}
