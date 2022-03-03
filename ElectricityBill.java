import java.sql.*;
import java.util.Scanner;

public class ElectricityBill {

	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String url = "jdbc:mysql://localhost:3306/ebbill";
		String username = "root";
		String pass = "";
		System.out.println("\n ----------TAMILNADU E-bill-----------\n\n\t1. Register \n\t2. View Bill \n\t3. Exit");
		System.out.println("Enter your choice : ");
		int c = sc.nextInt();
	
		    switch(c){
	        case 1:
	            System.out.println("-----New User Register----------");
//	            register(url,username,pass);
	            break;
	       case 2:
//	    	   	displayBill(url,username,pass);
	    	   	break;
	       case 3:
	           	System.exit(0);
	       default:
	    	   System.out.println("\nSelect a vslid option!!");
	    } 
	}
}
		

	