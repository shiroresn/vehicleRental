package com.Login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Connection.Connection;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection a = new Connection();
		Session session = a.getConnection();
		session.execute("use people");
		
		String email = request.getParameter("unamel");

		String password = request.getParameter("psw");
		
		String query1 = "select email, password from VUser where email = '"+email+"'";
		ResultSet rs = null;
		rs = session.execute(query1);
		
		
		if(rs.all().get(2).equals(password))
		{
			System.out.println("Login Sucessful");
		}
	}

}
