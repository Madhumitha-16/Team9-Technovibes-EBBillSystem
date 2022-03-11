package ebBillSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class GenerateBill {
	public void displayBill(String url,String username,String pass,int consumer_no) throws ClassNotFoundException, SQLException{
 		Scanner sc = new Scanner(System.in);
		Connection connection = DriverManager.getConnection(url,username,pass);
		Statement statement = connection.createStatement();
		System.out.println("Enter your previous Month Reading : ");
		int previousMonthReading = sc.nextInt();
		System.out.println("Enter your Current Month Reading : ");
		int CurrentMonthReading = sc.nextInt();
		String query="Update Customer set previousMonthreading='"+previousMonthReading+"',CurrentMonthReading = '"+CurrentMonthReading+"'  where UserID = '" +consumer_no+ "' ";
		statement.executeUpdate(query);
		ResultSet resultSet = statement.executeQuery("select * from Customer where UserID='"+consumer_no+"' ");
		boolean res=resultSet.next();
		if(res) {
			int Cur_Unit=resultSet.getInt(9);
			int Prev_Unit=resultSet.getInt(8);
			int units=Cur_Unit - Prev_Unit;
			double amount = calculateBillAmount(url,username,pass,consumer_no);
			System.out.println("---------------------------------------------BILL--------------------------------------------------");
			System.out.println("   Consumer No."+"     "+"First Name"+"     "+"Last Name"+"       "+"Units Consumed"+"       "+"Total Amount"+"        "+"Status");
			System.out.println("---------------------------------------------------------------------------------------------------");
		    System.out.println("     "+resultSet.getInt(6)+"                "+resultSet.getString(2)+"          "+resultSet.getString(3)+"               "+units+"                   "+amount+"           "+resultSet.getString(11));
			System.out.println("---------------------------------------------------------------------------------------------------");
		}else{
			System.out.println("No such user exits");
		}
		
	}
	
	public double calculateBillAmount(String url,String username,String pass,int consumer_no) throws SQLException {
		Connection connection = DriverManager.getConnection(url,username,pass);
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("select * from Customer where UserID='"+consumer_no+"' ");
		resultSet.next();
		String res=resultSet.getString(7);
		double tarrif=0.00;
		if(res.equals("H"))
        {
			int Cur_Unit=resultSet.getInt(9);
			int Prev_Unit=resultSet.getInt(8);
			int units=Cur_Unit - Prev_Unit;
            if(units<101)
            {
                tarrif=units * 1.0;
            }
            else if(units>100 && units<201)
            {
                tarrif=100*1.0 + (units-100)*2.5;
            }
            else if(units>200 && units <501)
            {
                tarrif=100*1.0 + 100*2.5 + (units-200)*4.0;
            }
            else if(units>500)
            {
                tarrif=100*1.0 + 100*2.5 + 300*4.0 + (units-500)*6.0;
            }
        }
        else
        {
        	int Cur_Unit=resultSet.getInt(10);
			int Prev_Unit=resultSet.getInt(9);
			int units=Cur_Unit - Prev_Unit;
			System.out.println(units+ "Units Consumed");
            if(units<101)
            {
                tarrif=units*2.0;
            }
            else if(units>100 && units<201)
            {
                tarrif=100*2.0 + (units-100)*4.50;
            }
            else if(units>200 && units<501)
            {
                tarrif=100*2.0 + 100*4.50+(units-200)*6.0;
            }
            else if(units>500)
            {
                tarrif=100*2.0 + 100*4.50 + 300*6.0 +(units-500)*7.0;
            }
        }
		statement.executeUpdate("Update Customer set totalAmount='"+tarrif+"' where UserID = '" +consumer_no+ "' ");
		resultSet.close();
		return tarrif;
        
    }
	
	
}
