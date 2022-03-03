import java.sql.*;
import java.util.Scanner;

public class ElectricityBill {
	Scanner sc = new Scanner(System.in) ;
	public static void main(String[] args)throws Exception {
        try{
		
		 Scanner sc = new Scanner(System.in) ;
            String url = "jdbc:mysql://localhost:3306/EbBill";
            String username = "root";
            String pass = "madhu";
            System.out.println("--------------EB-Bill-System------------------\n");
            while(true) {
            System.out.println("\n1. Registeration");
            System.out.println("2. View Bill \n3. Payment \n4. Exit");
            System.out.println("Enter your choice : ");
            int c = sc.nextInt();
                switch(c){
                case 1:
                    System.out.println("Enter Your Details");
                    register(url,username,pass);
                    break;
               case 2:
            	   System.out.println("Enter Consumer Number: ");
            	   	int consumer_no=sc.nextInt();
            	   	displayBill(url,username,pass,consumer_no);
            	   	break;
               case 3:
            	   System.out.println("Enter Consumer Number: ");
            	   int consumer_no1=sc.nextInt();
            	   payment(url,username,pass,consumer_no1);
            	   break;
               case 4:
            	   System.out.println("Enter Consumer Number: ");
//            	   int consumer_No=sc.nextInt();
//            	   calculateBillAmount(url,username,pass,consumer_No);
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
        
	
	
	public static void register(String url,String username,String pass) throws ClassNotFoundException, SQLException{
		 	Connection connection = DriverManager.getConnection(url,username,pass);
		 	Scanner sc = new Scanner(System.in);
		    System.out.println("Enter your firstname : ");
		    String firstname = sc.next();
            System.out.println("Enter your lastname : ");
		    String lastname = sc.next();
		    System.out.println("Enter your Address : ");
		    String address = sc.next();
		    System.out.println("Enter your Mobile Number : ");
		    int mobile_No = sc.nextInt();
            System.out.println("Enter your User ID : ");
		    int UserID = sc.nextInt();
		    System.out.println("Enter your previous Month Reading : ");
		    int previousMonthReading = sc.nextInt();
		    System.out.println("Enter your Current Month Reading : ");
		    int CurrentMonthReading = sc.nextInt();
		    System.out.println("Enter your Type of Connection :");
		    String type = sc.next();
		    Statement statement = connection.createStatement();
		    String queryCheck = "SELECT * from Customer WHERE UserID = '" +UserID+ "' " ;
		    ResultSet rs = statement.executeQuery(queryCheck); //execute the query, and get a java resultset
		    // if this consumer_no already exists, print registered
		    if(rs.next()) {
		         System.out.println("Already Exists");
		    }
		    else {
		    	String query="insert into Customer (firstname,lastname,address,UserID,password,type,previousMonthreading,CurrentMonthReading) values('"+firstname+"', '"+lastname+"','"+address+"','"+UserID+"', '"+pass+"','"+type+"','"+previousMonthReading+"','"+CurrentMonthReading+"');";
		    	int result = statement.executeUpdate(query);
		    	System.out.println("Registered Successfully!!");
		    }
		    
		    Class.forName("com.mysql.cj.jdbc.Driver");			
         
			statement.close();
			connection.close();
	}
	
	
	
	public static void displayBill(String url,String username,String pass,int consumer_no) throws ClassNotFoundException, SQLException{
		Connection connection = DriverManager.getConnection(url,username,pass);
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("select * from Customer where UserID='"+consumer_no+"' ");
		boolean res=resultSet.next();
		if(res) {
			int Cur_Unit=resultSet.getInt(9);
			int Prev_Unit=resultSet.getInt(8);
			int units=Cur_Unit - Prev_Unit;
			System.out.println("---------------------------BILL RECEIPT------------------------------");
			System.out.println("Consumer No."+"   "+"First Name"+"   "+"Last Name"+"       "+"Units Consumed"+"   "+"Status");
			System.out.println("---------------------------------------------------------------------");
            System.out.println(resultSet.getInt(5)+"                "+resultSet.getString(2)+"          "+resultSet.getString(3)+"       "+units+"       "+resultSet.getString(10));
		}else{
			System.out.println("No such user exits");
		}
	}
	
//	private static void calculateBillAmount(String url,String username,String pass,int consumer_No) throws SQLException {
//		Scanner sc= new Scanner(System.in);
//		Connection connection = DriverManager.getConnection(url,username,pass);
//		Statement statement = connection.createStatement();
//		ResultSet resultSet = statement.executeQuery("select * from Customer where UserID='"+consumer_No+"' ");
//		resultSet.next();
//		String res=resultSet.getString(8);
//		String typeCon="H";
//		double tarrif=0.00;
//		if(res.equals(typeCon)) {
//					
//			int Cur_Unit=resultSet.getInt(9);
//			int Prev_Unit=resultSet.getInt(8);
//			int units=Cur_Unit - Prev_Unit;
//			if(units<101)
//            {
//                tarrif=units * 1.0;
//            }
//            else if(units>100 && units<201)
//            {
//                tarrif=100*1.0 + (units-100)*2.5;
//            }
//            else if(units>200 && units <501)
//            {
//                tarrif=100*1.0 + 100*2.5 + (units-200)*4.0;
//            }
//            else if(units>500)
//            {
//                tarrif=100*1.0 + 100*2.5 + 300*4.0 + (units-500)*6.0;
//            }
//		}else {
//			
//
//			int Cur_Unit=resultSet.getInt(9);
//			int Prev_Unit=resultSet.getInt(8);
//			int units=Cur_Unit - Prev_Unit;
//			if(units<101)
//            {
//                tarrif=units*2.0;
//            }
//            else if(units>100 && units<201)
//            {
//                tarrif=100*2.0 + (units-100)*4.50;
//            }
//            else if(units>200 && units<501)
//            {
//                tarrif=100*2.0 + 100*4.50+(units-200)*6.0;
//            }
//            else if(units>500)
//            {
//                tarrif=100*2.0 + 100*4.50 + 300*6.0 +(units-500)*7.0;
//            }
//        }
//		
//          System.out.println(String.format("%,.2f", tarrif));
//		}
//	

	
	public static void printReceipt (String url,String username,String pass) throws ClassNotFoundException, SQLException{
		//To Be developed
	}
	
	
	
	public static void payment (String url,String username,String pass,int consumer_no1) throws ClassNotFoundException, SQLException{
		Connection connection = DriverManager.getConnection(url,username,pass);
		Statement statement = connection.createStatement();
		String u_query = "UPDATE Customer SET status = 'Paid' WHERE UserID = '"+consumer_no1+"'"; 
		statement.executeUpdate(u_query);
		ResultSet resultSet = statement.executeQuery("select * from Customer where UserID='"+consumer_no1+"' ");
		while(resultSet.next()){
            System.out.println(resultSet.getString(10));
	}
}
}

