package visualize;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class DbConn {

	public String query = null;
	public static String url 		= "jdbc:mysql://localhost:3306/mondial";
	public static String user 	= "root";
	public static String password = "kec1148!";
	
	/*public static String url 		= "jdbc:mysql://mysql.freehostingnoads.net:3306/u174323470_dbms";
	public static String user 	= "u174323470_dbms";
	public static String password = "123456";
	*/
	public static Statement connDB() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(url,user,password);
		System.out.println("Connecting to database...");
		Statement stmt = conn.createStatement();
		return stmt;

	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<HashMap> returnJson(String query,String column) throws Exception {
		List<HashMap> list = new ArrayList<HashMap>();
		Statement stmt = connDB();
		String sql = query;
		System.out.println(sql);
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			HashMap map =  new HashMap();
			String name = rs.getString("Name");
			map.put("name",name);
			if (column.equals("Population_Growth")) {
				Double col = Double.parseDouble(rs.getString(column));
				map.put("value",col);
			} else { 
				Integer col 	  = Integer.parseInt(rs.getString(column));
				map.put("value",col);
			}
			list.add(map);
		}
		System.out.println("New "+list);
		return list;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<HashMap> returnCitiesJson(String query) throws Exception {
		List<HashMap> list = new ArrayList<HashMap>();
		Statement stmt = connDB();
		String sql = query;
		System.out.println(sql);
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			HashMap map =  new HashMap();
			String  city = rs.getString("Name");
			Integer population 	  = Integer.parseInt(rs.getString("Population"));
			map.put("name",city);
			map.put("value",population);
			list.add(map);
		}
		System.out.println("New "+list);
		return list;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<HashMap> returnGDPJson(String query,String column) throws ClassNotFoundException, SQLException {
		List<HashMap> list = new ArrayList<HashMap>();
		Statement stmt = connDB();
		String sql = query;
		System.out.println(sql);
		ResultSet rs = stmt.executeQuery(sql);
		double c = 0.0;
		while (rs.next()) {
			HashMap map =  new HashMap();
			String  country = rs.getString("Country");
			map.put("name",country);
			if (column.equalsIgnoreCase("Agriculture")) {
				c = Double.parseDouble(rs.getString(column));
				map.put("y",c );

			} else {
				Integer col 	  = Integer.parseInt(rs.getString(column));
				map.put("y",col );
			}
			list.add(map);
		}
		System.out.println("New "+list);
		return list;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<HashMap> returnRiverJson(String query) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		List<HashMap> list = new ArrayList<HashMap>();
		Statement stmt = connDB();
		String sql = query;
		System.out.println(sql);
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			HashMap map =  new HashMap();
			String  name = rs.getString("Name");
			Integer length 	  = Integer.parseInt(rs.getString("Length"));
			map.put("name",name);
			map.put("y",length );
			list.add(map);
		}
		System.out.println("New "+list);
		return list;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<HashMap> returnProvinceCity(String query) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		List<HashMap> list = new ArrayList<HashMap>();
		Statement stmt = connDB();
		String sql = query;
		System.out.println(sql);
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			HashMap map =  new HashMap();
			String  origin = rs.getString("Province");
			Integer count 	  = Integer.parseInt(rs.getString("Population"));
			String  carrier = rs.getString("Name");
			map.put("origin",origin);
			map.put("count",count );
			map.put("carrier", carrier);
			list.add(map);
		}
		return list;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<HashMap> returnJson(String query) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		List<HashMap> list = new ArrayList<HashMap>();
		Statement stmt = connDB();
		String sql = query;
		System.out.println(sql);
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			HashMap map =  new HashMap();
			String  name = rs.getString("Name");
			Integer length 	  = Integer.parseInt(rs.getString("Population"));
			map.put("name",name);
			map.put("value",length );
			list.add(map);
		}
		System.out.println("New "+list);
		return list;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<HashMap> returnJson(String query, String string, String string2) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		List<HashMap> list = new ArrayList<HashMap>();
		Statement stmt = connDB();
		String sql = query;
		System.out.println(sql);
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			HashMap map =  new HashMap();
			String  name = rs.getString("Name");
			Integer area 	  = Integer.parseInt(rs.getString(string));
			Integer height    = Integer.parseInt(rs.getString(string2));
			map.put("Country",name);
			map.put("Area",area);
			map.put("Height", height);
			list.add(map);
		}
		System.out.println("New "+list);
		return list;
	}
	
//	public static void main(String[] args) throws Exception {

		//		Connection conn = null;
//		Statement stmt = null;
//		
//		HashMap<String,Integer> map =  new HashMap<String,Integer>();
//			//STEP 2: Register JDBC driver
//			Class.forName("com.mysql.jdbc.Driver");
//			//STEP 3: Open a connection
//			System.out.println("Connecting to database...");
//			conn = DriverManager.getConnection(url,user,password);
//			Statement st = conn.createStatement();
//			String sql = "select * from city LIMIT 5;";
//		    ResultSet rs = st.executeQuery(sql);
//		    while(rs.next()) {
//		    	String city = rs.getString("Name");
//		    	Integer population = Integer.parseInt(rs.getString("Population"));
//		    	map.put(city, population);
//		    }
//		    JSONObject json = new JSONObject(map);
//		    System.out.println(json);
//		   // System.out.println(json.get("Aarri"));
//		    //return json;
//	}

}
