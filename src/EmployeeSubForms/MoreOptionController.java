package EmployeeSubForms;

import DBConnection.DatabaseConnection;
import Lists.EmployeeList;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.sql.*;
import DBConnection.*;
import EmployeeSubForms.UpdateForm.UpdateFrmController;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
public class MoreOptionController implements Initializable {
    

// Database Connection
Connection conn=DatabaseConnection.connectDB();
DatabaseQueries dbQ=new DatabaseQueries();
PreparedStatement stmt;
EmployeeList employeeList;
int empID;
// Lists
    ObservableList<EmployeeList> empList=FXCollections.observableArrayList();
    EmployeeList list;
    
    @FXML
    private TableColumn<?, ?> colEmpID;
    @FXML
    private TableColumn<?, ?> colEmpFName;
    @FXML
    private TableColumn<?, ?> colEmpLName;
    @FXML
    private TableColumn<?, ?> colEmpPosition;
    @FXML
    private TableColumn<?, ?> colEmpBloodGroup;
    @FXML
    private TableColumn<?, ?> colEmpContactNO;
    @FXML
    private TableColumn<?, ?> colEmpAltNO;
    @FXML
    private TableColumn<?, ?> colEmpEmail;
    @FXML
    private TableColumn<?, ?> colEmpSalary;
    @FXML
    private TableColumn<?, ?> colEmpCDate;
    @FXML
    private TableColumn<?, ?> colEmpCEDate;
    @FXML
    private TableColumn<?, ?> colEmpCAdd;
    @FXML
    private TableColumn<?, ?> colEmpImage;
    @FXML
    private TableView<EmployeeList> empViewTable;
    @FXML
    private TableColumn<?, ?> colEmpDescriptions;
    @FXML
    private TableColumn<?, ?> colEmpPAdd;
    @FXML
    private JFXTextField txtEmpSearch;
    @FXML
    private MenuItem btnDelete;
    // Loading data from employe table into empTableView
    public void loadEmployeeData(){
        try{
            String query="select * from employee";
            ResultSet rSet=dbQ.selectData(query);
            while(rSet.next()){
                empList.add(new EmployeeList(rSet.getString(1),rSet.getString(2),
                rSet.getString(3),rSet.getString(4),rSet.getString(5),rSet.getString(6),
                rSet.getString(7),rSet.getString(8),rSet.getString(9),rSet.getString(10),
                rSet.getString(11),rSet.getString(12),rSet.getString(13),rSet.getString(14),
                rSet.getBlob(15)));  
            }
            empViewTable.setItems(empList);    
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }     
    }
    public void setEmployeeColumns(){
        colEmpID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colEmpFName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmpLName.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        colEmpPosition.setCellValueFactory(new PropertyValueFactory<>("position"));
        colEmpBloodGroup.setCellValueFactory(new PropertyValueFactory<>("bloodGroup"));
        colEmpContactNO.setCellValueFactory(new PropertyValueFactory<>("contactNO"));
        colEmpAltNO.setCellValueFactory(new PropertyValueFactory<>("altNO"));
        colEmpEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colEmpSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colEmpCDate.setCellValueFactory(new PropertyValueFactory<>("contractdate"));
        colEmpCEDate.setCellValueFactory(new PropertyValueFactory<>("enddate"));
        colEmpDescriptions.setCellValueFactory(new PropertyValueFactory<>("descriptions"));
        colEmpCAdd.setCellValueFactory(new PropertyValueFactory<>("currentadd"));
        colEmpPAdd.setCellValueFactory(new PropertyValueFactory<>("permanentadd"));
        colEmpImage.setCellValueFactory(new PropertyValueFactory<>("image"));
        
        // Searching Record in table
        
        FilteredList<EmployeeList> filterData=new FilteredList<>(empList,b->true);
        txtEmpSearch.textProperty().addListener((observable,oldValue,newValue)->{
        filterData.setPredicate(employeeList->{
            if(newValue==null || newValue.isEmpty())
            return true;
        String lowerCase=newValue.toLowerCase();
        if(employeeList.getName().toLowerCase().indexOf(lowerCase)!=-1)
            return true;
        else if(employeeList.getContactNO().indexOf(lowerCase)!=-1)
            return true;
        else if (employeeList.getPosition().toLowerCase().indexOf(lowerCase)!=-1)
            return true;
        else if(employeeList.getEmail().toLowerCase().indexOf(lowerCase)!=-1)
            return true;
        else if(employeeList.getBloodGroup().indexOf(lowerCase)!=-1)
            return true;
        
        return false;
        
        });
 });
        SortedList<EmployeeList> sortedData=new SortedList<>(filterData);
        sortedData.comparatorProperty().bind(empViewTable.comparatorProperty());
        empViewTable.setItems(sortedData);
        
    }
    
@FXML
    public void deleteRecord(){
        try{
            String query="Delete from Employee where EmpID=?";
            stmt=conn.prepareStatement(query);
            stmt.setInt(1, empID);
            stmt.execute();
            empList.clear();
            loadEmployeeData();   
        }
        catch(Exception ex){
            
        }
    }
    
    public void tableClicked(){
      empViewTable.setOnMouseClicked(e->{
          list=empViewTable.getItems().get(empViewTable.getSelectionModel().getSelectedIndex());
          empID=Integer.parseInt(list.getId());
          
      });
    }
    
@FXML
    public void openUpdateForm()throws Exception{
        FXMLLoader loader=new FXMLLoader();
           loader.setLocation(getClass().getResource("UpdateForm/UpdateFrm.fxml"));
        try{
           
           loader.load();
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
        list=empViewTable.getItems().get(empViewTable.getSelectionModel().getSelectedIndex());
        UpdateFrmController obj=loader.getController();
         Parent p=loader.getRoot();
           Stage myStage=new Stage();
           myStage.setScene(new Scene(p));
           myStage.setResizable(false);
           myStage.show(); 
           
        obj.setName(list.getName());
        obj.setLName(list.getLastname());
        obj.setPosition(list.getPosition());
        obj.setBloodGroup(list.getBloodGroup());
        obj.setSalary(list.getSalary());
        obj.setCData(list.getContractdate());
        obj.setContactNO(list.getContactNO());
        obj.setAltNO(list.getAltNO());
        obj.setEmail(list.getEmail());
        obj.setCAdd(list.getCurrentadd());
        obj.setPAdd(list.getPermanentadd());
        obj.setDescriptions(list.getDescriptions());
        obj.setImage(list.getImage());
        obj.setID(list.getId());  
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadEmployeeData();
        setEmployeeColumns();
        tableClicked();
    }     
}
