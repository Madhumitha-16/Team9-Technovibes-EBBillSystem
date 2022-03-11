package ebBillSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Register {
	public void register(String url,String username,String pass) throws ClassNotFoundException, SQLException{
	 	Connection connection = DriverManager.getConnection(url,username,pass);
	 		Scanner sc = new Scanner(System.in);
			System.out.println("Enter your firstname : ");
			String firstname = sc.next();
			System.out.println("Enter your lastname : ");
			String lastname = sc.next();
			System.out.println("Enter your Address : ");
			String address = sc.next();
			System.out.println("Enter your Mobile Number : ");
			double mobileNo = sc.nextDouble();
			System.out.println("Enter your User ID : ");
			int UserID = sc.nextInt();
			System.out.println("Enter your Type of Connection \n(H - Household)\n(C - Commercial)  :");
			String type = sc.next();
			Statement statement = connection.createStatement();
			String queryCheck = "SELECT * from Customer WHERE UserID = '" +UserID+ "' " ;
			ResultSet rs = statement.executeQuery(queryCheck); 
			// if this consumer_no already exists, print registered
			if(rs.next()) {
			     System.out.println("Already Exists");
			}
			else {
				String query="insert into Customer (firstname,lastname,address,mobileNo,UserID,type) values('"+firstname+"', '"+lastname+"','"+address+"','"+mobileNo+"','"+UserID+"','"+type+"');";
				statement.executeUpdate(query);
				System.out.println("Registered Successfully!!");
			}
			
			Class.forName("com.mysql.cj.jdbc.Driver");			
   
			statement.close();
			connection.close();
			sc.close();
			}

}
