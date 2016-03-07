package visualize;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class Conn {
	public static String url 	  = "jdbc:mysql://127.9.77.2:3306/mysql";
	public static String user 	  = "adminSKr7Tbz";
	public static String password = "h1xhapkqHyl5";

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(url,user,password);
		System.out.println("Connecting to database...");
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select * from country LIMIT 10;");
		while (rs.next()) {
			HashMap map =  new HashMap();
			String name = rs.getString("Name");
			Integer col 	  = Integer.parseInt(rs.getString("Area"));
			map.put("name",name);
			map.put("value",col);
			System.out.println(map);

		}
	}

}
