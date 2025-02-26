
package pmsystem;
import DBConnection.BrowseImage;
import DBConnection.DatabaseConnection;
import Lists.CategoryList;
import Lists.MedicineList;
import Lists.PurchaseList;
import Lists.ShelfList;
import Lists.SupplierList;
import Lists.UserList;
import Supplier.UpdateSupplierController;
import UpdatePurchase.UpdatePurchsaeController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import java.sql.*;
import java.util.Optional;
import javafx.animation.PauseTransition;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.StringConverter;
import javax.swing.JOptionPane;
import Lists.*;
import java.io.IOException;
import java.time.LocalDate;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class MainFormController implements Initializable {

    // Classes
    Connection conn=DatabaseConnection.connectDB();
    PreparedStatement stmt;
    BrowseImage image=new BrowseImage();
    InputStream inputStream;
    UserList uList;
    CategoryList catList;
    ShelfList shelfList;
    MedicineList medicineList;
    SupplierList supList;
    PurchaseList purList;
    CompanyList comList;
    EmployeeList empList;
    CustomerList cusList;
    AddingToCart cart;
    SaleList objSaleList;
    TopSale tSale;
    
    int categoryID;
    int shelfID;
    int medicineID;
    int supplierID;
    int purchaseID;
    int companyID;
    int employeeID;
    int customerID;
    int saleID;
    // String value form EmployeeName
    
    String empName;
    String cusName;
    String catName;
    String medName;
    
// Array Lists
    //Storeing Date into comboBox to store the jobs title
    ObservableList<String> list=FXCollections.observableArrayList("Admin","Child");
    // Storeing date for Table view to show the user data
    ObservableList<UserList> userList=FXCollections.observableArrayList();
    // Storeing data for Employee Positions
    ObservableList<String> employeePositions=FXCollections.observableArrayList("Clerk","Dispenser","Assistant","Technician","Pharmacist","Chemotherapy Pharmacist","Staff Pharmacist","Pharmacy Manager");
    // Storeing data for Blood Groups
    ObservableList<String> bloodGroup=FXCollections.observableArrayList("A+","A-","B+","B-","O+","O-","AB+","AB-");
    // Loading Category Name
    ObservableList<CategoryList>cList=FXCollections.observableArrayList();
    //Loading Shelf
    ObservableList<ShelfList> sList=FXCollections.observableArrayList();
    //loading Medicine 
    ObservableList<MedicineList> mList2=FXCollections.observableArrayList();
    // Loading particular data from Medicine into Stock
    ObservableList<StockList>stockList=FXCollections.observableArrayList();
    
    ObservableList<MedicineList> mList=FXCollections.observableArrayList();
    //Loading Supplier
    ObservableList<SupplierList> supplierList=FXCollections.observableArrayList();
    // Loading Purchase
    ObservableList<PurchaseList> purchaseList=FXCollections.observableArrayList();
    //Loading PurchasePrice
    ObservableList<PurchaseList>purchasePriceList=FXCollections.observableArrayList();
    //  Loading Company Name in Purchase Form to Combo Box
    ObservableList<CompanyList>companyList=FXCollections.observableArrayList();
    // Loading Company Data to TblViewCompany
    ObservableList<CompanyList>companyListTblView=FXCollections.observableArrayList();
    // Loading Employee
    ObservableList<EmployeeList>employeeList=FXCollections.observableArrayList();
    // Loading Customer
    ObservableList<CustomerList>customerList=FXCollections.observableArrayList();
    // Loading Customer Data to Tabl
    ObservableList<CustomerList>customerList2=FXCollections.observableArrayList();
    // Loading date to Cart
    ObservableList<AddingToCart> cartList=FXCollections.observableArrayList();
    //Storeing Cart data both integer and string
    ObservableList<AddingToCart> cartList2=FXCollections.observableArrayList();
    // Loading Sale Data
    ObservableList<SaleList> saleList=FXCollections.observableArrayList();
    // Loading TOP Sale
    ObservableList<TopSale> topSale=FXCollections.observableArrayList();
    // adding from cart table to store in sale table
    // Displaying Chart for dashboard
    
    
    java.util.Map<String,Integer>medicinePriceMap=new java.util.HashMap<>();
    java.util.Map<String,String>medicineCategoryMap=new java.util.HashMap<>();
    java.util.Map<String,Integer>categoryIDMap=new java.util.HashMap<>();
    java.util.Map<String,Integer>medicineIDMap=new java.util.HashMap<>();
    
   String na; 
   private Label label;
    @FXML
    private VBox dashbaordBox;
    @FXML
    private Label lblDashboard;
    @FXML
    private AnchorPane userPane;
    @FXML
    private JFXTextField txtName;
    @FXML
    private JFXPasswordField txtPassword;
    @FXML
    private JFXPasswordField txtCPassword;
    @FXML
    private JFXComboBox<String> comboJob;
    @FXML
    private JFXTextField txtEmail;
    @FXML
    private JFXTextField txtContactNO;
    @FXML
    private JFXButton btnSave;
    @FXML
    private Label lblRecordAffected;
    @FXML
    private ImageView imageView;
    @FXML
    private TableView<UserList> userTableView;
    @FXML
    private TableColumn<?, ?> columnUserID;
    @FXML
    private TableColumn<?, ?> columnUserName;
    @FXML
    private TableColumn<?, ?> columnUserPassword;
    @FXML
    private TableColumn<?, ?> columnUserJobStatus;
    @FXML
    private TableColumn<?, ?> columnUserPhoto;
    @FXML
    private TableColumn<?, ?> columnUserEmail;
    @FXML
    private TableColumn<?, ?> columnUserPhone;
    @FXML
    private TableColumn<?, ?> columnUserDate;
    @FXML
    private Label lblContactNOMessage;
    @FXML
    private Label lblAdmin;
    @FXML
    private Label lblStock;
    @FXML
    private AnchorPane EmployeePane;
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
    private JFXButton btnSaveEmp;
    @FXML
    private JFXTextArea empDescriptions;
    @FXML
    private Label lblMessage;
    @FXML
    private Label lblTotalEmployees;
    @FXML
    private ImageView imgEmployeeImageViewer;
    @FXML
    private JFXButton btnBrowseEmployeeImage;
    @FXML
    private Label lblMoreOptions;
    @FXML
    private AnchorPane stockPane;
    private JFXTextField txtStock;
    private JFXTextField txtQuantity;
    private ComboBox<CategoryList> comboCat;
    private DatePicker regDate;
    private DatePicker expDate;
    private JFXTextField txtPurchasePrice;
    @FXML
    private JFXTextField txtSalePrice;
    @FXML
    private Label lblStockMsg;
    private JFXTextArea txtStockDescriptions;
    @FXML
    private AnchorPane categoryPane;
    @FXML
    private JFXTextField txtCatName;
    @FXML
    private TableView<CategoryList> tblViewCategory;
    @FXML
    private TableColumn<?, ?> columnCatID;
    @FXML
    private TableColumn<?, ?> columnCatName;
    private TableColumn<?, ?> columnCatAction;
    @FXML
    private Label msgEmpty;
    @FXML
    private Label lblStarMarkCat;
    @FXML
    private JFXTextField txtSearchCategory;
    @FXML
    private JFXButton btnCategorySave;
    @FXML
    private JFXButton btnCategoryUpdate;
    @FXML
    private JFXButton btnCategoryCancel;
    @FXML
    private AnchorPane shelfPane;
    @FXML
    private JFXTextField txtShelfName;
    @FXML
    private TextArea txtShelfDescriptions;
    @FXML
    private JFXTextField txtShelfNO;
    @FXML
    private JFXDatePicker txtShelfDate;
    @FXML
    private JFXButton btnShelfSave;
    @FXML
    private TableView<ShelfList> tblViewShelf;
    @FXML
    private TableColumn<?, ?> shelfCID;
    @FXML
    private TableColumn<?, ?> shelfCName;
    @FXML
    private TableColumn<?, ?> shelfCNumber;
    @FXML
    private TableColumn<?, ?> shelfCDate;
    @FXML
    private TableColumn<?, ?> shelfCDescriptions;
    @FXML
    private Label lblShelfID;
    @FXML
    private Label lblShelfName;
    @FXML
    private Label lblShelfNumber;
    @FXML
    private Label lblShelfDate;
    @FXML
    private TextArea lblShelfDescriptions;
    @FXML
    private Pane viewPaneShelf;
    @FXML
    private Label lblShelfSaved;
    @FXML
    private MenuItem contextBUpdateShelf;
    @FXML
    private JFXButton btnUpdateShelf;
    @FXML
    private JFXButton btnCancelShelf;
    @FXML
    private JFXButton btnShelfViewClose;
    @FXML
    private JFXTextField txtShelfSearch;
    @FXML
    private Label txtCountShelfes;
    @FXML
    private AnchorPane medicinePane;
    @FXML
    private Label txtCountShelfes1;
    @FXML
    private ComboBox<CategoryList> comboMCatName;
    @FXML
    private ComboBox<ShelfList> comboMShelfName;
    @FXML
    private JFXTextField txtMedicneName;
    @FXML
    private JFXTextField txtMedicineBatchNO;
    @FXML
    private JFXDatePicker medicineRegDate;
    @FXML
    private JFXButton btnMedicineSave;
    @FXML
    private Label txtMedicineMessageSave;
    @FXML
    private TableView<MedicineList> tblViewMedicine;
    @FXML
    private TableColumn<?, ?> colMID;
    @FXML
    private TableColumn<?, ?> colMCat;
    @FXML
    private TableColumn<?, ?> colMShelf;
    @FXML
    private TableColumn<?, ?> colMName;
    @FXML
    private TableColumn<?, ?> colMBatch;
    @FXML
    private TableColumn<?, ?> colMDate;
    @FXML
    private JFXTextField txtSearchMedicine;
    @FXML
    private Label lblMID;
    @FXML
    private Label lblMName;
    @FXML
    private Label lblMShelfName;
    @FXML
    private Label lblMCatName;
    @FXML
    private Label lblMBatchNO;
    @FXML
    private Label lblMDate;
    @FXML
    private Pane viewMadecinePane;
    @FXML
    private JFXButton btnCloseViewMedicine;
    @FXML
    private Label lblTotalMedicine;
    @FXML
    private AnchorPane paneSupplier;
    @FXML
    private Label txtCountShelfes11;
    @FXML
    private Label txtCountShelfes111;
    @FXML
    private JFXTextField txtSupplierName;
    @FXML
    private JFXTextField txtSupplierPhone;
    @FXML
    private JFXTextField txtSupplierEmail;
    @FXML
    private JFXTextField txtSupplierAddress;
    @FXML
    private TextArea txtSupplierDescriptions;
    @FXML
    private Label lblSupplierMessage;
    @FXML
    private JFXDatePicker txtDateSupplier;
    @FXML
    private TableView<SupplierList> tblViewSupplier;
    @FXML
    private TableColumn<?, ?> colSupplierID;
    @FXML
    private TableColumn<?, ?> colSupplierName;
    @FXML
    private TableColumn<?, ?> colSupplierPhone;
    @FXML
    private TableColumn<?, ?> colSupplierEmail;
    @FXML
    private TableColumn<?, ?> colSupplierAddress;
    @FXML
    private TableColumn<?, ?> colSupplierRegDate;
    @FXML
    private TableColumn<?, ?> colSupplierDescriptions;
    @FXML
    private Label lblTotalSupplier;
    @FXML
    private Label lblSupplierID;
    @FXML
    private Label lblSupplierName;
    @FXML
    private Label lblSupplierPhone;
    @FXML
    private Label lblSupplierEmail;
    @FXML
    private Label lblSupplierDate;
    @FXML
    private TextArea lblSupplierDescriptions;
    @FXML
    private Pane supplierViewPane;
    @FXML
    private Label lblSupplierAddress;
    @FXML
    private JFXButton btnSupplierRefresh;
    @FXML
    private AnchorPane panePurchase;
    @FXML
    private Label txtCountShelfes112;
    @FXML
    private ComboBox<SupplierList> comboPurchaseID;
    @FXML
    private ComboBox<MedicineList> comboPMedicineID;
    @FXML
    private JFXDatePicker purchaseDate;
    @FXML
    private JFXTextField purchaseQuantity;
    @FXML
    private Label purchaseItemMessage;
    @FXML
    private TableView<PurchaseList> tblViewPurchase;
    @FXML
    private TableColumn<?, ?> columnPurchaseID;
    @FXML
    private TableColumn<?, ?> columnPSupplierName;
    @FXML
    private TableColumn<?, ?> columnPMedicineName;
    @FXML
    private TableColumn<?, ?> columnPurchaseDate;
    @FXML
    private TableColumn<?, ?> columnPurcahseQuantity;
    @FXML
    private JFXTextField purchseItemPrice;
    @FXML
    private JFXTextField purchaseTotalPrice;
    @FXML
    private TableColumn<?, ?> columnPurchasePrice;
    @FXML
    private TableColumn<?, ?> columnPurchaseTotalPrice;
    @FXML
    private Label lblPurchaseID;
    @FXML
    private Label lblPSupplierName;
    @FXML
    private Label lblPMedicineName;
    @FXML
    private Label lblPurchaseDate;
    @FXML
    private Label lblPurchaseQuantity;
    @FXML
    private Label lblPurchasePrice;
    @FXML
    private Label lblPurchaseTotalPrice;
    @FXML
    private Pane purchaseViewPane;
    @FXML
    private JFXTextField txtSearchPurchase;
    @FXML
    private Label txtTotalPurchases;
    private ComboBox<MedicineList> comboStockProduct;
    @FXML
    private TableView<StockList> tblViewStock;
    @FXML
    private TableColumn<?, ?> colStockPID;
    @FXML
    private TableColumn<?, ?> colProducName;
    @FXML
    private TableColumn<?, ?> colStockQuantity;
    private TableColumn<?, ?> stockCategory;
    @FXML
    private ComboBox<CategoryList> comboPCategory;
    private ComboBox<?> txtCompanyName;
    @FXML
    private TableColumn<?, ?> colPurchaseCategory;
    @FXML
    private TableColumn<?, ?> colPurchaseCompName;
    @FXML
    private Label lblCategoryPurchase;
    @FXML
    private Label lblCompanyPurchase;
    @FXML
    private Label txtCountShelfes1121;
    @FXML
    private Label txtTotalPurchases1;
    @FXML
    private AnchorPane companyPane;
    @FXML
    private JFXTextField txtComName;
    @FXML
    private TableView<CompanyList> tblViewCompany;
    @FXML
    private TableColumn<?, ?> colCompID;
    @FXML
    private TableColumn<?, ?> colCompName;
    @FXML
    private ComboBox<CompanyList> comboCompanyName;
    @FXML
    private JFXTextField txtStockProduct;
    @FXML
    private TableColumn<?, ?> colStockQuantityInHand;
    @FXML
    private AnchorPane salePane;
    @FXML
    private Label txtCountShelfes11211;
   @FXML
    private Label txtTotalPurchases11;
    @FXML
    private ComboBox<EmployeeList> comboEmployeeSale;
    @FXML
    private ComboBox<CustomerList> comboCustomerSale;
    @FXML
    private ComboBox<String> comboMedicineSale;
    @FXML
    private ComboBox<String> comboCategorySale;
    @FXML
    private VBox txtTotalSalePrice;
    @FXML
    private JFXDatePicker saleDate;
    @FXML
    private JFXTextField txtQuantitySale;
    @FXML
    private TableView<AddingToCart> tblViewSale;
    @FXML
    private JFXTextField saleTotalPrice;
    @FXML
    private TableColumn<AddingToCart, String> columnSaleEmpName;
    @FXML
    private TableColumn<AddingToCart, String> columnSaleCusName;
    @FXML
    private TableColumn<AddingToCart, String> columnSaleMedicineName;
    @FXML
    private TableColumn<AddingToCart, String> columnSaleCatName;
    @FXML
    private TableColumn<AddingToCart, String> columnSaleDate;
    @FXML
    private TableColumn<AddingToCart, String> columnSaleQuantity;
    @FXML
    private TableColumn<AddingToCart, String> columnSalePrice;
    @FXML
    private TableColumn<AddingToCart, String> columnSaleTotal;
    @FXML
    private Label lblSaleDate;
    @FXML
    private TableView<SaleList> tblViewSoldItems;
    @FXML
    private TableColumn<?, ?> columnSaleID;
    @FXML
    private TableColumn<?, ?> colSaleEmpName;
    @FXML
    private TableColumn<?, ?> colSaleCusName;
    @FXML
    private TableColumn<?, ?> colSaleMedicine;
    @FXML
    private TableColumn<?, ?> colSaleCategory;
    @FXML
    private TableColumn<?, ?> colSaleDate;
    @FXML
    private TableColumn<?, ?> colSaleQuantity;
    @FXML
    private TableColumn<?, ?> colSalePrice;
    @FXML
    private TableColumn<?, ?> colSaleTotalPrice;
    @FXML
    private TableColumn<?, ?> colStockSaleQuantity;
    @FXML
    private Label txtTotalStockInHand;
    @FXML
    private Label txtTotalSales;
    @FXML
    private TableView<TopSale> tableViewTopSale;
    @FXML
    private TableColumn<?, ?> columnTopMedicineID;
    @FXML
    private TableColumn<?, ?> columnTopMedicineName;
    @FXML
    private TableColumn<?, ?> columnTopSale;
    @FXML
    private AnchorPane customerPane;
    @FXML
    private Label txtCountShelfes112111;
    @FXML
    private JFXTextField txtCustomerName;
    @FXML
    private JFXTextField txtCustomerPhone;
    @FXML
    private JFXTextField txtCustomerEmail;
    @FXML
    private Label lblCountTotalCustomers;
    @FXML
    private Label lblMessageCustomerInserted;
    @FXML
    private TableView<CustomerList> tblViewCustomer;
    @FXML
    private TableColumn<?, ?> colCustomerID;
    @FXML
    private TableColumn<?, ?> colCustomerName;
    @FXML
    private TableColumn<?, ?> colCustomerEmail;
    @FXML
    private TableColumn<?, ?> colCustomerPhone;
    @FXML
    private Label lblTotalCustomerCount;
    @FXML
    private JFXTextField txtCustomerSearch;

       @FXML
    private Label lblLoggedUser;
    @FXML
    private HBox paneUser;
    @FXML
    private HBox paneEmployee;
    @FXML
    private HBox paneShelf;
    @FXML
    private HBox paneMedicine;
    @FXML
    private HBox paneCompany;
    
    // Creating Static variables to be accessed in another controller
    // for child user access
   public static HBox userPanel;
   public static HBox employeePanel;
   public static HBox shelfPanel;
   public static HBox medicinePanel;
   public static HBox companyPanel;
   public static HBox sPanel;
   public static HBox purPanel;
   public static HBox catPanel;
   public static Label lblLoggedU;
   
    @FXML
    private Label lblLoggOff;
    @FXML
    private HBox supplierPanel;
    @FXML
    private HBox purchasePanel;
    @FXML
    private HBox categoryPanel;
    @FXML
    private Label msgEmptyShelf;
    @FXML
    private Label lblStarShelf;
    @FXML
    private Label lblStarShelf1;
    @FXML
    private Label lblShelfNMark;
    @FXML
    private Label lblMessageUser;
    @FXML
    private Label lblStarUser;
    @FXML
    private JFXDatePicker txtDate;
    @FXML
    private Label lblMessageEmployee;
    @FXML
    private Label lblStarEmployee;
    @FXML
    private Label lblMessageMedicine;
    @FXML
    private Label lblStarMedicine;
    @FXML
    private Label lblMessageSale;
    @FXML
    private Label lblStarSale;
    @FXML
    private JFXButton btnSaleItems;
    @FXML
    private Label lblMessageCompany;
    @FXML
    private Label lblStarCompany;
    @FXML
    private JFXTextField txtSearchCompany;
    @FXML
    private JFXButton btnSaveCompany;
    @FXML
    private JFXButton btnUpdateCompany;
    @FXML
    private JFXButton btnCancelCompanyView;
    @FXML
    private Label lblMessageSupplier;
    @FXML
    private Label lblStarSupplier;
    @FXML
    private HBox paneCompany1;
    @FXML
    private Label txtCountShelfes1121111;
    @FXML
    private Label lblCountTotalCustomers1;
    @FXML
    private JFXTextField txtSearchTblViewSale;
    @FXML
    private TableView<SaleList> tblViewSaleReport;
    @FXML
    private TableColumn<?, ?> saleIDColumn;
    @FXML
    private TableColumn<?, ?> saleCustomerCol;
    @FXML
    private TableColumn<?, ?> saleEmployeeCol;
    @FXML
    private TableColumn<?, ?> saleDateCol;
    @FXML
    private TableColumn<?, ?> saleCategoryCol;
    @FXML
    private TableColumn<?, ?> saleMedicineCol;
    @FXML
    private TableColumn<?, ?> saleQuantityCol;
    @FXML
    private TableColumn<?, ?> salePriceCol;
    @FXML
    private TableColumn<?, ?> saleTotalPriceCol;
    @FXML
    private AnchorPane reportPane;
    @FXML
    private Label lblTotalEmployeesCount;
    @FXML
    private Label lblTotalUsersCount;
    @FXML
    private Label lblViewAllSale;
    @FXML
    private ComboBox<String> comboPurchasePriceSale;
    @FXML
    private LineChart<String,Number> salesLineChart;
    @FXML
    private Label lbl_TotalSalePrice;
    @FXML
    private Label lbl_TotalSalePrice1;
    @FXML
    private MenuItem clearCartRecord;
    @FXML
    public void showDashboard()
    {
        userPane.setVisible(false);
        dashbaordBox.setVisible(true);
        EmployeePane.setVisible(false);
        stockPane.setVisible(false);
        categoryPane.setVisible(false);
        shelfPane.setVisible(false);
        paneSupplier.setVisible(false);
        panePurchase.setVisible(false);
        companyPane.setVisible(false);
        salePane.setVisible(false);
         customerPane.setVisible(false);
         reportPane.setVisible(false);
    }
    @FXML
    public void showUser()
    {
        dashbaordBox.setVisible(false);
        userPane.setVisible(true);
        EmployeePane.setVisible(false);
        stockPane.setVisible(false);
        categoryPane.setVisible(false);
        shelfPane.setVisible(false);
        medicinePane.setVisible(false);
        paneSupplier.setVisible(false);
        panePurchase.setVisible(false);
        companyPane.setVisible(false);
        salePane.setVisible(false);
         customerPane.setVisible(false);
         reportPane.setVisible(false);
    }
    @FXML
    public void showEmployee()
    {
        dashbaordBox.setVisible(false);
        userPane.setVisible(false);
        EmployeePane.setVisible(true);
        stockPane.setVisible(false);
        shelfPane.setVisible(false);
        categoryPane.setVisible(false);
        medicinePane.setVisible(false);
        paneSupplier.setVisible(false);
        panePurchase.setVisible(false);
        companyPane.setVisible(false);
        salePane.setVisible(false);
         customerPane.setVisible(false);
         reportPane.setVisible(false);
    }
      @FXML
    private void stockForm(MouseEvent event) {
        dashbaordBox.setVisible(false);
        userPane.setVisible(false);
        EmployeePane.setVisible(false);
        stockPane.setVisible(true);
        categoryPane.setVisible(false);
        shelfPane.setVisible(false);
        medicinePane.setVisible(false);
        paneSupplier.setVisible(false);
        panePurchase.setVisible(false);
        companyPane.setVisible(false);
        salePane.setVisible(false);
         customerPane.setVisible(false);
         reportPane.setVisible(false);
    }
     @FXML
    private void showCategoryForm(MouseEvent event) {
        dashbaordBox.setVisible(false);
        userPane.setVisible(false);
        EmployeePane.setVisible(false);
        stockPane.setVisible(false);
        categoryPane.setVisible(true);
        shelfPane.setVisible(false);
        medicinePane.setVisible(false);
        paneSupplier.setVisible(false);
        panePurchase.setVisible(false);
        companyPane.setVisible(false);
        salePane.setVisible(false);
         customerPane.setVisible(false);
         reportPane.setVisible(false);
    }
    @FXML
    public void showShelfForm(){
        dashbaordBox.setVisible(false);
        userPane.setVisible(false);
        EmployeePane.setVisible(false);
        stockPane.setVisible(false);
        categoryPane.setVisible(false);
        shelfPane.setVisible(true);
        medicinePane.setVisible(false);
        paneSupplier.setVisible(false);
        panePurchase.setVisible(false);
        companyPane.setVisible(false);
        salePane.setVisible(false);
         customerPane.setVisible(false);
         reportPane.setVisible(false);
    }
    @FXML
    public void showMedicinePane(){
        dashbaordBox.setVisible(false);
        userPane.setVisible(false);
        EmployeePane.setVisible(false);
        stockPane.setVisible(false);
        categoryPane.setVisible(false);
        shelfPane.setVisible(false);
        medicinePane.setVisible(true);
        paneSupplier.setVisible(false);
        panePurchase.setVisible(false);
        companyPane.setVisible(false);
        salePane.setVisible(false);
         customerPane.setVisible(false);
         reportPane.setVisible(false);
    }
    @FXML
    public void showSupplierPane(){
        dashbaordBox.setVisible(false);
        userPane.setVisible(false);
        EmployeePane.setVisible(false);
        stockPane.setVisible(false);
        categoryPane.setVisible(false);
        shelfPane.setVisible(false);
        medicinePane.setVisible(false);
        companyPane.setVisible(false);
        paneSupplier.setVisible(true);
        salePane.setVisible(false);
        panePurchase.setVisible(false);
         customerPane.setVisible(false);
         reportPane.setVisible(false);
    }
    @FXML
    public void showPanePurchase(){
        dashbaordBox.setVisible(false);
        userPane.setVisible(false);
        EmployeePane.setVisible(false);
        stockPane.setVisible(false);
        categoryPane.setVisible(false);
        shelfPane.setVisible(false);
        medicinePane.setVisible(false);
        paneSupplier.setVisible(false);
        companyPane.setVisible(false);
        panePurchase.setVisible(true);
        salePane.setVisible(false);
         customerPane.setVisible(false);
         reportPane.setVisible(false);
    }
    @FXML
    public void showCompanyPane(){
        dashbaordBox.setVisible(false);
        userPane.setVisible(false);
        EmployeePane.setVisible(false);
        stockPane.setVisible(false);
        categoryPane.setVisible(false);
        shelfPane.setVisible(false);
        medicinePane.setVisible(false);
        paneSupplier.setVisible(false);
        panePurchase.setVisible(false);
        companyPane.setVisible(true);
        salePane.setVisible(false);
         customerPane.setVisible(false);
         reportPane.setVisible(false);
    }
    @FXML
    public void showSalePane(){
        dashbaordBox.setVisible(false);
        userPane.setVisible(false);
        EmployeePane.setVisible(false);
        stockPane.setVisible(false);
        categoryPane.setVisible(false);
        shelfPane.setVisible(false);
        medicinePane.setVisible(false);
        paneSupplier.setVisible(false);
        panePurchase.setVisible(false);
        companyPane.setVisible(false);
        customerPane.setVisible(false);
        reportPane.setVisible(false);
        salePane.setVisible(true);
    }
    @FXML
    public void showCustomerPane(){
        dashbaordBox.setVisible(false);
        userPane.setVisible(false);
        EmployeePane.setVisible(false);
        stockPane.setVisible(false);
        categoryPane.setVisible(false);
        shelfPane.setVisible(false);
        medicinePane.setVisible(false);
        paneSupplier.setVisible(false);
        panePurchase.setVisible(false);
        companyPane.setVisible(false);
        salePane.setVisible(false);
        reportPane.setVisible(false);
        customerPane.setVisible(true);
    }
    @FXML
    public void showReportPane(){
        dashbaordBox.setVisible(false);
        userPane.setVisible(false);
        EmployeePane.setVisible(false);
        stockPane.setVisible(false);
        categoryPane.setVisible(false);
        shelfPane.setVisible(false);
        medicinePane.setVisible(false);
        paneSupplier.setVisible(false);
        panePurchase.setVisible(false);
        companyPane.setVisible(false);
        salePane.setVisible(false);
        customerPane.setVisible(false);
        reportPane.setVisible(true);
    }
    // Inserting Data into User Form
    @FXML
    public void saveUser() throws Exception{ 
        
        try{
        String pass,confirmPass;
        pass=txtPassword.getText();
        confirmPass=txtCPassword.getText();
        inputStream=image.insertImage(imageView);
        String query="insert into user(UserName,UserPassword,JobStatus,Photo,Email,Phone,Date)values(?,?,?,?,?,?,?)";
        stmt=conn.prepareStatement(query);
        
        if( txtName.getText().trim().isEmpty()|| txtPassword.getText().trim().isEmpty() || txtEmail.getText().trim().isEmpty() ||
        txtContactNO.getText().trim().isEmpty() || comboJob.getSelectionModel().isEmpty() || txtDate.getValue()==null || inputStream==null){
            
            txtDate.setValue(LocalDate.now());
            lblMessageUser.setText("All Fields are required to be Fill");
            lblMessageUser.setVisible(true);
            lblStarUser.setVisible(true);
            PauseTransition visible=new PauseTransition(Duration.seconds(1));
            visible.setOnFinished(event->lblMessageUser.setVisible(false));
            visible.play();
        }
        else{
            
            stmt.setString(1, txtName.getText());
            stmt.setString(2, txtPassword.getText());
            stmt.setString(3, comboJob.getValue());
            stmt.setBinaryStream(4, inputStream);
            stmt.setString(5, txtEmail.getText());
            stmt.setString(6, txtContactNO.getText());
            stmt.setString(7, txtDate.getValue().toString());
                
            if(pass.equals(confirmPass)){
                stmt.execute();
               lblRecordAffected.setVisible(true);
               userList.clear();
               loadUserData();
               stmt.close();
               loadUserLabels();
             }
            else{
                Alert alert=new Alert(AlertType.INFORMATION);
                alert.setTitle("Message From System");
                alert.setContentText("Password is not Matching");
                alert.show();
            }
        }
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }
     
 }
    // Browseing User Image into Image Viewer 
    @FXML
    public void browseImage()
    {
        image.browseImage(imageView);
        imageView.setStyle("-fx-background-radius:50%;");
    }
    // Browsing Employee Image into Image Viwer
    @FXML
    public void browseEmpImage(){
       image.browseImage(imgEmployeeImageViewer);
       imgEmployeeImageViewer.setStyle("-fx-background-radius:50%;");
    }
    // Loading Data into Table View
    public void loadUserData() 
    {   
        String query="select * from user order by UserID Desc";
        try{
            stmt=conn.prepareStatement(query);
            ResultSet rSet=stmt.executeQuery();
            while(rSet.next()){
                userList.add(new UserList(rSet.getString(1),rSet.getString(2),
                rSet.getString(3),rSet.getString(4),rSet.getBlob(5),
                rSet.getString(6),rSet.getString(7),rSet.getString(8)));
            }
            userTableView.setItems(userList);
            stmt.close();
            
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    // Setting columns to userTableView
    public void setUserColumns(){
        columnUserID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        columnUserName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnUserPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        columnUserJobStatus.setCellValueFactory(new PropertyValueFactory<>("jobStatus"));
        columnUserPhoto.setCellValueFactory(new PropertyValueFactory<>("photo"));
        columnUserEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        columnUserPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        columnUserDate.setCellValueFactory(new PropertyValueFactory<>("date"));  
    }
   
    public void loadUserLabels(){
        // The Code must be reviewed again because of too many connections and 
        // lenghty code
        
        try{
            String query="select count(jobstatus) from user where jobstatus='Admin'";
            stmt=conn.prepareStatement(query);
            ResultSet rSetAdmin=stmt.executeQuery();
            if(rSetAdmin.next())
            {
                int admin=rSetAdmin.getInt(1);
                lblAdmin.setText(admin+"");
            }
            
            String q="select count(jobstatus) from user where jobstatus='Child'";
            stmt=conn.prepareStatement(q);
            ResultSet rSetStock=stmt.executeQuery();
            if(rSetStock.next())
            {
                int stock=rSetStock.getInt(1);
                lblStock.setText(stock+"");
            }
            
            stmt.close();
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
    // Inserting Employee Information
    @FXML
    public void insertEmployee(){
        try{
            inputStream=image.insertImage(imgEmployeeImageViewer);
            
            String query="insert into Employee (EmpName,EmpLastName,Position,BloodGroup,ContactNO,"
                    + "AlternativeNO,Email,Salary,ContractDate,ContractExpiration,Descriptions,"
                    + "CurrentAdd,PermanentAdd,Image) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            stmt=conn.prepareStatement(query);
            if(empFirstName.getText().trim().isEmpty() || empLastName.getText().trim().isEmpty() ||
                    empPosition.getValue()==null || empBloodGroup.getValue()==null || 
                    empContactNO.getText().trim().isEmpty() || empAlternativeNO.getText().trim().isEmpty() ||
                    empEmail.getText().trim().isEmpty() || empSalary.getText().trim().isEmpty() ||
                    empContractDate.getValue()==null || 
                    empDescriptions.getText().trim().isEmpty() || empCurrentAdd.getText().trim().isEmpty() ||
                    empPermanentAdd.getText().trim().isEmpty() || inputStream==null){
                empContractDate.setValue(LocalDate.now());
                empContractDate.setValue(LocalDate.now().plusYears(1));
                
                lblMessageEmployee.setText("All Fields are required to be Fill ");
                lblMessageEmployee.setVisible(true);
                lblStarEmployee.setVisible(true);
                PauseTransition visible=new PauseTransition(Duration.seconds(1));
                visible.setOnFinished(event->lblMessageEmployee.setVisible(false));
                visible.play();
            }
            else{
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
            stmt.setBinaryStream(14, inputStream);
            stmt.execute();
            lblMessage.setVisible(true);
            PauseTransition visible=new PauseTransition(Duration.seconds(1));
            visible.setOnFinished(event->lblMessage.setVisible(false));
            visible.play();
            clearEmployeeFields();
            loadTotalEmployee();
            stmt.close();
            }
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, "NULL");
        }     
    }
    // Clearing Employee TextFields
    public void clearEmployeeFields(){
        empFirstName.clear();
        empLastName.clear();
        empPosition.setValue("");
        empBloodGroup.setValue("");
        empContactNO.clear();
        empAlternativeNO.clear();
        empEmail.clear();
        empSalary.clear();
        empCurrentAdd.clear();
        empPermanentAdd.clear();  
        empDescriptions.clear();
    }
    // Loading Total Employees into Label
    public void loadTotalEmployee(){
        try{
            String query="select count(EmpID) from employee";
            stmt=conn.prepareStatement(query);
            ResultSet rSet=stmt.executeQuery();
            while(rSet.next()){
                int totalEmployee=rSet.getInt(1);
                lblTotalEmployees.setText(totalEmployee+"");
                lblTotalEmployeesCount.setText(totalEmployee+"");
            }
            stmt.close();
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
        
        // CONVERTING DATA INTO Combo cat deleted from here
        
    }
    @FXML
   public void openEmployeeMoreOptionsFrm() throws Exception{ 
       Parent root=FXMLLoader.load(getClass().getResource("/EmployeeSubForms/MoreOption.fxml"));
       Stage myStage=new Stage();
       Scene myScene=new Scene(root);
       myStage.setScene(myScene);
       myStage.show();
   }
   

    @FXML
    private void saveCategory(){
        String query="insert into category (CatName)values(?)";
        try{
            stmt=conn.prepareStatement(query);
            stmt.setString(1, txtCatName.getText());
            if(txtCatName.getText().isEmpty()){
                msgEmpty.setText("Empty Category");
                msgEmpty.setVisible(true);
                PauseTransition ps=new PauseTransition(Duration.seconds(1));
                ps.setOnFinished(event->msgEmpty.setVisible(false));
                ps.play();
                lblStarMarkCat.setVisible(true);
            }
            else if(txtCatName.getText().matches("^[a-zA-Z]*$")){
                stmt.execute();
                txtCatName.setText("");
                cList.clear();
                loadCategoryList();
                lblStarMarkCat.setVisible(false);
            }
            else{
                msgEmpty.setText("Numbers are not Allowed");
                msgEmpty.setVisible(true);
                PauseTransition ps=new PauseTransition(Duration.seconds(1));
                ps.setOnFinished(event->msgEmpty.setVisible(false));
                ps.play();
                lblStarMarkCat.setVisible(true);
                
            }
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
    }
 
    public void loadCategoryList(){
          String query="select * from category";
          try{
              
              stmt=conn.prepareStatement(query);
              ResultSet rSet=stmt.executeQuery();
              while(rSet.next()){
                  cList.add(new CategoryList(rSet.getString(1),rSet.getString(2)));      
              }  
          }
          catch(Exception ex){
              JOptionPane.showMessageDialog(null, ex);
          }
    }
    
    public void setColumnsCategoryTable(){
        // NOte: in case of any error in the columns, generate setter and getter by the system.
        columnCatID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        columnCatName.setCellValueFactory(new PropertyValueFactory<>("name")); 
        // Searching Record in Table Category
        FilteredList<CategoryList>filterData=new FilteredList<>(cList,b->true);
        txtSearchCategory.textProperty().addListener((observable,oldValue,newValue)->{
            filterData.setPredicate(catList->{
                if(newValue==null || newValue.isEmpty())
                    return true;
            String lowerCase=newValue.toLowerCase();
            if(catList.getName().toLowerCase().indexOf(lowerCase)!=-1)
                return true;
            else if(catList.getID().indexOf(lowerCase)!=-1)
                return true;
             
            return false;
            });
        });
        SortedList<CategoryList>sortedData=new SortedList<>(filterData);
        sortedData.comparatorProperty().bind(tblViewCategory.comparatorProperty());
        tblViewCategory.setItems(sortedData);
        
    }
    // Delete Query for Category
    @FXML
    public void deleteCategory(){
        String query="delete from Category where CatID=?";
         Alert alert=new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Pharmacy Management");
            alert.setContentText("Are you Sure to Delete the Record");
         Optional<ButtonType>btnAction=alert.showAndWait();
         if(btnAction.get()==ButtonType.OK){
        try{
            stmt=conn.prepareStatement(query);
            stmt.setInt(1, categoryID);
            stmt.execute();
            cList.clear();
            loadCategoryList();
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }
     }
 }
    // Updating Category
    @FXML
    public void updateCategory(){
        String query="update category set catName=? where catID=?";
        try{
            stmt=conn.prepareStatement(query);
            stmt.setString(1, txtCatName.getText());
            categoryID=Integer.parseInt(catList.getID());
            stmt.setInt(2, categoryID);
            stmt.executeUpdate();
            cList.clear();
            loadCategoryList();
            
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    // Table Category Clicked
    public void tblCategoryClicked(){
        tblViewCategory.setOnMouseClicked(event->{
        catList=tblViewCategory.getItems().get(tblViewCategory.getSelectionModel().getSelectedIndex());
        categoryID=Integer.parseInt(catList.getID());
        txtCatName.setText(catList.getName());
        btnCategoryUpdate.setVisible(true);
        btnCategorySave.setVisible(false);
        btnCategoryCancel.setVisible(true);
        });
        btnCategoryCancel.setOnMouseClicked(event->{
            txtCatName.setText(null);
            btnCategoryUpdate.setVisible(false);
            btnCategorySave.setVisible(true);
            btnCategoryCancel.setVisible(false);
                    
        });
    }
    
    //Shelf Insertion
    @FXML
    public void insertShelf(){
        String query="insert into Shelf (ShelfName,NumericNumber,Date,Descriptions)values"
                + "(?,?,?,?)";
        try{
            
            if (txtShelfName.getText().trim().isEmpty() && txtShelfNO.getText().trim().isEmpty() &&  
                txtShelfDate.getValue()==null && txtShelfDescriptions.getText().trim().isEmpty())
            { 
                txtShelfDate.setValue(LocalDate.now());
                msgEmptyShelf.setText("All Fields are required to be Fill");
                msgEmptyShelf.setVisible(true);
                lblStarShelf.setVisible(true);
                PauseTransition visibleShelf=new PauseTransition(Duration.seconds(1));
                visibleShelf.setOnFinished(event->msgEmptyShelf.setVisible(false));
                visibleShelf.play();
                
            }
            else if(txtShelfNO.getText().matches("^[a-zA-Z]*$") ){
                msgEmptyShelf.setText("Only Numbers are Allowed");
                msgEmptyShelf.setVisible(true);
                lblShelfNMark.setVisible(true);
                PauseTransition visibleShelf=new PauseTransition(Duration.seconds(1));
                visibleShelf.setOnFinished(event->msgEmptyShelf.setVisible(false));
                visibleShelf.play();
            }
            else{
            stmt=conn.prepareStatement(query);
  
            stmt.setString(1, txtShelfName.getText());
            stmt.setString(2, txtShelfNO.getText());
            stmt.setString(3, txtShelfDate.getValue().toString());
            stmt.setString(4, txtShelfDescriptions.getText());
            stmt.execute();
            lblShelfSaved.setVisible(true);
            PauseTransition visibleShelf=new PauseTransition(Duration.seconds(1));
            visibleShelf.setOnFinished(event->lblShelfSaved.setVisible(false));
            visibleShelf.play();
            clearShelf();
            sList.clear();
            loadShelfData();
            countShelfes();
            lblShelfNMark.setVisible(false);
            lblShelfNMark.setVisible(false);
            }
            
        }
        catch(SQLException ex){
             JOptionPane.showMessageDialog(null, "System Error");
        }
    }
    // Loading Data for Shelf
    public void loadShelfData(){
        String query="select * from Shelf";
        try{
            stmt=conn.prepareStatement(query);
            ResultSet rSet=stmt.executeQuery();
            while(rSet.next()){
                sList.add(new ShelfList(rSet.getString(1),rSet.getString(2),rSet.getString(3),
                rSet.getString(4),rSet.getString(5)));  
            }
            tblViewShelf.setItems(sList);
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    //Update Shelf Record Context Menu
    @FXML
    public void updateShelf(){
        String query="update Shelf set ShelfName=?,NumericNumber=?,Date=?,Descriptions=? "
                + "where shelfID=? ";
        try{
            stmt=conn.prepareStatement(query);
            stmt.setString(1, txtShelfName.getText());
            stmt.setString(2, txtShelfNO.getText());
            stmt.setString(3, txtShelfDate.getValue().toString());
            stmt.setString(4, txtShelfDescriptions.getText());
            shelfID=Integer.parseInt(shelfList.getId());
            stmt.setInt(5, shelfID);
            stmt.executeUpdate();
            sList.clear();
            loadShelfData();         
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    //Delete Shelf Record 
    @FXML
    public void deleteShelf(){
        String query="delete from shelf where shelfID=?";
        Alert alert=new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Pharmacy Management");
        alert.setContentText("Are you Sure to Delete the Record");
        Optional<ButtonType>btnAction=alert.showAndWait();
        if(btnAction.get()==ButtonType.OK){
        try{
            stmt=conn.prepareStatement(query);
            stmt.setInt(1, shelfID);
            stmt.execute();
            sList.clear(); 
            loadShelfData();
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }   
    }
 }
    // Method for Clearing Shelf TextFields
    public void clearShelf(){
        txtShelfName.setText("");
        txtShelfNO.setText("");
        txtShelfDescriptions.setText("");
        txtShelfDate.setValue(null);
    }
    //Set Columns for Shelf
    public void setColumnsShelfTable(){
        shelfCID.setCellValueFactory(new PropertyValueFactory<>("id"));
        shelfCName.setCellValueFactory(new PropertyValueFactory<>("name"));
        shelfCNumber.setCellValueFactory(new PropertyValueFactory<>("numericNumber"));
        shelfCDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        shelfCDescriptions.setCellValueFactory(new PropertyValueFactory<>("descriptions"));
        FilteredList<ShelfList> filter=new FilteredList<>(sList,b->true);
        txtShelfSearch.textProperty().addListener((observable,oldValue,newValue)->{
            filter.setPredicate(sList->{
                String lowerCase=newValue.toLowerCase();
                if(sList.getName().toLowerCase().indexOf(lowerCase)!=-1)
                    return true;
                else if(sList.getId().indexOf(lowerCase)!=-1)
                    return true;
                else if(sList.getNumericNumber().indexOf(lowerCase)!=-1)
                    return true;
                
                return false;    
            });
        });
        SortedList<ShelfList> sort=new SortedList<>(filter);
        sort.comparatorProperty().bind(tblViewShelf.comparatorProperty());
        tblViewShelf.setItems(sort);
    }
    // Counting Shelfes
    public void countShelfes(){
        String query="select count(shelfID) from shelf";
        try{
            stmt=conn.prepareStatement(query);
            ResultSet rSet=stmt.executeQuery();
            while(rSet.next()){
                int shelfID=rSet.getInt(1);
                txtCountShelfes.setText(shelfID+"");
            }
            stmt.close();
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    //Table View Shelf on Click Event
    public void tblShelfClicked(){
        tblViewShelf.setOnMouseClicked(event->{
            shelfList=tblViewShelf.getItems().get(tblViewShelf.getSelectionModel().getSelectedIndex());
            shelfID=Integer.parseInt(shelfList.getId());
            lblShelfID.setText(shelfList.getId());
            lblShelfName.setText(shelfList.getName());
            lblShelfNumber.setText(shelfList.getNumericNumber());
            lblShelfDate.setText(shelfList.getDate());
            lblShelfDescriptions.setText(shelfList.getDescriptions());
            viewPaneShelf.setVisible(true);
            btnShelfViewClose.setVisible(true);
            contextBUpdateShelf.setOnAction(e->{
                txtShelfName.setText(shelfList.getName());
                txtShelfNO.setText(shelfList.getNumericNumber());
                txtShelfDescriptions.setText(shelfList.getDescriptions());
                btnShelfSave.setVisible(false);
                btnUpdateShelf.setVisible(true);
                btnCancelShelf.setVisible(true);
            });
            
        });
    }
    // Canceling Update data in shelf textboxes
    @FXML
    public void cancelShelfData(){
        btnUpdateShelf.setVisible(false);
        btnShelfSave.setVisible(true);
        btnCancelShelf.setVisible(false);
        clearShelf();
    }
    //close Shelf View
    @FXML
    public void closeShelfView(){
        viewPaneShelf.setVisible(false);
        btnShelfViewClose.setVisible(false);
    }
    //Medicine Portion
    // Loading date into Combo Boxes
    public void loadCatMedicine(){
        String query="select * from category";
        try{
            stmt=conn.prepareStatement(query);
            ResultSet rSet=stmt.executeQuery();
            while(rSet.next()){
                cList.add(new CategoryList(rSet.getString(1),rSet.getString(2))); 
            }
           
            comboMCatName.setItems(cList);
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
    comboMCatName.setConverter(new StringConverter<CategoryList>(){
            @Override
            public String toString(CategoryList object) {
            return object.getName();
            }
            @Override
            public CategoryList fromString(String string) {
             return null;
            }  
    });
    comboMCatName.valueProperty().addListener((obs,oldValue,newValue)->{
        if(newValue!=null){
            categoryID=Integer.parseInt(newValue.getID());
        }
    });
 }
    //loading Shelf data into Medicine form
    public void loadShelfMedicine(){
        String query="select * from shelf";
        try{
            stmt=conn.prepareStatement(query);
            ResultSet rSet=stmt.executeQuery();
            while(rSet.next()){
                sList.add(new ShelfList(rSet.getString(1),rSet.getString(2),rSet.getString(3),rSet.getString(4),rSet.getString(5)));
            }
            comboMShelfName.setItems(sList);
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
        comboMShelfName.setConverter(new StringConverter<ShelfList>(){
            @Override
            public String toString(ShelfList object) {
                return object.getName();
            }

            @Override
            public ShelfList fromString(String string) {
               return null;
            } 
        });
        comboMShelfName.valueProperty().addListener((obs,oldValue,newValue)->{
            if(newValue!=null){
                shelfID=Integer.parseInt(newValue.getId());
            }
        });
    }
    //Saving medicine data
    @FXML
    public void saveMedicine(){
        String query="insert into Medicine(CatID,ShelfID,Name,BatchNO,RDate,QuantityInHand)values(?,?,?,?,?,0)";
        try{
            if(categoryID==0 || shelfID==0 || txtMedicneName.getText().trim().isEmpty() || 
                    txtMedicineBatchNO.getText().trim().isEmpty() || medicineRegDate.getValue()==null){
                lblMessageMedicine.setText("All Fields are required to be Fill");
                lblMessageMedicine.setVisible(true);
                lblStarMedicine.setVisible(true);
                PauseTransition visibleShelf=new PauseTransition(Duration.seconds(1));
                visibleShelf.setOnFinished(event->lblMessageMedicine.setVisible(false));
                visibleShelf.play();
            }
            else{
            stmt=conn.prepareStatement(query);
            stmt.setInt(1, categoryID);
            stmt.setInt(2, shelfID);
            stmt.setString(3, txtMedicneName.getText());
            stmt.setString(4, txtMedicineBatchNO.getText());
            stmt.setString(5, medicineRegDate.getValue().toString());
            stmt.execute();
            txtMedicineMessageSave.setVisible(true);
            PauseTransition pt=new PauseTransition(Duration.seconds(1));
            pt.setOnFinished(event->txtMedicineMessageSave.setVisible(false));
            pt.play();
            mList.clear();
            laodMedicineData();
            
            loadIntoStock();
            stmt.close();
            lblStarMedicine.setVisible(false);
            }
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    //loading date into TableView Medicine
    public void laodMedicineData(){
        //using inner join to retrieve Category Name and shelf name instead of their
        //ids
        String query="select Medicine.MedicineID,CatName,ShelfName,Name,BatchNO,Rdate from medicine "
                + "inner JOIN category ON medicine.CatID=Category.CatID "
                + "inner join Shelf ON medicine.ShelfID=shelf.ShelfID";
   
        try{
            stmt=conn.prepareStatement(query);
            ResultSet rSet=stmt.executeQuery();
            while(rSet.next()){
                mList.add(new MedicineList(rSet.getString(1),rSet.getString(2),rSet.getString(3),rSet.getString(4),rSet.getString(5),rSet.getString(6)));     
            }
            tblViewMedicine.setItems(mList);
            stmt.close();
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }      
    }
    //Setting Columns data to Medicine Table
    public void setMedicineColumns(){
        colMID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colMCat.setCellValueFactory(new PropertyValueFactory<>("catID"));
        colMShelf.setCellValueFactory(new PropertyValueFactory<>("shelfID"));
        colMName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colMBatch.setCellValueFactory(new PropertyValueFactory<>("batchNO"));
        colMDate.setCellValueFactory(new PropertyValueFactory<>("date")); 
        
        FilteredList<MedicineList>filter=new FilteredList<>(mList,b->true);
        txtSearchMedicine.textProperty().addListener((observable,oldValue,newValue)->{
            filter.setPredicate(predicate->{
                String lowerCase=newValue.toLowerCase();
                if(newValue==null || newValue.isEmpty())
                    return true;
                if(predicate.getName().toLowerCase().indexOf(lowerCase)!=-1)
                    return true;
                else if(predicate.getBatchNO().indexOf(lowerCase)!=-1)
                    return true;
                else if(predicate.getDate().indexOf(lowerCase)!=-1)
                    return true;
                else if(predicate.getId().indexOf(lowerCase)!=-1)
                    return true;  
                else if(predicate.getCatID().toLowerCase().indexOf(lowerCase)!=-1)
                    return true;
                else if(predicate.getShelfID().toLowerCase().indexOf(lowerCase)!=-1)
                    return true;
                return false; 
            });
        });
        SortedList<MedicineList> sort=new SortedList<>(filter);
        sort.comparatorProperty().bind(tblViewMedicine.comparatorProperty());
        tblViewMedicine.setItems(sort);
    }
    //Option Buttons for Medicine Search
    
    //Viewing Medicine Data in the Pane 
    @FXML
    public void viewMedicineData(){
        medicineList=tblViewMedicine.getItems().get(tblViewMedicine.getSelectionModel().getSelectedIndex());
        viewMadecinePane.setVisible(true);
        medicineID=Integer.parseInt(medicineList.getId());
        lblMID.setText(medicineList.getId());
        lblMCatName.setText(medicineList.getCatID());
        lblMShelfName.setText(medicineList.getShelfID());
        lblMName.setText(medicineList.getName());
        lblMBatchNO.setText(medicineList.getBatchNO());
        lblMDate.setText(medicineList.getDate());
        btnCloseViewMedicine.setVisible(true);
        
    }
    // close medicineView
    @FXML
    public void closeMedicineView(){
        viewMadecinePane.setVisible(false);
        btnCloseViewMedicine.setVisible(false);
    }
    //Delete single Record from Medicine
    @FXML
    public void delteMedicine(){
        String query="delete from medicine where MedicineID=?";
        try{
            Alert alert=new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Message from System");
            alert.setContentText("Aer You Sure to Delete the Record");
            Optional<ButtonType>btnAction=alert.showAndWait();
            if(btnAction.get()==ButtonType.OK){
            medicineID=Integer.parseInt(medicineList.getId());
            stmt=conn.prepareStatement(query);
            stmt.setInt(1, medicineID);
            stmt.execute();
            mList.clear();
            laodMedicineData();
            countTotalMedicine();
            }
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    //Deleting all data from medicine
    @FXML
    public void deleteAllMedicine(){
        String query="delete from medicine";
        try{
            Alert alert=new Alert(AlertType.CONFIRMATION);
            alert.setContentText("Are You Sure to Delete all the Records Of Medicine");
            alert.setTitle("Message from System");
            Optional<ButtonType>btnAction=alert.showAndWait();
            if(btnAction.get()==ButtonType.OK){
            stmt=conn.prepareStatement(query);
            stmt.execute();
            mList.clear();
            laodMedicineData();
            countTotalMedicine();
            }
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    //Loading Medicine Total Records
    public void countTotalMedicine(){
        try{
            String query="select count(MedicineID) from medicine";
            stmt=conn.prepareStatement(query);
            ResultSet rSet=stmt.executeQuery();
            while(rSet.next()){
                medicineID=rSet.getInt(1);
                lblTotalMedicine.setText(medicineID+"");
            }  
            stmt.close();
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    //tblMedicineClicked
    public void tblMedicineClicked(){
        tblViewMedicine.setOnMouseClicked(event->{
            medicineList=tblViewMedicine.getItems().get(tblViewMedicine.getSelectionModel().getSelectedIndex()); 
        });
    }
    //Supplier Section
    //Inserting Date into Supplier 
    
    @FXML
    public void saveSupplier(){
        try{
            String query="insert into Suppliar(name,email,phone,address,descriptions,regDate)"
                    + "values(?,?,?,?,?,?)";
            if(txtSupplierName.getText().trim().isEmpty() || txtSupplierEmail.getText().trim().isEmpty() ||
                    txtSupplierPhone.getText().trim().isEmpty() || txtSupplierAddress.getText().trim().isEmpty() ||
                    txtSupplierDescriptions.getText().trim().isEmpty() || txtDateSupplier.getValue()==null){
                lblMessageSupplier.setText("All Fields are required to be fill");
                lblMessageSupplier.setVisible(true);
                lblStarSupplier.setVisible(true);
                PauseTransition pt=new PauseTransition(Duration.seconds(1));
                pt.setOnFinished(event->lblMessageSupplier.setVisible(false));
                pt.play();
            }
            else if(txtSupplierPhone.getText().matches("^[a-zA-Z]*$"))
            {
                lblMessageSupplier.setText("Please Type Phone Number");
                lblMessageSupplier.setVisible(true);
                lblStarSupplier.setVisible(true);
                PauseTransition pt=new PauseTransition(Duration.seconds(1));
                pt.setOnFinished(event->lblMessageSupplier.setVisible(false));
                pt.play();
            }
            else{
            stmt=conn.prepareStatement(query);
            stmt.setString(1, txtSupplierName.getText());
            stmt.setString(2, txtSupplierEmail.getText());
            stmt.setString(3, txtSupplierPhone.getText());
            stmt.setString(4, txtSupplierAddress.getText());
            stmt.setString(5, txtSupplierDescriptions.getText());
            stmt.setString(6, txtDateSupplier.getValue().toString());
            stmt.execute();
            lblSupplierMessage.setVisible(true);
            PauseTransition ps=new PauseTransition(Duration.seconds(1));
            ps.setOnFinished(event->{
                lblSupplierMessage.setVisible(false);
            });
            ps.play();
            txtSupplierName.setText("");
            txtSupplierEmail.setText("");
            txtSupplierPhone.setText("");
            txtSupplierAddress.setText("");
            txtSupplierDescriptions.setText("");
            supplierList.clear();
            loadSupplier();
            }
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    // Loading Date into Supplier Table
    public void loadSupplier(){
        try{
            String query="select * from suppliar";
            stmt=conn.prepareStatement(query);
            ResultSet rSet=stmt.executeQuery();
            while(rSet.next()){
                supplierList.add(new SupplierList(rSet.getString(1),rSet.getString(2),
                rSet.getString(3),rSet.getString(4),rSet.getString(5),rSet.getString(6),rSet.getString(7))); 
            }
            tblViewSupplier.setItems(supplierList);
            setSupplierColumns();
            totalSuppliers();
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    // Setting Columns for Supplier Table
    public void setSupplierColumns(){
        colSupplierID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colSupplierName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colSupplierPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colSupplierEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colSupplierAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colSupplierDescriptions.setCellValueFactory(new PropertyValueFactory<>("descriptions"));
        colSupplierRegDate.setCellValueFactory(new PropertyValueFactory<>("regDate"));
    }
    // Counting total Suppliers
    public void totalSuppliers(){
        try{
            String query="select count(SupplierID) from Suppliar";
            stmt=conn.prepareStatement(query);
            ResultSet rSet=stmt.executeQuery();
            while(rSet.next()){
                supplierID=rSet.getInt(1);
                lblTotalSupplier.setText(supplierID+"");
            }         
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    // on table supplier clicked
    public void supplierTblClicked(){
        tblViewSupplier.setOnMouseClicked(event->{
            supList=tblViewSupplier.getItems().get(tblViewSupplier.getSelectionModel().getSelectedIndex());
            supplierViewPane.setVisible(true);
            lblSupplierID.setText(supList.getId());
            lblSupplierName.setText(supList.getName());
            lblSupplierEmail.setText(supList.getEmail());
            lblSupplierPhone.setText(supList.getPhone());
            lblSupplierDate.setText(supList.getRegDate());
            lblSupplierDescriptions.setText(supList.getDescriptions());
            lblSupplierAddress.setText(supList.getAddress());
            
        });
    }
    // Opening Supplier Update Form
    @FXML
    public void openSupplierUpdateForm() throws Exception{
       FXMLLoader loader=new FXMLLoader();
       loader.setLocation(getClass().getResource("/Supplier/UpdateSupplier.fxml"));
       try{
           loader.load();
       }
       catch(Exception ex){
           JOptionPane.showMessageDialog(null, ex);
       }
       supList=tblViewSupplier.getItems().get(tblViewSupplier.getSelectionModel().getSelectedIndex());
       UpdateSupplierController control=loader.getController();
       Parent root=loader.getRoot();
       Stage myStage=new Stage();
       myStage.setScene(new Scene(root));
       myStage.setResizable(false);
       myStage.show();
    
       control.setID(supList.getId());
       control.setName(supList.getName());
       control.setEmail(supList.getEmail());
       control.setPhone(supList.getPhone());
       control.setAddress(supList.getAddress());
       control.setDate(supList.getRegDate());
       control.setDescriptions(supList.getDescriptions());
    }
    
    @FXML
    public void refreshSupplier(){
        supplierList.clear();
       tblViewSupplier.setItems(supplierList);
       loadSupplier();
    }
    // Deleting Supplier
    @FXML
    public void deleteSupplier(){
        try{
            String query="delete from Suppliar where SupplierID=?";
            Alert alert=new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Message from System");
            alert.setContentText("Are you sure to Delete the Record");
            Optional<ButtonType> btnAction=alert.showAndWait();
            if(btnAction.get()==ButtonType.OK){
                supplierID=Integer.parseInt(supList.getId());
                stmt=conn.prepareStatement(query);
                stmt.setInt(1, supplierID);
                stmt.execute();
                supplierList.clear();
                loadSupplier();
            }
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    // Delete All from Supplier
    @FXML
    public void deleteAllSupplier(){
        try{
            String query="delete from suppliar";
            Alert alert=new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Message from System");
            alert.setContentText("Are you sure to Delete the Record");
            Optional<ButtonType> btnAction=alert.showAndWait();
            if(btnAction.get()==ButtonType.OK){
                stmt=conn.prepareStatement(query);
                stmt.execute();
                supplierList.clear();
                loadSupplier();   
            }
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }         
    }
    // Loading Purchase Combo Boxes with Data (Supplier)
    public void loadPSupplierCombo(){
        try{
            String query="select * from Suppliar";
            stmt=conn.prepareStatement(query);
            ResultSet rSet=stmt.executeQuery();
            while(rSet.next()){
                supplierList.add(new SupplierList(rSet.getString(1),rSet.getString(2),rSet.getString(3),
                rSet.getString(4),rSet.getString(5),rSet.getString(6),rSet.getString(7)));
            }
            comboPurchaseID.setItems(supplierList);
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
        comboPurchaseID.setConverter(new StringConverter<SupplierList>(){
            @Override
            public String toString(SupplierList object) {
                return object.getName();
            }

            @Override
            public SupplierList fromString(String string) {
              return null;
            } 
    });
    comboPurchaseID.valueProperty().addListener((obs,oldValue,newValue)->{
        if(newValue!=null)
            supplierID=Integer.parseInt(newValue.getId());
    });        
    }
    //  Loading Purchase Combo Boxe with Data (Medicine)
    public void loadPMedicine(){
        try{
            String query="select * from medicine";
            stmt=conn.prepareStatement(query);
            ResultSet rSet=stmt.executeQuery();
            while(rSet.next()){
                mList2.add(new MedicineList(rSet.getString(1),rSet.getString(2),
                rSet.getString(3),rSet.getString(4),rSet.getString(5),rSet.getString(6)));  
            }
            mList2.clear();
            comboPMedicineID.setItems(mList2);
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
        comboPMedicineID.setConverter(new StringConverter<MedicineList>(){
            @Override
            public String toString(MedicineList object) {
                return object.getName();
            }

            @Override
            public MedicineList fromString(String string) {
               return null;
            }
        });
        comboPMedicineID.valueProperty().addListener((obs,oldValue,newValue)->{
            if(newValue!=null)
                medicineID=Integer.parseInt(newValue.getId());
        });
    }
    // Loading PurchaseCategory
    public void loadPCategory(){
        try{
            String query="select * from category";
            stmt=conn.prepareStatement(query);
            ResultSet rSet=stmt.executeQuery();
            while(rSet.next()){
                cList.add(new CategoryList(rSet.getString(1),rSet.getString(2)));
            }
            comboPCategory.setItems(cList);
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
        
        comboPCategory.setConverter(new StringConverter<CategoryList>(){
            @Override
            public String toString(CategoryList object) {
               return object.getName();
            }

            @Override
            public CategoryList fromString(String string) {
               return null;
            }  
        });
       comboPCategory.valueProperty().addListener((obs,oldValue,newValue)->{
           categoryID=Integer.parseInt(newValue.getID());
       });
    }
    // Storeing Purchase
    @FXML
    public void savePurchase(){
        if(supplierID==0 || purchaseDate.getValue()==null ||
                    medicineID==0 || purchaseQuantity.getText()==null ||
                    purchseItemPrice.getText()==null){
                Alert alert=new Alert(AlertType.WARNING);
                alert.setContentText("Please make Sure that all fields contains Data");
                alert.showAndWait();
            }
        else{
            try{
             String query="insert into Purchase(SuppliarID,Date,MedicineID,Quantity,PurchasePrice,TotalPrice,CategoryID,ComID)"
                    + "VALUES(?,?,?,?,?,?,?,?)";
             
            stmt=conn.prepareStatement(query);
            stmt.setInt(1, supplierID);
            stmt.setString(2, purchaseDate.getValue().toString());
            stmt.setInt(3, medicineID);
            stmt.setString(4, purchaseQuantity.getText());
            stmt.setString(5, purchseItemPrice.getText());
            stmt.setString(6, purchaseTotalPrice.getText());
            stmt.setInt(7, categoryID);
            stmt.setInt(8, companyID);
            stmt.execute();
            loadIntoStock();
            purchaseItemMessage.setVisible(true);
            PauseTransition pt=new PauseTransition(Duration.seconds(1));
            pt.setOnFinished(event->purchaseItemMessage.setVisible(false));
            pt.play();
            purchaseQuantity.setText(""); 
            purchseItemPrice.setText("");
            purchaseTotalPrice.setText("");
            purchaseList.clear();
            loadTblViewPurchase();
            }
            catch(Exception ex){
                JOptionPane.showMessageDialog(null, ex);
            }
    }
  }
 
    //Calculating Purchase Price
    @FXML
    public void calculatePurchase(){
        int price=Integer.parseInt(purchseItemPrice.getText());
        int qty=Integer.parseInt(purchaseQuantity.getText());
        int total=price*qty;
        purchaseTotalPrice.setText(total+"");
       
    }
    // Loading table Purchase
    public void loadTblViewPurchase(){
        try{
            String query="Select PurchaseID, s.Name as SupplierName,Date, m.Name as MedicineName,Quantity,purchasePrice,TotalPrice,"
                    + "catName,p.comName as CompanyName from purchase"
                    + " inner JOIN suppliar s ON purchase.SuppliarID=s.SupplierID"
                    + " inner JOIN medicine m ON purchase.MedicineID=m.MedicineID"
                    + " inner Join Category c ON purchase.CategoryID=c.catID"
                    + " inner Join Company p ON purchase.comID=p.ComID";
            stmt=conn.prepareStatement(query);
            ResultSet rSet=stmt.executeQuery();
            while(rSet.next()){
                purchaseList.add(new PurchaseList(rSet.getString(1),rSet.getString(2),
                rSet.getString(3),rSet.getString(4),rSet.getString(5),rSet.getString(6),rSet.getString(7),
                rSet.getString(8),rSet.getString(9)));
            }
           // purchaseList.clear();
            tblViewPurchase.setItems(purchaseList);
             
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    // Set Columns for Table Purchase
    public void setPurchaseColumns(){
        columnPurchaseID.setCellValueFactory(new PropertyValueFactory<>("PurchaseID"));
        columnPSupplierName.setCellValueFactory(new PropertyValueFactory<>("SupplierID"));
        columnPMedicineName.setCellValueFactory(new PropertyValueFactory<>("medicineID"));
        columnPurchaseDate.setCellValueFactory(new PropertyValueFactory<>("purchaseDate"));
        columnPurcahseQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        columnPurchasePrice.setCellValueFactory(new PropertyValueFactory<>("purchasePrice"));
        columnPurchaseTotalPrice.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        colPurchaseCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colPurchaseCompName.setCellValueFactory(new PropertyValueFactory<>("companyName"));
        FilteredList<PurchaseList> filter=new FilteredList<>(purchaseList,b->true);
        txtSearchPurchase.textProperty().addListener((observable,oldValue,newValue)->{
            filter.setPredicate(pL->{
                String lowerCase=newValue.toLowerCase();
                if(pL.getSupplierID().toLowerCase().indexOf(lowerCase)!=-1)
                    return true;
                else if(pL.getMedicineID().toLowerCase().indexOf(lowerCase)!=-1)
                    return true;
                else if(pL.getPurchaseID().indexOf(lowerCase)!=-1)
                    return true;
                else if(pL.getPurchaseDate().toLowerCase().indexOf(lowerCase)!=-1)
                    return true;
             
                return false;
            });
        });
        SortedList<PurchaseList> sort=new SortedList<>(filter);
        sort.comparatorProperty().bind(tblViewPurchase.comparatorProperty());
        tblViewPurchase.setItems(sort);
        
    }
    // Purchase Table View Clicked
    public void tblPurchaseClicked(){
        tblViewPurchase.setOnMouseClicked(event->{
            purList=tblViewPurchase.getItems().get(tblViewPurchase.getSelectionModel().getSelectedIndex());
            purchaseViewPane.setVisible(true);
            lblPurchaseID.setText(purList.getPurchaseID());
            lblPSupplierName.setText(purList.getSupplierID());
            lblPMedicineName.setText(purList.getMedicineID());
            lblPurchaseDate.setText(purList.getPurchaseDate());
            lblPurchaseQuantity.setText(purList.getQuantity());
            lblPurchasePrice.setText(purList.getPurchasePrice());
            lblPurchaseTotalPrice.setText(purList.getTotalPrice());
            lblCategoryPurchase.setText(purList.getCategory());
            lblCompanyPurchase.setText(purList.getCompanyName());
        });
    }
    // Opening update purchase form
    @FXML
    public void openUpdatePurchaseForm(){
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("/UpdatePurchase/UpdatePurchsae.fxml"));
        try{
            loader.load();
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
        purList=tblViewPurchase.getItems().get(tblViewPurchase.getSelectionModel().getSelectedIndex());
        UpdatePurchsaeController control=loader.getController();
        Parent root=loader.getRoot();
        Stage myStage=new Stage();
        myStage.setScene(new Scene(root));
        myStage.setResizable(false);
        myStage.show();
        
        control.setPurchaseID(purList.getPurchaseID());
        control.setSupplierName(purList.getSupplierID());
        control.setMedicineName(purList.getMedicineID());
        control.setDate(purList.getPurchaseDate());
        control.setQuantity(purList.getQuantity());
        control.setPrice(purList.getPurchasePrice());
        control.setTotalPrice(purList.getTotalPrice());  
        control.setCompanyName(purList.getCompanyName());
        control.setCategory(purList.getCategory());
    }
    // Refreshing data on refresh button purchase
    @FXML
    public void refresh(){
        purchaseList.clear();
        loadTblViewPurchase();
    }
    // Deleting Purchase Item
    @FXML
    public void deletePurchase(){
        try{
            String query="delete from Purchase where PurchaseID=?";
            Alert alert=new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Message from System");
            alert.setContentText("Are You sure to Delete the Record");
            Optional<ButtonType>btnAction=alert.showAndWait();
            if(btnAction.get()==ButtonType.OK){
                purchaseID=Integer.parseInt(purList.getPurchaseID());
                            stmt=conn.prepareStatement(query);
                            stmt.setInt(1, purchaseID);
                            stmt.execute();
                            purchaseList.clear();
                            loadTblViewPurchase();
            }   
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
    // loading Medicine Name into Stock form 
    public void loadProductItems(){
        
       String query="select * from medicine";
       try{
            stmt=conn.prepareStatement(query);
            ResultSet rSet=stmt.executeQuery();
            while(rSet.next()){
                mList2.add(new MedicineList(rSet.getString(1),rSet.getString(2),
                rSet.getString(3),rSet.getString(4),rSet.getString(5),rSet.getString(6)));  
            }
            comboStockProduct.setItems(mList2);
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }   
        
        comboStockProduct.setConverter(new StringConverter<MedicineList>(){
           @Override
           public String toString(MedicineList object) {
               return object.getName();
           }

           @Override
           public MedicineList fromString(String string) {
              return null;
           }
            
        });
        comboStockProduct.valueProperty().addListener((obs,oldValue,newValue)->{
            if(newValue!=null){
                medicineID=Integer.parseInt(newValue.getId());
            }
        });
    }
    // Counting Total Purchases
    public void totalPurchases(){
           String query="select count(PurchaseID) from Purchase";
        try{
            stmt=conn.prepareStatement(query);
            ResultSet rSet=stmt.executeQuery();
            while(rSet.next()){
                int purchaseID=rSet.getInt(1);
                txtTotalPurchases.setText(purchaseID+"");
            }
            stmt.close();
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
    // Click event when selection item in combo box Stock
    // Stock Form
    // Loading date into Stock Form from Purchase and Sale Tabels
    
    public void loadIntoStock(){
        try{
            
       String query="Select Medicine.MedicineID,Name, @pur:=(Select IFNULL(SUM(Purchase.Quantity),0) from Purchase Where Purchase.MedicineID=medicine.MedicineID) as TotalPurchase,\n" +
"@sal:=(Select IFNULL(SUM(Sale.SaleQuantity),0) from Sale Where Sale.MedicineID=medicine.MedicineID) AS TotalSale,\n" +
"@pur-@sal as TotalRemanings\n" +
"\n" +
"from medicine\n" +
"Left Join Purchase ON Purchase.MedicineID=medicine.MedicineID\n" +
"Left Join Sale ON Sale.MedicineID=medicine.MedicineID\n" +
"Group by Medicine.MedicineID";
            stmt=conn.prepareStatement(query);
            ResultSet rSet=stmt.executeQuery();
           
            while(rSet.next()){
                stockList.add(new StockList(rSet.getString(1),rSet.getString(2),rSet.getString(3),rSet.getString(4),rSet.getString(5)));
               
            }
            tblViewStock.setItems(stockList);
                
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
//               
    }
     // Setting columns for TblViewStock
    public void setStockColumns(){
        colStockPID.setCellValueFactory(new PropertyValueFactory<>("proID"));
        colProducName.setCellValueFactory(new PropertyValueFactory<>("proName"));
        colStockQuantity.setCellValueFactory(new PropertyValueFactory<>("proQuantity"));
        colStockSaleQuantity.setCellValueFactory(new PropertyValueFactory<>("saleQuantity"));
        colStockQuantityInHand.setCellValueFactory(new PropertyValueFactory<>("proQInHand")); 
        
        //Searching Stock
        FilteredList<StockList>filterData=new FilteredList<>(stockList,b->true);
        txtStockProduct.textProperty().addListener((observable,oldValue,newValue)->{
            filterData.setPredicate(predicate->{
                String lowerCase=newValue.toLowerCase();
                if(newValue==null || newValue.isEmpty())
                    return true;
            
            if(predicate.getProName().toLowerCase().indexOf(lowerCase)!=-1)
                return true;
            else if(predicate.getProID().indexOf(lowerCase)!=-1)
                return true;
             
            return false;
            });
        });
        SortedList<StockList>sortedData=new SortedList<>(filterData);
        sortedData.comparatorProperty().bind(tblViewStock.comparatorProperty());
        tblViewStock.setItems(sortedData);    
    }
    
 
    //Loading Data into Combo Box Company Name
    public void loadComboCompanyName(){
        try{
            String query="select * from Company";
            stmt=conn.prepareStatement(query);
            ResultSet rSet=stmt.executeQuery();
            while(rSet.next()){
                companyList.add(new CompanyList(rSet.getString(1),rSet.getString(2)));
            }
            comboCompanyName.setItems(companyList);
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
        comboCompanyName.setConverter(new StringConverter<CompanyList>(){
            @Override
            public String toString(CompanyList object) {
                    return object.getName();
            }

            @Override
            public CompanyList fromString(String string) {
                return null;
            } 
        });
        comboCompanyName.valueProperty().addListener((obs,oldValue,newValue)->{
            if(newValue!=null){
                companyID=Integer.parseInt(newValue.getId());
            }
        });
    }
    
    // inserting data into company
    @FXML
    public void saveCompany(){
        try{
            if(txtComName.getText().trim().isEmpty()){
                lblMessageCompany.setText("Company Name must be fill");
                lblMessageCompany.setVisible(true);
                lblStarCompany.setVisible(true);
                PauseTransition visibleShelf=new PauseTransition(Duration.seconds(1));
                visibleShelf.setOnFinished(event->lblMessageCompany.setVisible(false));
                visibleShelf.play();
            }
            else{
            String query="insert into Company (comName) values(?)";
            stmt=conn.prepareStatement(query);
            stmt.setString(1, txtComName.getText());
            stmt.execute();
            txtComName.setText("");
            companyList.clear();
            loadDataCompanyTbl();
            lblStarCompany.setVisible(false);
        }
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    //Loading Data into CompanyTblView
    public void loadDataCompanyTbl(){
        try{
            
            String query="select * from Company";
            stmt=conn.prepareStatement(query);
            ResultSet rSet=stmt.executeQuery();
            while(rSet.next()){
                companyListTblView.add(new CompanyList(rSet.getString(1),rSet.getString(2)));
            }
            //companyList.clear();
            tblViewCompany.setItems(companyListTblView); 
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    //Setting Columns for Company Table
    public void setTblViewCompanyColumns(){
        colCompID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCompName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        
        FilteredList<CompanyList>filterData=new FilteredList<>(companyListTblView,b->true);
        txtSearchCompany.textProperty().addListener((observable,oldValue,newValue)->{
        filterData.setPredicate(predicate->{
            
            String lowerCase=newValue.toLowerCase();
            if(newValue==null || newValue.isEmpty())
                return true;
            if(predicate.getName().toLowerCase().indexOf(lowerCase)!=-1)
                return true;
            else if(predicate.getId().indexOf(lowerCase)!=-1)
                return true;
           
            return false;
            
        });
        });
        SortedList<CompanyList>sortedData=new SortedList<>(filterData);
        sortedData.comparatorProperty().bind(tblViewCompany.comparatorProperty());
        tblViewCompany.setItems(sortedData);
        
    }
    // gettin data from tbl view company while clicked
    public void tblViewCompanyClicked(){
        tblViewCompany.setOnMouseClicked(event->{
            comList=tblViewCompany.getItems().get(tblViewCompany.getSelectionModel().getSelectedIndex());
            companyID=Integer.parseInt(comList.getId());
            txtComName.setText(comList.getName());
            btnSaveCompany.setVisible(false);
            btnUpdateCompany.setVisible(true); 
            btnCancelCompanyView.setVisible(true);
            btnCancelCompanyView.setOnAction(eventCancel->{
                btnUpdateCompany.setVisible(false);
                btnSaveCompany.setVisible(true);
                txtComName.setText("");
                btnCancelCompanyView.setVisible(false);
            });
        });
    }
    //Deleting from Category
    @FXML
    public void deleteCompany(){
      try{
          String query="delete from Company where comID=?";
          Alert alert=new Alert(AlertType.CONFIRMATION);
          alert.setTitle("Message from System");
          alert.setContentText("Aer you sure to delete the Record");
          Optional<ButtonType>btnAction=alert.showAndWait();
          if(btnAction.get()==ButtonType.OK){
              companyID=Integer.parseInt(comList.getId());
              stmt=conn.prepareStatement(query);
              stmt.setInt(1, companyID);
              stmt.execute();
              companyListTblView.clear();
             loadDataCompanyTbl();
          }
                  
      }
      catch(SQLException ex){
          JOptionPane.showMessageDialog(null, ex);
      }
    }
    // Deleting all data from company
    @FXML
    public void deleteAllCompany(){
        try{
            String query="Delete * from company";
            stmt=conn.prepareStatement(query);
            Alert alert=new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Message from System");
            alert.setContentText("Are you sure to Delete all the data from the Database");
            Optional<ButtonType>btnAction=alert.showAndWait();
            if(btnAction.get()==ButtonType.OK){
                stmt.execute();
                companyListTblView.clear();
                loadDataCompanyTbl();
            }
            else{
                stmt.close();
            }
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    // Updateing Company
    @FXML
    public void updateCompany(){
        try{
            String query="update Company set comName=? where comID=?";
            stmt=conn.prepareStatement(query);
            stmt.setString(1, txtComName.getText());
            companyID=Integer.parseInt(comList.getId());
            stmt.setInt(2, companyID);
            stmt.executeUpdate();
            Alert alert=new Alert(AlertType.INFORMATION);
            alert.setTitle("Message from System");
            alert.setContentText("Record Updated Successfully");
            alert.show();
            companyListTblView.clear();
            loadDataCompanyTbl();
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    //loading Date into Employee For Sale
    public void loadEmployeeSale(){
        try{
            String query="select * from Employee";
            stmt=conn.prepareStatement(query);
            ResultSet rSet=stmt.executeQuery();
            while(rSet.next()){
                employeeList.add(new EmployeeList(rSet.getString(1),rSet.getString(2),rSet.getString(3),
                rSet.getString(4),rSet.getString(5),rSet.getString(6),rSet.getString(7),rSet.getString(8),rSet.getString(9),
                rSet.getString(10),rSet.getString(11),rSet.getString(12),rSet.getString(13),rSet.getString(14),rSet.getBlob(15)));   
            }
            comboEmployeeSale.setItems(employeeList);
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }
        comboEmployeeSale.setConverter(new StringConverter<EmployeeList>(){
            @Override
            public String toString(EmployeeList object) {
                empName=object.getName();
                return object.getName();
            }

            @Override
            public EmployeeList fromString(String string) {
               return null;
            } 
        });
        comboEmployeeSale.valueProperty().addListener((obs,oldValue,newValue)->{
            employeeID=Integer.parseInt(newValue.getId());
            
        });
    }
    // Loading Customer Date into Sale Form
    public void loadComboCustomer(){
        try{
            String query="select * from Customer";
            stmt=conn.prepareStatement(query);
            ResultSet rSet=stmt.executeQuery();
            while(rSet.next()){
                customerList.add(new CustomerList(rSet.getString(1),rSet.getString(2),rSet.getString(3),rSet.getString(4))); 
            }
            comboCustomerSale.setItems(customerList);
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
        comboCustomerSale.setConverter(new StringConverter<CustomerList>(){
            @Override
            public String toString(CustomerList object) {
                cusName=object.getCustomerName();
                return object.getCustomerName();
            }

            @Override
            public CustomerList fromString(String string) {
               return null;
            }
        });
        comboCustomerSale.valueProperty().addListener((obs,oldValue,newValue)->{
            customerID=Integer.parseInt(newValue.getCustomerID());
            
        });
    }
   
    //Maping the data for two different combo boxes
    int selectedPurchaseID;
    public void loadMedicineSaleAndPrice() {
    try {
        String query = "SELECT p.PurchaseID, m.MedicineID, m.Name, p.PurchasePrice, c.CatID, c.CatName " +
                       "FROM purchase p " +
                       "INNER JOIN medicine m ON p.MedicineID = m.MedicineID " +
                       "INNER JOIN category c ON p.CategoryID = c.CatID";
        stmt = conn.prepareStatement(query);
        ResultSet rSet = stmt.executeQuery();
        while (rSet.next()) {
            String medicine = rSet.getString("Name");
            int purchasePrice = rSet.getInt("PurchasePrice");
            String cateName = rSet.getString("CatName");
            int categoryID = rSet.getInt("CatID");
            int medID = rSet.getInt("MedicineID");
            selectedPurchaseID = rSet.getInt("PurchaseID");
            medicinePriceMap.put(medicine, purchasePrice);
            medicineCategoryMap.put(medicine, cateName);
            categoryIDMap.put(medicine, categoryID);
            medicineIDMap.put(medicine, medID);
        }
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, ex);
    }

    comboMedicineSale.setItems(FXCollections.observableArrayList(medicinePriceMap.keySet()));
    comboMedicineSale.setOnAction(event -> {
        String selectedMedicine = comboMedicineSale.getValue();
        if (selectedMedicine != null && medicinePriceMap.containsKey(selectedMedicine)) {
            // Clear the previous items
            comboPurchasePriceSale.getItems().clear();
            comboCategorySale.getItems().clear();

            // Populate with new values
            comboPurchasePriceSale.setItems(FXCollections.observableArrayList(String.valueOf(medicinePriceMap.get(selectedMedicine))));
            comboPurchasePriceSale.getSelectionModel().selectFirst();

            String category = medicineCategoryMap.get(selectedMedicine);
            comboCategorySale.setItems(FXCollections.observableArrayList(category));
            comboCategorySale.getSelectionModel().selectFirst();

            // Update related IDs
            categoryID = categoryIDMap.get(selectedMedicine);
            medicineID = medicineIDMap.get(selectedMedicine);
        }
    });
}
    //this method works properly 
//    public void loadMedicineSaleAndPrice(){
//        try{
//              String query = "SELECT p.PurchaseID, m.MedicineID, m.Name, p.PurchasePrice, c.CatID, c.CatName " +
//               "FROM purchase p " +
//               "INNER JOIN medicine m ON p.MedicineID = m.MedicineID " +
//               "INNER JOIN category c ON p.CategoryID = c.CatID";
//            stmt=conn.prepareStatement(query);
//            ResultSet rSet=stmt.executeQuery();
//            while(rSet.next()){
//                String medicine=rSet.getString("Name");
//                int purchasePrice=rSet.getInt("PurchasePrice");
//                String cateName=rSet.getString("CatName");
//                int categoryID=rSet.getInt("CatID");
//                int medID=rSet.getInt("MedicineID");
//                selectedPurchaseID=rSet.getInt("PurchaseID");
//                medicinePriceMap.put(medicine, purchasePrice);
//                medicineCategoryMap.put(medicine, cateName);
//                categoryIDMap.put(medicine, categoryID);
//                medicineIDMap.put(medicine, medID);
//            }
//            
//        }
//        catch(Exception ex){
//            JOptionPane.showMessageDialog(null, ex);
//        }
//        comboMedicineSale.setItems(FXCollections.observableArrayList(medicinePriceMap.keySet()));
//        comboMedicineSale.setOnAction(event->{
//            String selectedMedicine=comboMedicineSale.getValue();
//            if(selectedMedicine!=null && medicinePriceMap.containsKey(selectedMedicine)){
//                comboPurchasePriceSale.setItems(FXCollections.observableArrayList(String.valueOf(medicinePriceMap.get(selectedMedicine))));
//            }
//            comboPurchasePriceSale.getSelectionModel().selectFirst();
//            String category=medicineCategoryMap.get(selectedMedicine);
//            comboCategorySale.setItems(FXCollections.observableArrayList(category));
//            comboCategorySale.getSelectionModel().selectFirst();
//            categoryID = categoryIDMap.get(selectedMedicine);
//            medicineID=medicineIDMap.get(selectedMedicine);
//        });
//        
//        
//    }

    // Adding Items to the Cart
    @FXML
    public void addItemCart(){
      String quantity=txtQuantitySale.getText();
      String selectedMedicine=comboMedicineSale.getValue();
      boolean medicineFount=false;
      int requestQuantity=Integer.parseInt(quantity);
      int availableStock=0;
      
      for(StockList st:stockList){
          if(Integer.parseInt(st.getProID())==medicineID){
              availableStock=Integer.parseInt(st.getProQInHand());
              medicineFount=true;
              break;
          }
      }
      if(!medicineFount){
          lblMessageSale.setText("Selected Medicne not found!");
          lblMessageSale.setVisible(true);
          lblStarSale.setVisible(true);
          PauseTransition ps=new PauseTransition(Duration.seconds(1));
          ps.setOnFinished(event->lblMessageSale.setVisible(false));
          ps.play();
          return;
      }
      if(requestQuantity > availableStock){
          lblMessageSale.setText("Insufficient Stock. Available Stock: "+availableStock);
          lblMessageSale.setVisible(true);
          lblStarSale.setVisible(true);
          PauseTransition ps=new PauseTransition(Duration.seconds(1));
          ps.setOnFinished(event->lblMessageSale.setVisible(false));
          ps.play();
      }
      else{
      String ename=empName;
      String cName=cusName;
      String mName=comboMedicineSale.getValue();
      String caName=comboCategorySale.getValue();
      String date=saleDate.getValue().toString();
      
      String price=txtSalePrice.getText();
      String tPrice=saleTotalPrice.getText();
      cartList.add(new AddingToCart(ename,cName,mName,caName,selectedPurchaseID,date,quantity,price,tPrice));
      cartList2.add(new AddingToCart(employeeID+"",customerID+"",medicineID+"",categoryID+"",selectedPurchaseID,date,quantity,price,tPrice));
      int grandTotal=0;
      for(AddingToCart c:cartList){
          grandTotal+=Integer.parseInt(c.getTotalPrice());
      }
      lbl_TotalSalePrice.setText(grandTotal+"");
      setCartColumns();
      tblViewSale.setItems(cartList);
      btnSaleItems.setDisable(false);
      lblStarSale.setVisible(false);
      }
    }
   
    
   //this method is workding but has some problem
    
//    @FXML
//    public void addItemCart(){
//      String quantity=txtQuantitySale.getText();
//      String medicineName=medicineCategoryMap.get("medicine");
//      
//      int requestQuantity=Integer.parseInt(quantity);
//      int availableStock=0;
//      
//      for(StockList st:stockList){
//          
//          availableStock=Integer.parseInt(st.getProQInHand());
//          break;
//      }
//      if(requestQuantity > availableStock){
//          lblMessageSale.setText("Insufficient Stock. Available Stock: "+availableStock);
//          lblMessageSale.setVisible(true);
//          lblStarSale.setVisible(true);
//          PauseTransition ps=new PauseTransition(Duration.seconds(1));
//          ps.setOnFinished(event->lblMessageSale.setVisible(false));
//          ps.play();
//      }
//      else{
//      String ename=empName;
//      String cName=cusName;
//      String mName=comboMedicineSale.getValue();
//      String caName=comboCategorySale.getValue();
//      String date=saleDate.getValue().toString();
//      
//      String price=txtSalePrice.getText();
//      String tPrice=saleTotalPrice.getText();
//      cartList.add(new AddingToCart(ename,cName,mName,caName,selectedPurchaseID,date,quantity,price,tPrice));
//      cartList2.add(new AddingToCart(employeeID+"",customerID+"",medicineID+"",categoryID+"",selectedPurchaseID,date,quantity,price,tPrice));
//      int grandTotal=0;
//      for(AddingToCart c:cartList){
//          grandTotal+=Integer.parseInt(c.getTotalPrice());
//      }
//      lbl_TotalSalePrice.setText(grandTotal+"");
//      setCartColumns();
//      tblViewSale.setItems(cartList);
//      btnSaleItems.setDisable(false);
//      lblStarSale.setVisible(false);
//      }
//    }
//  
    //Setting values to Columns Cart
    public void setCartColumns(){
       columnSaleEmpName.setCellValueFactory(new PropertyValueFactory<>("empName"));
       columnSaleCusName.setCellValueFactory(new PropertyValueFactory<>("cusName"));
       columnSaleMedicineName.setCellValueFactory(new PropertyValueFactory<>("medName"));
       columnSaleCatName.setCellValueFactory(new PropertyValueFactory<>("catName"));
       columnSaleDate.setCellValueFactory(new PropertyValueFactory<>("date"));        
       columnSaleQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));        
       columnSalePrice.setCellValueFactory(new PropertyValueFactory<>("salePrice"));
       columnSaleTotal.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));        
    }
    //Removing Item From Cart
    @FXML
    public void removeCartItem(){
        AddingToCart selectedRecord=tblViewSale.getSelectionModel().getSelectedItem();
        if(selectedRecord!=null){
            tblViewSale.getItems().remove(selectedRecord);
            tblViewSale.getSelectionModel().clearSelection();
         
        }
        if(tblViewSale.getItems().isEmpty()){
           btnSaleItems.setDisable(true);
           
            
        }
    }
    // Counting TotalPrice of Sale when adding to CART
    @FXML
    public void sumSaleTotalPrice(){
      int quantity=Integer.parseInt(txtQuantitySale.getText());
      int price=Integer.parseInt(txtSalePrice.getText());
      int totalPrice=quantity*price;
      saleTotalPrice.setText(totalPrice+"");
    }
    //Inserting data from tblViewSale into Sale

    @FXML
    public void insertSale(){
        String query="insert into Sale(CusID,EmpID,Date,CatID,MedicineID,PurchaseID,SaleQuantity,SalePrice,TotalPrice)values(?,?,?,?,?,?,?,?,?)";
       try{
           stmt=conn.prepareStatement(query);
           for(int i=0;i<cartList2.size();i++){
               
         //Debugging the code
         System.out.println("Debugging Record " + (i + 1) + ":");
    System.out.println("EmpID: " + cartList2.get(i).getEmpName());
    System.out.println("CusID: " + cartList2.get(i).getCusName());
    System.out.println("Date: " + cartList2.get(i).getDate());
    System.out.println("CatName: " + cartList2.get(i).getCatName());
    System.out.println("MedName: " + cartList2.get(i).getMedName());
    System.out.println("PurchaseID: " + cartList2.get(i).getPurchaseID());
    System.out.println("Quantity: " + cartList2.get(i).getQuantity());
    System.out.println("SalePrice: " + cartList2.get(i).getSalePrice());
    System.out.println("TotalPrice: " + cartList2.get(i).getTotalPrice());
    //
        stmt.setString(1,cartList2.get(i).getCusName());
        stmt.setString(2, cartList2.get(i).getEmpName());
        stmt.setString(3, cartList2.get(i).getDate());
        stmt.setString(4, cartList2.get(i).getCatName());
        stmt.setString(5, cartList2.get(i).getMedName());
        stmt.setInt(6, cartList2.get(i).getPurchaseID());
        stmt.setString(7, cartList2.get(i).getQuantity());
        
        stmt.setString(8, cartList2.get(i).getSalePrice());
        stmt.setString(9, cartList2.get(i).getTotalPrice());
        stmt.addBatch();
       }
           stmt.executeBatch();
           tblViewSale.getItems().clear();
           btnSaleItems.setDisable(true);
           stockList.clear();
           loadIntoStock();
           loadSaleDataTblView();
          
    }
       catch(Exception ex){
           JOptionPane.showMessageDialog(null, ex);
       }
    }
    
    //load Sale data 
    public void loadSaleDataTblView(){
        String query="select * from sale order by SaleID DESC LIMIT 10";
        
        try{
            stmt=conn.prepareStatement(query);
            ResultSet rSet=stmt.executeQuery();
            while(rSet.next()){
               saleList.add(new SaleList(rSet.getString(1),rSet.getString(2),rSet.getString(3),
               rSet.getString(4),rSet.getString(5),rSet.getString(6),rSet.getString(7),rSet.getString(8),rSet.getString(9)));
           }
            tblViewSoldItems.setItems(saleList);
            setSaleColumns();
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    // Loading Sale Data for printing report
    public void loadSaleData(){
       try{
           String query="Select SaleID, Employee.EmpName,Customer.CusName,Medicine.Name,category.CatName,date,SaleQuantity,SalePrice,TotalPrice from Sale\n" +
"Inner Join Customer ON Sale.CusID=Customer.CusID\n" +
"Inner Join employee ON Sale.EmpID=employee.EmpID\n" +
"Inner JOIN category ON Sale.CatID=category.CatID\n" +
"Inner JOIN medicine ON Sale.MedicineID=medicine.MedicineID";
           stmt=conn.prepareStatement(query);
           ResultSet rSet=stmt.executeQuery();
           while(rSet.next()){
               saleList.add(new SaleList(rSet.getString(1),rSet.getString(2),rSet.getString(3),
               rSet.getString(4),rSet.getString(5),rSet.getString(6),rSet.getString(7),rSet.getString(8),rSet.getString(9)));
           }
           tblViewSaleReport.setItems(saleList);
           setSaleReportTblColumns();
       }
       catch(SQLException ex){
           JOptionPane.showMessageDialog(null, ex);
       }
    }
    // setting SALE Report Columns
    public void setSaleReportTblColumns(){
    saleIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
    saleEmployeeCol.setCellValueFactory(new PropertyValueFactory<>("empName"));
    saleCustomerCol.setCellValueFactory(new PropertyValueFactory<>("cusName"));
    saleCategoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));
    saleMedicineCol.setCellValueFactory(new PropertyValueFactory<>("medicineName"));
    saleDateCol.setCellValueFactory(new PropertyValueFactory<>("saleDate"));
    saleQuantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
    salePriceCol.setCellValueFactory(new PropertyValueFactory<>("salePrice"));
    saleTotalPriceCol.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));  
    
    // Searching in Sale Report table
   FilteredList<SaleList>filterData=new FilteredList<>(saleList,b->true);
      txtSearchTblViewSale.textProperty().addListener((observable,oldValue,newValue)->{
         filterData.setPredicate(predicate->{
             String lowerCase=newValue.toLowerCase();
             if(newValue==null || newValue.isEmpty())
                 return true;
             else if(predicate.getId().indexOf(lowerCase)!=-1)
                 return true;
             else if(predicate.getCusName().toLowerCase().indexOf(lowerCase)!=-1)
                 return true;
             else if(predicate.getMedicineName().toLowerCase().indexOf(lowerCase)!=-1)
                 return true;
             else if(predicate.getCategory().toLowerCase().indexOf(lowerCase)!=-1)
                 return true;
              else if(predicate.getSaleDate().toLowerCase().indexOf(lowerCase)!=-1)
                 return true;
             return false;
         });
         SortedList<SaleList>sortedData=new SortedList<>(filterData);
         sortedData.comparatorProperty().bind(tblViewSaleReport.comparatorProperty());
         tblViewSaleReport.setItems(sortedData);
      });
    }
    // setting Columns data to Sale Table
    
    public void setSaleColumns(){
        columnSaleID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colSaleEmpName.setCellValueFactory(new PropertyValueFactory<>("empName"));
        colSaleCusName.setCellValueFactory(new PropertyValueFactory<>("cusName"));
        colSaleMedicine.setCellValueFactory(new PropertyValueFactory<>("medName"));
        colSaleCategory.setCellValueFactory(new PropertyValueFactory<>("catName"));
        colSaleDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colSaleQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colSalePrice.setCellValueFactory(new PropertyValueFactory<>("salePrice"));
        colSaleTotalPrice.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));  
    }
    // Total Stock in the database
  public void calcuatingStock(){
      try{
         String query="select (Select @pur:=SUM(Purchase.Quantity) from Purchase) as TotalPurchase,\n" +
"(Select @sal:=SUM(Sale.SaleQuantity) from Sale) as TotalSale,\n" +
"@Total:=@pur-@sal as QuantityInHand";
         stmt=conn.prepareStatement(query);
         ResultSet rSet=stmt.executeQuery();
         while(rSet.next()){
             txtTotalStockInHand.setText(rSet.getString(3));
         }

      }
      catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);
      }
  } 
  // Calcuating Total Sales
  public void calculatingSales(){
      try{
          String query="Select SUM(saleQuantity) from Sale";
          stmt=conn.prepareStatement(query);
          ResultSet rSet=stmt.executeQuery();
          while(rSet.next()){
              txtTotalSales.setText(rSet.getString(1));
          }
      }
      catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);
      }  
  }
  // Top Sales
  public void topSale(){
      try{
          String query="Select Medicine.MedicineID,Name, SUM(Sale.SaleQuantity) as MaximumSale from Medicine\n" +
"Inner Join Sale ON Sale.MedicineID=Medicine.MedicineID\n" +
"GROUP by Medicine.MedicineID";
          stmt=conn.prepareStatement(query);
          ResultSet rSet=stmt.executeQuery();
          while(rSet.next()){
              topSale.add(new TopSale(rSet.getString(1),rSet.getString(2),rSet.getString(3)));   
          }
          tableViewTopSale.setItems(topSale);
          setTopSaleColumns();
      }
      catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);
      }
      
  }
  // Setting Columns for Table TopSALE 
  public void setTopSaleColumns(){
      columnTopMedicineID.setCellValueFactory(new PropertyValueFactory<>("id"));
      columnTopMedicineName.setCellValueFactory(new PropertyValueFactory<>("name"));
      columnTopSale.setCellValueFactory(new PropertyValueFactory<>("topSale"));
  }
  
  // Save Customer
    @FXML
  public void saveCustomer(){
      try{
          if(txtCustomerName.getText().equals("")  || txtCustomerPhone.getText().equals("")){
                Alert alert=new Alert(AlertType.WARNING);
                alert.setContentText("Please make Sure that all fields contains Data");
                alert.showAndWait();
            }
          else{
          String query="insert into Customer(CusName,Email,Phone) values(?,?,?)";
          stmt=conn.prepareStatement(query);
          stmt.setString(1, txtCustomerName.getText());
          stmt.setString(2, txtCustomerEmail.getText());
          stmt.setString(3, txtCustomerPhone.getText());
          stmt.execute();
          
           PauseTransition pt=new PauseTransition(Duration.seconds(1));
           pt.setOnFinished(event->lblMessageCustomerInserted.setVisible(false));
           pt.play();
          txtCustomerName.setText("");
          txtCustomerEmail.setText("");
          txtCustomerPhone.setText("");
          totalCustomers();
          customerList2.clear();
          loadCustomer();
          }
          
      }
      catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);
      }
  }
  // Counting Total Customers
  public void totalCustomers(){
      try{
          String query="select Count(CusID) from Customer";
          stmt=conn.prepareStatement(query);  
          ResultSet rSet=stmt.executeQuery();
          while(rSet.next()){
              customerID=rSet.getInt(1);
              lblCountTotalCustomers.setText(customerID+"");
              lblTotalCustomerCount.setText(customerID+"");
          }
          stmt.close();
      }
      catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);
      }
  }
  //Load Customer Data
  public void loadCustomer(){
      try{
          String query="select * from Customer";
          stmt=conn.prepareStatement(query);
          ResultSet rSet=stmt.executeQuery();
          while(rSet.next()){
              customerList2.add(new CustomerList(rSet.getString(1),rSet.getString(2),rSet.getString(3),rSet.getString(4)));   
          }
          tblViewCustomer.setItems(customerList2);
      }
      catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);
      }
  }
  //set Columns to Customer View Table
  public void settblCustomerColumns(){
      colCustomerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
      colCustomerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
      colCustomerEmail.setCellValueFactory(new PropertyValueFactory<>("customerEmail"));
      colCustomerPhone.setCellValueFactory(new PropertyValueFactory<>("customerPhone"));  
      
      FilteredList<CustomerList>filterData=new FilteredList<>(customerList2,b->true);
      txtCustomerSearch.textProperty().addListener((observable,oldValue,newValue)->{
         filterData.setPredicate(predicate->{
             String lowerCase=newValue.toLowerCase();
             if(newValue==null || newValue.isEmpty())
                 return true;
             else if(predicate.getCustomerID().indexOf(lowerCase)!=-1)
                 return true;
             else if(predicate.getCustomerName().toLowerCase().indexOf(lowerCase)!=-1)
                 return true;
             else if(predicate.getCustomerEmail().toLowerCase().indexOf(lowerCase)!=-1)
                 return true;
             else if(predicate.getCustomerPhone().toLowerCase().indexOf(lowerCase)!=-1)
                 return true;
             return false;
         });
         SortedList<CustomerList>sortedData=new SortedList<>(filterData);
         sortedData.comparatorProperty().bind(tblViewCustomer.comparatorProperty());
         tblViewCustomer.setItems(sortedData);
          
      });
  }
  // Table Customer Clicked
  public void tblCustomerClicked(){
       tblViewCustomer.setOnMouseClicked(event->{
           cusList=tblViewCustomer.getItems().get(tblViewCustomer.getSelectionModel().getSelectedIndex());
       });
  }

  // Deleting Customer
    @FXML
  public void deleteCustomer(){
      String query="delete from Customer where cusID=?";
        try{
            Alert alert=new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Message from System");
            alert.setContentText("Aer You Sure to Delete the Record");
            Optional<ButtonType>btnAction=alert.showAndWait();
            if(btnAction.get()==ButtonType.OK){
            customerID=Integer.parseInt(cusList.getCustomerID());
            stmt=conn.prepareStatement(query);
            stmt.setInt(1, customerID);
            stmt.execute();
            customerList2.clear();
            totalCustomers();
            loadCustomer();
            }
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }
  }
  // Deleting all Data from Customer Table
    @FXML
  public void deleteAllCustomer(){
       String query="delete * from Customer";
        try{
            Alert alert=new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Message from System");
            alert.setContentText("Aer You Sure to Delete All data");
            Optional<ButtonType>btnAction=alert.showAndWait();
            if(btnAction.get()==ButtonType.OK){
            stmt=conn.prepareStatement(query);
            stmt.execute();
            customerList2.clear();
            totalCustomers();
            loadCustomer();
            }
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }
  }
  
  // Printing Sale Report
    @FXML

    //this method works good for print the whole table data
    
    public void printSaleReport() throws SQLException {
    try {
        // Load JasperDesign
        JasperDesign jd = JRXmlLoader.load("E:\\BCS\\Project Pharmacy Management System\\PMSystem\\src\\Reports\\SaleReport.jrxml");

        // SQL Query to Retrieve the Whole Table Data
        String query = "SELECT Customer.CusName, Medicine.Name, date, SaleQuantity, SalePrice, TotalPrice " +
                       "FROM Sale " +
                       "INNER JOIN Customer ON Sale.CusID = Customer.CusID " +
                       "INNER JOIN Medicine ON Sale.MedicineID = Medicine.MedicineID";

        // Set Query to JasperDesign
        JRDesignQuery jq = new JRDesignQuery();
        jq.setText(query);
        jd.setQuery(jq);

        // Compile Report
        JasperReport jR = JasperCompileManager.compileReport(jd);

        // Fill Report without Parameters
        JasperPrint jPrint = JasperFillManager.fillReport(jR, null, conn);

        // View Report
        JasperViewer.viewReport(jPrint, false);
    } catch (JRException ex) {
        JOptionPane.showMessageDialog(null, "Report Error: " + ex.getMessage());
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, "SQL Error: " + ex.getMessage());
    }
}

  // Closing Platform
    @FXML
  public void closeApp(){
      try{
      FXMLLoader loader=new FXMLLoader();
      loader.setLocation(getClass().getResource("/Login/LoginFormFXML.fxml"));
      loader.load();
                Parent root=loader.getRoot();
                Stage myStage=new Stage();
                myStage.setScene(new Scene(root));
                myStage.setMaximized(true);
                myStage.show();
                myStage=(Stage) lblLoggOff.getScene().getWindow();
                myStage.close();
      }
      catch(IOException ex){
          JOptionPane.showMessageDialog(null, ex);
      }
      
  }
  // Loading total USERS From database
  public void loadTotalUsers(){
             try{
            String query="select count(UserID) from user";
            stmt=conn.prepareStatement(query);
            ResultSet rSet=stmt.executeQuery();
            while(rSet.next()){
                int totalUsers=rSet.getInt(1);
                lblTotalUsersCount.setText(totalUsers+"");
            }
            stmt.close();
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
  }
// Displaying Sales Chart for Dashboard
  
  public void salesChart(java.util.Map<String,Integer>salesData) {
      XYChart.Series<String,Number> series= new XYChart.Series<>();
      series.setName("Monthly Sales");
      salesData.forEach((month,totalSales)->{
          series.getData().add(new XYChart.Data<>(month,totalSales));
      });
    
    salesLineChart.getData().add(series);
}

//  //Loading data from sales for chart
  
  private java.util.Map<String,Integer> fetchSalesData(){
      java.util.Map<String,Integer>salesDataMap=new java.util.HashMap<>();
      String query="select MONTHNAME(date) as Month,SUM(TotalPrice) as Totalprice from sale GROUP by Year(date) ,Month(date) order by Month(date)";
      try{
          stmt=conn.prepareStatement(query);
          ResultSet rSet=stmt.executeQuery();
          while(rSet.next()){
              String month=rSet.getString("month");
              int totalSales=rSet.getInt("totalPrice");
              salesDataMap.put(month, totalSales);
          }
      }
      catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);
      }
      return salesDataMap;
  }
    // Restricting Fiel
    // main Function
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        //addItemCart();
        comboJob.setItems(list);
        empPosition.setItems(employeePositions);
        empBloodGroup.setItems(bloodGroup);
        loadUserData();
        setUserColumns();
        loadUserLabels();
        loadTotalEmployee(); 
        loadCategoryList();
        setColumnsCategoryTable();
        tblCategoryClicked();
        loadShelfData();
        setColumnsShelfTable();
        tblShelfClicked();
        countShelfes();
        loadCatMedicine();
        loadShelfMedicine();
        setMedicineColumns();
        tblMedicineClicked();
        countTotalMedicine();
        loadSupplier();
        supplierTblClicked(); 
        loadPSupplierCombo();
        loadPMedicine();
        loadTblViewPurchase();
        setPurchaseColumns();
        tblPurchaseClicked() ;
        totalPurchases();
        loadPCategory();
        loadDataCompanyTbl();
        setTblViewCompanyColumns();
        loadComboCompanyName();
        laodMedicineData();
        loadIntoStock();
        setStockColumns();
        // Loading date into Combo Boxes in Sale Form
        // Employee,Customer,Medcine
        loadEmployeeSale();
        loadComboCustomer();
        loadSaleData();
        calcuatingStock();
        calculatingSales();
        topSale();
        totalCustomers();
        loadCustomer();
        settblCustomerColumns();
        tblCustomerClicked();
        tblViewCompanyClicked();
        // initializaing static variables
        userPanel=paneUser;
        employeePanel=paneEmployee;
        companyPanel=paneCompany;
        shelfPanel=paneShelf;
        medicinePanel=paneMedicine;
        sPanel=supplierPanel;
        purPanel=purchasePanel;
        catPanel=categoryPanel;
        lblLoggedU=lblLoggedUser;  
        loadTotalUsers();
       loadMedicineSaleAndPrice();
         java.util.Map<String,Integer>salesData=fetchSalesData();
         salesChart(salesData);
    }
}            