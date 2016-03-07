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
 * Servlet implementation class Economy
 */
@WebServlet("/Economy")
public class Economy extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Economy() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//			param = "select Country, GDP from economy where GDP IS NOT NULL ORDER BY GDP DESC LIMIT 5;";
		JSONArray json = null;
		List<HashMap> list = null;
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("largesteconomy","select Country, GDP from economy where GDP IS NOT NULL ORDER BY GDP DESC LIMIT 5;");
		map.put("agrieconomy","select * from economy ORDER BY Agriculture desc LIMIT 10;");
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

	private List<HashMap> executeQuery(String param, String query) {
		// TODO Auto-generated method stub
		DbConn db = new DbConn();
		List<HashMap> list = null;
		try {
			if (param.equals("largesteconomy")) {
				list =	db.returnGDPJson(query,"GDP");
			} 
			if (param.equals("agrieconomy")) {
				list =	db.returnGDPJson(query,"Agriculture");
			}
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
