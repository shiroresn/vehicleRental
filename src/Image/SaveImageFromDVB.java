package Image;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.codec.BinaryDecoder;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

public class SaveImageFromDVB {
public static void main(String[] args) throws IOException {
	Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").build();

	Session session = cluster.connect();
	
	session.execute("use people");
	
	ResultSet rs = null;
	
	System.out.println("Enter Name : ");
	Scanner sc = new Scanner(System.in);
	String name = sc.nextLine();
	
	String query = "select user,image from Messages where user = '"+name+"'";
	rs = session.execute(query);
	
	File image = new File("F:\\V\\"+name+".jpg");
	
	FileOutputStream fos = new FileOutputStream(image);
	
	byte[] buffer = new byte[1];
	
	List<Row> hm = rs.all();
	
	InputStream is =  (InputStream) hm.get(0);
	
	while(is.read(buffer)>0)
	{
		fos.write(buffer);
	}
	
	System.out.println("\"F:\\\\V\\\\\"+name+\".png\"");
	
	fos.close();
}
}
