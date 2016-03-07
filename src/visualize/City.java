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
 * Servlet implementation class City
 */
@WebServlet("/City")
public class City extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public City() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		JSONArray json = null;
		List<HashMap> list = null;
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("top10cities","select * from city where Population IS NOT NULL && Population > 0 ORDER BY Population DESC LIMIT 10;");
		map.put("top10least", "select * from city where Population IS NOT NULL && Population > 0 ORDER BY Population ASC LIMIT 10;");
		map.put("usatop", "select * from city where Country=\"USA\" ORDER BY Population DESC LIMIT 10;");
		map.put("provinceofcities", "select Name, Province, Population from city where Population IS NOT NULL AND Province=\"Nigeria\" OR Province = \"Sao Paulo\" OR Province = \"Texas\" OR Province = \"Para\" ORDER BY Province LIMIT 140;");
		map.put("biggestarea","select Name, Area  from island where Area IS NOT NULL ORDER BY Area DESC LIMIT 10 ;");
		map.put("height","select Name, Height from island ORDER BY Height DESC LIMIT 10;");

		String param = request.getParameter("query");
		list = executeQuery(param,map.get(param));
		try {
			json = new JSONArray(list);
			response.getWriter().write(json.toString());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	@SuppressWarnings("rawtypes")
	private List<HashMap> executeQuery(String param, String query) {
		// TODO Auto-generated method stub
		
		DbConn db = new DbConn();
		List<HashMap> list = null;
		if (param.equalsIgnoreCase("provinceofcities")) {
			try {
				list =	db.returnProvinceCity(query);
			} catch (SQLException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {

			try {
				list =	db.returnJson(query);
			} catch (SQLException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//return list;
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
