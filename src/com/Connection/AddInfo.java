package com.Connection;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

public class AddInfo {
public static void main(String[] args) {
	Connection a = new Connection();
	Session session = a.getConnection();
	
	Scanner sc = new Scanner(System.in);
	
	session.execute("use people");
	/*
	System.out.println("Enter Name : ");
	String name = sc.next();
	System.out.println("Enter Email : ");
	String email = sc.next();
	
	String query = "insert into employees (id,name,email) values (now(),'"+name+"','"+email+"');";
	session.execute(query);
	sc.close();
	*/
	ResultSet rs = null;
	String query2 = "select * from employees";
	rs = session.execute(query2);
	
	//HashMap<Integer, String> data = new HashMap<>();
	List<Row> hm = rs.all();
	//data = (HashMap<Integer, String>) rs.all();
	
	//System.out.println(data);
	System.out.println(hm.size());
	for(int i=0;i<hm.size();i++)
	{
	System.out.print("Name : ");
	System.out.print(hm.get(i).getString(2) + "\n");
	System.out.print("Email : ");
	System.out.print(hm.get(i).getString(1) +"\n");
	System.out.print("ID : ");
	System.out.print(hm.get(i).getUUID(0) +"\n");
	}
	
sc.close();
}


}
