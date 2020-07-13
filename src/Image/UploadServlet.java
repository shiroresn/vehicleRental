package Image;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;

/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		
		String name = request.getParameter("name");
		String address = request.getParameter("fileToUpload");
				
		System.out.println("name"+name);
		System.out.println("file"+address);
		
		
		System.out.println("Start");
		
		FileInputStream fis=new FileInputStream(address);
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
		session.execute(  boundStatement.bind( buffer, name, length));
		
		System.out.println("Image inserted to database");
	}

}
