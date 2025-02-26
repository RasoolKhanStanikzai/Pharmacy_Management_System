
package Login;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.sql.*;
import DBConnection.DatabaseConnection;
import Lists.UserList;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javax.swing.JOptionPane;
import pmsystem.MainFormController;
import static pmsystem.MainFormController.purPanel;
public class LoginFormFXMLController extends MainFormController implements Initializable {
    //Lists
    ObservableList<UserList> userList=FXCollections.observableArrayList();
    
    //Database Connection
    Connection conn=DatabaseConnection.connectDB();
    PreparedStatement stmt;
    
    //External Classes
    UserList uList;
    DateFormat dform = new SimpleDateFormat("dd/MM/yy");
    Calendar calender = Calendar.getInstance();
    
    FXMLLoader loader=new FXMLLoader();
    
    
    // Variables
    int userID;
    String jobStatus;
    String userName;
   
    // Controller Components
    @FXML
    private ComboBox<UserList> comboUserRole;
    @FXML
    private JFXTextField txtUserName;
    @FXML
    private JFXPasswordField txtUserPassword;
    @FXML
    private JFXButton btnLogin;
    @FXML
    private Label lblDate;
    AnchorPane paneUser;
    
   
    // Loading Job Status From User
    public void loadUserJobStatus(){
        try{
            String query="select *from user Group by JobStatus";
            stmt=conn.prepareStatement(query);
            ResultSet rSet=stmt.executeQuery();
            while(rSet.next()){
                userList.add(new UserList(rSet.getString(1),rSet.getString(2),rSet.getString(3),rSet.getString(4),
                rSet.getBlob(5),rSet.getString(6),rSet.getString(7),rSet.getString(8)));
            }
            comboUserRole.setItems(userList);
           
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
        comboUserRole.setConverter(new StringConverter<UserList>(){
            @Override
            public String toString(UserList object) {
                jobStatus=object.getJobStatus();
                
                return object.getJobStatus();
            }

            @Override
            public UserList fromString(String string) {
                return null;
            }    
            
        });
        comboUserRole.valueProperty().addListener((obs,oldValue,newValue)->{
            userID=Integer.parseInt(newValue.getID());
            
        });
         
    }
    // Loading Data from USRE 
    @FXML
    public void loadUser(ActionEvent event) throws Exception{
         String uName=txtUserName.getText();
         String userPassword=txtUserPassword.getText();
        try{
            String query="select * from user where UserName=? AND UserPassword=? AND JobStatus=?";
            stmt=conn.prepareStatement(query);
            stmt.setString(1, uName);
            stmt.setString(2, userPassword);
            stmt.setString(3, jobStatus);
            ResultSet rSet=stmt.executeQuery();
            if(rSet.next()){
                loader.setLocation(getClass().getResource("/pmsystem/MainForm.fxml"));
                loader.load();
                Parent root=loader.getRoot();
                Stage myStage=new Stage();
                myStage.setScene(new Scene(root));
                if(jobStatus.equals("Admin")){
                myStage.show();
                }
                else{
                myStage.show();
                userPanel.setDisable(true);
                employeePanel.setDisable(true);
                medicinePanel.setDisable(true);
                shelfPanel.setDisable(true);
                companyPanel.setDisable(true);
                purPanel.setDisable(true);
                sPanel.setDisable(true);
                catPanel.setDisable(true);
                }
                myStage=(Stage) btnLogin.getScene().getWindow();
                myStage.close();
                lblLoggedU.setText(uName);
            }
            else
            {
                 Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Pharmacy Management");
                alert.setContentText("Incorrect User Name or Password");
                 Optional<ButtonType>btnAction=alert.showAndWait();
            }
  }  
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
    }
   // Setters
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      loadUserJobStatus();
      lblDate.setText(dform.format(calender.getTime()));
    }    
}

