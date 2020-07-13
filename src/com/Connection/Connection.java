package com.Connection;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

public class Connection {
	
	public Session getConnection() {
		// TODO Auto-generated method stub
		Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
		
		Session session = cluster.connect();
		return session;
	}
	
public static void main(String[] args) throws Exception{
	String query = "create keyspace tp with replication"
			+ " = { 'class' : 'SimpleStrategy','replication_factor':2};";
	Connection a = new Connection();
	Session session = a.getConnection();
	
	session.execute(query);
	session.execute("use tp");
	System.out.println("KeySpace Created");
}
}
