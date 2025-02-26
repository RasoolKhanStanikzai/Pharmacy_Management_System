package DBConnection;
import java.sql.*;
import javax.swing.JOptionPane;
public class DatabaseQueries{
    Connection con=DatabaseConnection.connectDB();
    PreparedStatement stmt;
    
    public PreparedStatement insertUpdateDelete(String query)throws SQLException 
    {
      stmt=con.prepareStatement(query);
      stmt.execute();
      stmt.close();
      return null;
    }
    
    public ResultSet selectData(String query)
    {
        try
        {
            stmt=con.prepareStatement(query);
            ResultSet rSet=stmt.executeQuery();
            return rSet;
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex);
        }
        return null;
    }

    public PreparedStatement insertUpdateDelete() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}