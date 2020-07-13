package Image;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Scanner;

import com.Connection.Connection;
import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;

public class InsertImageTest {
public static void main(String[] args) throws IOException {
	
	InsertImageTest imageTest = new InsertImageTest();
    imageTest.insertImage();

    /*
    
*/
    
}

public void insertImage() throws IOException {
    Connection c = new Connection();
	Session session = c.getConnection();
	session.execute("use people");
	
	System.out.println("Enter id : ");
	Scanner sc = new Scanner(System.in);
	int id = sc.nextInt();
	
	session.execute("use people");
	

	FileInputStream fis=new FileInputStream("C:\\Users\\Shubham\\Desktop\\bg.jpg");
	byte[] b= new byte[fis.available()+1];
	int length=b.length;
	fis.read(b);
	
	ByteBuffer buffer =ByteBuffer.wrap(b);

	/*
	String query = "Insert into ImageDatabase (id,img) values ("+id+",'"+buffer+")";
	session.execute(query);
*/
	
	PreparedStatement ps = session.prepare("insert into Messages (id,img) values(?,?)");
	BoundStatement boundStatement = new BoundStatement(ps);
	session.execute(  boundStatement.bind(id, buffer));
}
}
