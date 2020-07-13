package Image;

import java.nio.ByteBuffer;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.utils.Bytes;

public class ViewImage {
public static void main(String[] args) {
Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
	
	
	Session session = cluster.connect();
	
	session.execute("use people");
	
	PreparedStatement ps = session.prepare("select user,image,imagelength from Messages where user =?");
	BoundStatement boundStatement = new BoundStatement(ps);
	ResultSet rs =session.execute ( boundStatement.bind("Andy"));
	
	ByteBuffer bImage=null;
	for (Row row : rs) {
	 bImage = row.getBytes("image") ;
	 int length=row.getInt("imagelength");
	 byte image[]= new byte[length];
	 image=Bytes.getArray(bImage);
	 
	 System.out.println("image get process successful");
	 
	 

	 
	}
	

}
	
}
