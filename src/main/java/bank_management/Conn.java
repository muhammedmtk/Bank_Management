package bank_management;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Conn {
    Connection con;
    Statement st;

    public Conn(){
        String url = "jdbc:mysql://localhost:3306/bank_db";
        String uname = "root";
        String pass = "Root@12345!";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url,uname,pass);
            st = con.createStatement();
            System.out.println("Connection established");
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
