import java.sql.*;
public class SQLConnection {
    public static void main(String[] args) {
        String url="jdbc:mysql://localhost:3306/Eb_Bill";
        String username="root";
        String password="";
    

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection= DriverManager.getConnection(url,username,password);

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select * Customer_info");

            while(resultSet.next()){
                System.out.println(resultSet.getString(1)+" "+resultSet.getString(2)+" "+resultSet.getString(3)+" "+resultSet.getString(4)+" "+resultSet.getString(5));
            }

        } catch (Exception e) {
            System.out.println(e);
        }
      
    } 
}