
package UpdatePurchase;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import DBConnection.DatabaseConnection;
import java.sql.*;
import javafx.animation.PauseTransition;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import Lists.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.StringConverter;
public class UpdatePurchsaeController implements Initializable {
    
    Connection conn=DatabaseConnection.connectDB();
    PreparedStatement stmt;
    SupplierList supList;
    MedicineList mList;
    CategoryList categoryList;
    ObservableList<SupplierList> sList=FXCollections.observableArrayList();
    ObservableList<MedicineList>medicineList=FXCollections.observableArrayList();
    ObservableList<CategoryList>catList=FXCollections.observableArrayList();
    
    int supplierID;
    int medicineID;
    int categoryID;
    private ComboBox<String> comb;
    @FXML
    private ComboBox<SupplierList> txtSupplierName;
    @FXML
    private ComboBox<MedicineList> txtMedicineName;
    @FXML
    private JFXDatePicker purchaseDate;
    @FXML
    private JFXTextField purchaseQuantity;
    @FXML
    private JFXTextField purchasePrice;
    @FXML
    private JFXTextField purchaseTotal;
    @FXML
    private JFXButton btnUpdate;
    @FXML
    private Label messageUpdate;
    @FXML
    private ComboBox<CategoryList> txtCategoryName;
    @FXML
    private JFXTextField txtCompanyName;
    @FXML
    private Label lblPurchaseID;
    @FXML
    private Label lblSupplierId;
    @FXML
    private Label lblMedicineID;
    @FXML
    private Label lblCategoryID;
    
    public void setPurchaseID(String id){
        lblPurchaseID.setText(id);
    }
    public void setSupplierName(String supName){
        lblSupplierId.setText(supName);
    }
 
    public void setMedicineName(String mName){
       lblMedicineID.setText(mName);
       
    }
    public void setCategory(String cName){
        lblCategoryID.setText(cName);
    }
    public void setDate(String date){
        purchaseDate.setValue(LocalDate.parse(date));
    }
    public void setQuantity(String qty){
        purchaseQuantity.setText(qty);
    }
    public void setPrice(String price){
        purchasePrice.setText(price);
    }
    public void setTotalPrice(String total){
        purchaseTotal.setText(total);
    }
    public void setCompanyName(String comName){
        txtCompanyName.setText(comName);
    }
    
    //Updating Purchase
    @FXML
    public void updatePurchase(){
        
        try{
            String query="Update Purchase Set SuppliarID=?, Date=?, MedicineID=?, CategoryID=?, Quantity=?,"
                    + "purchasePrice=?, totalPrice=?, companyName=? where PurchaseID=? ";
            stmt=conn.prepareStatement(query);
            stmt.setString(1, lblSupplierId.getText());
            stmt.setString(2, purchaseDate.getValue().toString());
            stmt.setString(3, lblMedicineID.getText());
            stmt.setString(4, lblCategoryID.getText());
            stmt.setString(5, purchaseQuantity.getText());
            stmt.setString(6, purchasePrice.getText());
            stmt.setString(7, purchaseTotal.getText());
            stmt.setString(8, txtCompanyName.getText());
            stmt.setString(9, lblPurchaseID.getText());
            
            if(txtSupplierName.equals("") && txtMedicineName.equals("") && txtCategoryName.equals("")){
                JOptionPane.showMessageDialog(null, "Please Select Value in Selection Boxes");
                stmt.close();
            }
            stmt.execute();
            
            messageUpdate.setVisible(true);
            PauseTransition pt=new PauseTransition(Duration.seconds(1));
            pt.setOnFinished(event->messageUpdate.setVisible(true));
            pt.play();    
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Please Select date in Selection Boxes");
        }
    }
    //Calcualting 
    @FXML
    public void calculatePrice(){
        int quantity=Integer.parseInt(purchaseQuantity.getText());
        int price=Integer.parseInt(purchasePrice.getText());
        int total=quantity*price;
        purchaseTotal.setText(total+"");
    }
    
     public void loadPMedicine(){
        try{
            String query="select * from medicine";
            stmt=conn.prepareStatement(query);
            ResultSet rSet=stmt.executeQuery();
            while(rSet.next()){
                medicineList.add(new MedicineList(rSet.getString(1),rSet.getString(2),
                rSet.getString(3),rSet.getString(4),rSet.getString(5),rSet.getString(6)));  
            }
            txtMedicineName.setItems(medicineList);
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
        txtMedicineName.setConverter(new StringConverter<MedicineList>(){
            @Override
            public String toString(MedicineList object) {
                return object.getName();
            }

            @Override
            public MedicineList fromString(String string) {
               return null;
            }
        });
        txtMedicineName.valueProperty().addListener((obs,oldValue,newValue)->{
            if(newValue!=null)
                medicineID=Integer.parseInt(newValue.getId());
            lblMedicineID.setText(String.valueOf(medicineID));
        });
        
        
    }
     
      public void loadPSupplierCombo(){
        try{
            String query="select * from Suppliar";
            stmt=conn.prepareStatement(query);
            ResultSet rSet=stmt.executeQuery();
            while(rSet.next()){
                sList.add(new SupplierList(rSet.getString(1),rSet.getString(2),rSet.getString(3),
                rSet.getString(4),rSet.getString(5),rSet.getString(6),rSet.getString(7)));
            }
            txtSupplierName.setItems(sList);
            
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
        txtSupplierName.setConverter(new StringConverter<SupplierList>(){
            @Override
            public String toString(SupplierList object) {
                return object.getName();
            }

            @Override
            public SupplierList fromString(String string) {
              return null;
            } 
    });
    txtSupplierName.valueProperty().addListener((obs,oldValue,newValue)->{
        if(newValue!=null)
            supplierID=Integer.parseInt(newValue.getId());
            lblSupplierId.setText(String.valueOf(supplierID));
    });     
      }
      
      // load Cateogory
     public void loadCategory(){
         String query="select * from Category";
         try{
             stmt=conn.prepareStatement(query);
             ResultSet rSet=stmt.executeQuery();
             while(rSet.next()){
                 catList.add(new CategoryList(rSet.getString(1),rSet.getString(2)));
             }
             txtCategoryName.setItems(catList); 
         }
         catch(Exception ex){
             JOptionPane.showMessageDialog(null, ex);
         }
         txtCategoryName.setConverter(new StringConverter<CategoryList>(){
             @Override
             public String toString(CategoryList object) {
                return object.getName();
             }

             @Override
             public CategoryList fromString(String string) {
                 return null;
             }
             
         });
         txtCategoryName.valueProperty().addListener((obs,oldValue,newValue)->{
             if(newValue!=null){
                 categoryID=Integer.parseInt(newValue.getID());
                 lblCategoryID.setText(String.valueOf(categoryID));
             }
         });
     }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadPMedicine();
        loadPSupplierCombo();
        loadCategory();
    }      
}
