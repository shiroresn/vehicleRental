package Image;

import java.awt.Image;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.utils.Bytes;

/**
 * Servlet implementation class ImageDisplay
 */
@WebServlet("/ImageDisplay")
public class ImageDisplay extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImageDisplay() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").build();

		Session session = cluster.connect();
		
		session.execute("use people");
		
		
		
		PreparedStatement ps = session.prepare("select user,image,imagelength from Messages where user =?");
		BoundStatement boundStatement = new BoundStatement(ps);
		ResultSet rs =session.execute ( boundStatement.bind("Andy"));
		
		ByteBuffer bImage=null;
		int length = 0;
		for (Row row : rs) {
		 bImage = row.getBytes("image") ;
		 
		 length=row.getInt("imagelength");
		}
		
		 byte image[]= new byte[length];
		 image=Bytes.getArray(bImage);
		
	
		 System.out.println("image get process successful");
	
		 	}


	}

