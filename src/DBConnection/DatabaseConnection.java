package DBConnection;

import java.sql.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
public class DatabaseConnection{
    Connection conn=null;
    public static Connection connectDB()
    {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacyinventorysystem","root","");
            return conn;
        }
        catch(Exception ex)
        {
           Alert alert=new Alert(AlertType.ERROR);
           alert.setTitle("Pharmacy Management System");
           alert.setContentText("Database is not Connected! Please start server first or Import the Database file into Server.");
           alert.showAndWait();
           System.exit(0);
        }
       return null; 
    }
}

