package ebBillSystem;

import java.sql.*;
import java.util.Scanner;

public class ElectricityBill {
	Scanner sc = new Scanner(System.in) ;
	public static void main(String[] args)throws Exception {
        try{
		    Scanner sc = new Scanner(System.in);
		    String url = "jdbc:mysql://localhost:3306/EbBill";
		    String username = "root";
		    String pass = "madhu";
		    System.out.println("\n---------------EB-Bill-System------------------\n");
		    while(true) {
		    System.out.println("\n1. Registeration");
		    System.out.println("2. Generate Bill \n3. Payment \n4. Exit");
		    System.out.println("Enter your choice : ");
		    int c = sc.nextInt();
                switch(c){
                case 1:
                	Register reg = new Register();
			System.out.println("Enter Your Details");
			reg.register(url,username,pass);
			break;
               case 2:
			System.out.println("Enter Consumer Number: ");
			GenerateBill genBill = new GenerateBill();
		        int consumer_no=sc.nextInt();
			genBill.displayBill(url,username,pass,consumer_no);
		        break;
               case 3:
            	   System.out.println("Enter Consumer Number: ");
            	   int consumer_no1=sc.nextInt();
            	   payment(url,username,pass,consumer_no1);
            	   break;
               case 4:
            	  
            	   System.out.println("Exiting......\nExited");
                   System.exit(0);
               default:
            	   System.out.println("\nSelect a valid option!!");
            }
         }
        }
        catch(Exception e){
        System.out.println(e);
        }
        } 
        

	public static void payment (String url,String username,String pass,int consumer_no) throws ClassNotFoundException, SQLException{
		Connection connection = DriverManager.getConnection(url,username,pass);
		Statement statement = connection.createStatement();
		String u_query = "UPDATE Customer SET status = 'Paid' WHERE UserID = '"+consumer_no+"'"; 
		statement.executeUpdate(u_query);
		ResultSet resultSet = statement.executeQuery("select * from Customer where UserID='"+consumer_no+"' ");
		while(resultSet.next()){
            System.out.println("Status: "+resultSet.getString(11));
	}
}
}

