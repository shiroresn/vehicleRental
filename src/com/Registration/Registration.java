package com.Registration;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.SaveToDB.SaveToDB;
import com.User.User;

/**
 * Servlet implementation class Registration
 */
@WebServlet("/Registration")
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Registration() {
        // TODO Auto-generated constructor stub
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		
		User u = new User();
		String email = request.getParameter("email");
		String pwd1 = request.getParameter("psw");
		String pwd2 = request.getParameter("psw-repeat");
		
		if(pwd1.equals(pwd2))
		{
		u.setEmail(email);
		u.setPassword(pwd1);
		}
		
		SaveToDB saveToDB = new SaveToDB();
		saveToDB.toSave(u);		

		
		RequestDispatcher rd = request.getRequestDispatcher("LoginSuccessful.jsp");

		request.setAttribute("email", u.getEmail());
		
		rd.forward(request, response);
	
		
	}

}
