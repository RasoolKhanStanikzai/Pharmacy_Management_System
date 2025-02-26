
package EmployeeSubForms.UpdateForm;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import DBConnection.BrowseImage;
import DBConnection.DatabaseConnection;
import EmployeeSubForms.MoreOptionController;
import Lists.EmployeeList;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import java.sql.Blob;
import javafx.embed.swing.SwingFXUtils;
import javax.imageio.ImageIO;
import java.sql.*;
import java.time.LocalDate;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javax.swing.JOptionPane;
public class UpdateFrmController implements Initializable {
    //Database Connection Class
    Connection conn=DatabaseConnection.connectDB();
    PreparedStatement stmt;
    // Using External Controller in the current Controller
    FXMLLoader loader=new FXMLLoader();
    MoreOptionController moreObj=loader.getController();
    
    //External Classes
    
    EmployeeList empList;
    BrowseImage img=new BrowseImage();
    InputStream inputStream;
    InputStream st;
   //lists;
    ObservableList<String> employeePositions=FXCollections.observableArrayList("Clerk","Dispenser","Assistant","Technician","Pharmacist","Chemotherapy Pharmacist","Staff Pharmacist","Pharmacy Manager");
    ObservableList<String> bloodGroup=FXCollections.observableArrayList("A+","A-","B+","B-","O+","O-","AB+","AB-");
    
    @FXML
    private JFXTextField empFirstName;
    @FXML
    private JFXTextField empLastName;
    @FXML
    private JFXComboBox<String> empPosition;
    @FXML
    private JFXComboBox<String> empBloodGroup;
    @FXML
    private JFXTextField empSalary;
    @FXML
    private DatePicker empContractDate;
    @FXML
    private DatePicker empContractEndDate;
    @FXML
    private JFXTextField empContactNO;
    @FXML
    private JFXTextField empAlternativeNO;
    @FXML
    private JFXTextField empEmail;
    @FXML
    private JFXTextField empCurrentAdd;
    @FXML
    private JFXTextField empPermanentAdd;
    @FXML
    private JFXTextArea empDescriptions;
    @FXML
    private JFXButton btnUpdate;
    @FXML
    private ImageView imgEmployeeImageViewer;
    @FXML
    private JFXButton btnBrowseEmployeeImage;
    
    @FXML
    private Label lblID;
    @FXML
    private Label lblMessage;
    //Setters
    public void setID(String id){
        lblID.setText(id);
    }
    public void setName(String name)
    {
        empFirstName.setText(name);
    }
    public void setLName(String lastName){
        empLastName.setText(lastName);
    }
    public void setPosition(String position){
        empPosition.getSelectionModel().select(position);   
    }
    public void setBloodGroup(String bGroup){
        empBloodGroup.getSelectionModel().select(bGroup);
    }
    public void setSalary(String salary){
        empSalary.setText(salary);
    }
    public void setCData(String date){
        empContractDate.setValue(LocalDate.parse(date));
    }
    public void setCEDae(String date){
        empContractEndDate.setValue(LocalDate.parse(date));
    }
    public void setContactNO(String no){
        empContactNO.setText(no);
    }
    public void setAltNO(String no){
        empAlternativeNO.setText(no);
    }
    public void setEmail(String email){
        empEmail.setText(email);
    }
    public void setCAdd(String add){
        empCurrentAdd.setText(add);
    }
    public void setPAdd(String add){
        empPermanentAdd.setText(add);
    }
    public void setDescriptions(String desc){
        empDescriptions.setText(desc);
    }
     
    public void setImage(Blob path) throws Exception{
        
        Blob imgBlob=path;// Setting the parameter to the Blob
        //InputStream is an obstacle class ob byte stream that describe 
        // stream input nd it is used for reading and it coulbe a file,image
        // autdio,video,webpage etc
        //Input stream read data from source one item at a time
         st=imgBlob.getBinaryStream(); // reading Blob Object
        BufferedImage bgImg=ImageIO.read(st);//A BufferedImage is essentially an image with an 
       // accessable data buffer, A buffered image has a colormodel anda raster of image data
       Image imgView=SwingFXUtils.toFXImage(bgImg, null);
       imgEmployeeImageViewer.setImage(imgView);
       // Converting the Image back into byte type
       ByteArrayOutputStream os=new ByteArrayOutputStream();
       ImageIO.write(bgImg, "jpg", os);
       st=new ByteArrayInputStream(os.toByteArray());
    }
    
    @FXML
    public void updateEmployee()throws Exception{ 
        inputStream=img.insertImage(imgEmployeeImageViewer);
        
            String query="update Employee set empName=?,EmpLastName=?,"
                    + "position=?,bloodGroup=?,ContactNO=?,AlternativeNO=?,"
                    + "Email=?,Salary=?,ContractDate=?,ContractExpiration=?,"
                    + "Descriptions=?,CurrentAdd=?,PermanentAdd=?,Image=? where EmpID=?";
            
            stmt=conn.prepareStatement(query);
           
            stmt.setString(1, empFirstName.getText());
            stmt.setString(2, empLastName.getText());
            stmt.setString(3, empPosition.getValue());
            stmt.setString(4, empBloodGroup.getValue());
            stmt.setString(5, empContactNO.getText());
            stmt.setString(6, empAlternativeNO.getText());
            stmt.setString(7, empEmail.getText());
            stmt.setString(8, empSalary.getText());
            stmt.setString(9, empContractDate.getValue().toString());
            stmt.setString(10, empContractEndDate.getValue().toString());
            stmt.setString(11, empDescriptions.getText());
            stmt.setString(12, empCurrentAdd.getText());
            stmt.setString(13, empPermanentAdd.getText());
            // Inserting or Updating Old Imag in the recrod
            if(inputStream!=null)
                stmt.setBinaryStream(14, inputStream);
            else
                stmt.setBinaryStream(14, st);
          
            stmt.setString(15, lblID.getText());
            stmt.executeUpdate();
         //   empFirstName.setText(""); 
} 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       empPosition.setItems(employeePositions);
       empBloodGroup.setItems(bloodGroup);
    }    

    @FXML
    private void browseEmpImage(ActionEvent event) throws FileNotFoundException {
        img.browseImage(imgEmployeeImageViewer);
      
    }
}
