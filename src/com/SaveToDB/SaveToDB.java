package com.SaveToDB;

import com.Connection.Connection;
import com.User.User;
import com.datastax.driver.core.Session;

public class SaveToDB {

public void toSave(User u)
{
	//Connection c = new Connection();
	Connection c = new Connection();
	Session session = c.getConnection();

	session.execute("use people");
	String query = "insert into user (email,password) values ('"+u.getEmail()+"','"+u.getPassword()+"');";
	session.execute(query);
	System.out.println("Saved to Database!");
}

public static void main(User u) {
	SaveToDB saveToDB = new SaveToDB();
	saveToDB.toSave(u);
}

}
