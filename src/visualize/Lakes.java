package visualize;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

/**
 * Servlet implementation class Lakes
 */
@WebServlet("/Lakes")
public class Lakes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Lakes() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		JSONArray json = null;
		List<HashMap> list = null;
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("largestlakes","select Name, Area from lake where Area IS NOT NULL ORDER BY Area desc  LIMIT 10;");
		map.put("altitudelakes","select Name, Altitude from lake where Altitude IS NOT NULL ORDER BY Altitude desc LIMIT 10;");

		String param = request.getParameter("query");
		try {
			list = executeQuery(param,map.get(param));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			json = new JSONArray(list);
			response.getWriter().write(json.toString());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
		}

	private List<HashMap> executeQuery(String param, String query) throws Exception {
		// TODO Auto-generated method stub
		DbConn db = new DbConn();
		List<HashMap> list = null;
		try {
			if (param.equals("largestlakes")) {
				list =	db.returnJson(query,"Area");
			} 
			if (param.equals("altitudelakes")) {
				list =	db.returnJson(query,"Altitude");
			}
			
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
