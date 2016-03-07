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
 * Servlet implementation class Connection
 */
@WebServlet("/Connection")
public class Connection extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Connection() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response){
		JSONArray json = null;
		List<HashMap> list = null;
		// Island top 10
		//String param = "select Name, Area  from island where Area IS NOT NULL ORDER BY Area DESC LIMIT 10 ;";
		String param = request.getParameter("query");
		// Island Top 10 = select Name, Area from island ORDER BY Area LIMIT 10;
		if ( param.equalsIgnoreCase("largestislands")) {
			param = "select Name, Area  from island where Area IS NOT NULL ORDER BY Area DESC LIMIT 10 ;";
		
//		try {
//			list = resultsRiver(param);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		 }
		}
		if (param.equalsIgnoreCase("top10cities")) {
			param = " select * from city where Population IS NOT NULL && Population > 0 ORDER BY Population DESC LIMIT 10;";
			//param = " select * from city where Population IS NOT NULL && Population > 0 ORDER BY Population DESC;";

			try {
				list = returnCities(param);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			 }
		}
		
		if (param.equalsIgnoreCase("largestrivers")) {
			param = "select * from river where Length IS NOT NULL ORDER BY Length desc LIMIT 10";
			try {
				list = returnRivers(param);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			 }
		}
		
		if (param.equalsIgnoreCase("provinceofcities")) {
			param = "select Name, Province, Population from city where Population IS NOT NULL AND Province=\"Nigeria\" OR Province = \"Sao Paulo\" OR Province = \"Texas\" OR Province = \"Para\" ORDER BY Province LIMIT 140;";
			try {
				list = returnProvinceCity(param);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			 }
		}
		
		try {
			json = new JSONArray(list);
			response.getWriter().write(json.toString());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
	private List<HashMap> returnProvinceCity(String param) {
		// TODO Auto-generated method stub
		DbConn db = new DbConn();
		List<HashMap> list = null;
		try {
			list =	db.returnProvinceCity(param);
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;	
	}

	@SuppressWarnings("rawtypes")
	private List<HashMap> returnRivers(String param) throws Exception {
		DbConn db = new DbConn();
		List<HashMap> list = null;
		try {
			list =	db.returnRiverJson(param);
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;	
	}

	
	@SuppressWarnings("rawtypes")
	public List<HashMap> returnCities(String param) throws Exception {
		DbConn db = new DbConn();
		List<HashMap> list = null;
		try {
			list =	db.returnCitiesJson((param));
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;	
	}
	@SuppressWarnings("rawtypes")
//	public List<HashMap> resultsRiver(String param) throws Exception {
//		DbConn db = new DbConn();
//		List<HashMap> list = null;
//		try {
//			list =	db.returnIslandJson(param);
//		} catch (SQLException | ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return list;
//	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
