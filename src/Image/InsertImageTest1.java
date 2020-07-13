package Image;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;

public class InsertImageTest1 {
	
public static void main(String[] args) throws IOException {
	
	System.out.println("Start");
	
	FileInputStream fis=new FileInputStream("G:\\20141217_162853.jpg");
	byte[] b= new byte[fis.available()+1];
	int length=b.length;
	fis.read(b);
	
	
	ByteBuffer buffer =ByteBuffer.wrap(b);
	

	System.out.println("image read process completed");
	
	Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
	
	
	Session session = cluster.connect();
	
	session.execute("use people");
	
	PreparedStatement ps = session.prepare("insert into Messages ( image, user, imagelength) values(?,?,?)");
	BoundStatement boundStatement = new BoundStatement(ps);
	session.execute(  boundStatement.bind( buffer, "Shubham", length));
	
	System.out.println("Image inserted to database");
}
}
