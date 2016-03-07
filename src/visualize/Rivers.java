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
 * Servlet implementation class Rivers
 */
@WebServlet("/Rivers")
public class Rivers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Rivers() {
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
		map.put("riversChart","select Name, Length from river where Length IS NOT NULL ORDER BY Length desc LIMIT 10;");
		map.put("altituderivers","select Name, SourceAltitude from river where `SourceAltitude` IS NOT NULL ORDER BY SourceAltitude desc limit 10;");
		
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

	@SuppressWarnings("rawtypes")
	private List<HashMap> executeQuery(String param, String query) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(param + " "+ query);
		DbConn db = new DbConn();
		List<HashMap> list = null;
		try {
			if (param.equals("altituderivers")) {
				list =	db.returnJson(query,"SourceAltitude");
			} 
			else	 {
				list = db.returnJson(query, "Length");
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
