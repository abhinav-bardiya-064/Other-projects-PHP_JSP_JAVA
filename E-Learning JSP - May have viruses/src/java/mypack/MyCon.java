package mypack;


import java.sql.*;


public class MyCon 
{

    
 private static Connection con;

    
 static {
        
         try {
            
              Class.forName("com.mysql.jdbc.Driver");
            
              con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");
                 }  
             catch (Exception e) 
             {
           
              e.printStackTrace();
        
             }
   
              }

   
            public static Connection getConnection() throws Exception 
           {
        
            if (con.isClosed()) 
            {
           
             con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");
                 }
         
            return con;
    
           }

}
