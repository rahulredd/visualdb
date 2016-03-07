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
 * Servlet implementation class Country
 */
@WebServlet("/Country")
public class Country extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Country() {
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
		map.put("largestarea","select Name, Area from country ORDER BY Area desc LIMIT 10;");
		map.put("populated","select Name, Population from country ORDER BY Population desc LIMIT 10;");
		map.put("tallestmountains", "select Name, Height from mountain ORDER BY Height desc LIMIT 10;");
		map.put("populationgrowth", "select Name, Population_Growth from country, population where country.`Code`=population.`Country` AND `Population_Growth` IS NOT NULL AND `Population_Growth` > -0.4 ORDER BY `Population_Growth` LIMIT 15;");
		map.put("leastpopulated","select Name, `Population` from population, country where population.`Country`=country.`Code` ORDER BY `Population` asc LIMIT 15;");
		
		
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
		DbConn db = new DbConn();
		List<HashMap> list = null;
		try {
			if (param.equals("largestarea")) {
				list =	db.returnJson(query,"Area");
			} 
			if (param.equals("populated") || param.equals("leastpopulated")) {
				list =	db.returnJson(query,"Population");
			}
			if (param.equals("tallestmountains")) {
				list =	db.returnJson(query,"Height");
			}
			if (param.equals("populationgrowth")) {
				list = db.returnJson(query, "Population_Growth");
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
