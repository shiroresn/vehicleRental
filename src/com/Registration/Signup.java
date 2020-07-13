package com.Registration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Connection.Connection;
import com.User.VUser;
import com.datastax.driver.core.Session;

/**
 * Servlet implementation class Signup
 */
@WebServlet("/Signup")
public class Signup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Signup() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Connection a = new Connection();
		Session session = a.getConnection();
		session.execute("use people");
		
		if(request.getParameter("psw-repeat").equals(request.getParameter("psw")))
		{
		VUser u = new VUser();
		u.setName(request.getParameter("name"));
		u.setEmail(request.getParameter("email"));
		u.setMobile(request.getParameter("mobile"));
		u.setAddress(request.getParameter("address"));
		u.setCity(request.getParameter("city"));
		u.setPin(request.getParameter("pin"));
		u.setPassword(request.getParameter("psw"));
		
		String profilepic = request.getParameter("image");
		Path temp = Files.copy(Paths.get(profilepic), Paths.get("F:\\Workspace4_Cassendra\\VehicleRentralSystem1\\WebContent\\ProfilePic\\"+u.getMobile()+"_profile.png"));
		
		String query = "insert into VUser (name,email,mobile,address,city,pin,password) values ('"+u.getName()+"','"+u.getEmail()+"','"+u.getMobile()+"','"+u.getAddress()+"','"+u.getCity()+"','"+u.getPin()+"','"+u.getPassword()+"')";
		session.execute(query);

		
		System.out.println("Registration Sucessful");
		
		
		RequestDispatcher rd = request.getRequestDispatcher("LoginSuccessful.jsp");

		request.setAttribute("email", u.getEmail());
		
		rd.forward(request, response);
		}
	}
	
	

}
