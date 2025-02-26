
package Supplier;

import DBConnection.DatabaseConnection;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import java.sql.*;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
public class UpdateSupplierController implements Initializable {

    // Database Class
    Connection conn=DatabaseConnection.connectDB();
    PreparedStatement stmt;
    
    @FXML
    private JFXTextField uSupplierName;
    @FXML
    private JFXTextField uSupplierEmail;
    @FXML
    private JFXTextField uSupplierAddress;
    @FXML
    private JFXDatePicker uSupplierDate;
    @FXML
    private TextArea uSupplierDescriptions;
    @FXML
    private JFXTextField uSupplierPhone;
    @FXML
    private JFXButton btnUpdate;
    @FXML
    private Label lblSupplierID;

    public void setID(String id){
        lblSupplierID.setText(id);
    }
    public void setName(String name){
        uSupplierName.setText(name);
    }
    public void setEmail(String email){
        uSupplierEmail.setText(email);
    }
    public void setAddress(String address){
        uSupplierAddress.setText(address);
    }
    public void setPhone(String phone){
        uSupplierPhone.setText(phone);
    }
    public void setDate(String date){
        
        uSupplierDate.setValue(LocalDate.parse(date));
    }
    public void setDescriptions(String desc){
        uSupplierDescriptions.setText(desc);
    }
    // Updating Supplier
    @FXML
    public void updateSupplier() {
        
        try{
            String query="update Suppliar set Name=?, email=?, phone=?,"
                    + "address=?,descriptions=?,RegDate=? where supplierID=?";
            stmt=conn.prepareStatement(query);
            stmt.setString(1, uSupplierName.getText());
            stmt.setString(2, uSupplierEmail.getText());
            stmt.setString(3, uSupplierPhone.getText());
            stmt.setString(4, uSupplierAddress.getText());
            stmt.setString(5, uSupplierDescriptions.getText());
            stmt.setString(6, uSupplierDate.getValue().toString());
            stmt.setString(7, lblSupplierID.getText());
            stmt.execute();
            uSupplierName.setText("");
            Stage myStage=(Stage) btnUpdate.getScene().getWindow();
            myStage.close();
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
       
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }       
}
