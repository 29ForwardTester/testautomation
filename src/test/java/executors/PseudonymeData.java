package executors;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import constants.CurrentConstants;

public class PseudonymeData {
	
	//this code aims to pseudonymize input data from excel sheet (or directly from DB).
	// from DB: given the column names of sensitive data, the information is replaced by other impersonal identifiers
	
	public static Connection connection;
	public static Statement stat;
	public static ResultSet resSet;
	public static String dbDriver = "com.mysql.jdbc.Driver";
	
	public void establish_conn() {
		try {
			Class.forName(dbDriver);
			connection = DriverManager.getConnection(CurrentConstants.dbURL, CurrentConstants.dbUSERNAME, CurrentConstants.dbPASSWORD);
			System.out.println("connected successfully to the DB");
			stat = connection.createStatement();
			System.out.println("statement created");
		} catch(Exception e) {
			System.out.println(e.toString());
		}
	}
	
	public void pseudonymise (String columnName) throws SQLException {
		String querry = "select * from usertestdata";
		resSet = stat.executeQuery(querry);
		while(resSet.next()) {
			int id = resSet.getInt("id");
			String fname = resSet.getString("family_name");
			String bdate = resSet.getString("birthdate");
			//display original data
			System.out.println("id: " + id + "\n");
			System.out.println("family_name: " + fname + "\n");
			System.out.println("birthdate: " + bdate + "\n");
			
			
			
		}
	}

}
