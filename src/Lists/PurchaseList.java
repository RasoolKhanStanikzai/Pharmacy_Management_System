package Lists;

public class PurchaseList{
    String purchaseID,supplierID,purchaseDate,medicineID,quantity,purchasePrice,TotalPrice;
    String category,companyName;
    
    
  

    public PurchaseList(String purchaseID, String supplierID, String purchaseDate, String medicineID, String quantity, String purchasePrice, String TotalPrice,
            String category,String companyName) {
        this.purchaseID = purchaseID;
        this.supplierID = supplierID;
        this.purchaseDate = purchaseDate;
        this.medicineID = medicineID;
        this.quantity = quantity;
        this.purchasePrice = purchasePrice;
        this.TotalPrice = TotalPrice;
        this.category=category;
        this.companyName=companyName;
    }
    public String getPurchaseID() {
        return purchaseID;
    }

    public void setPurchaseID(String purchaseID) {
        this.purchaseID = purchaseID;
    }

    public String getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getMedicineID() {
        return medicineID;
    }

    public void setMedicineID(String medicineID) {
        this.medicineID = medicineID;
    }
    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(String purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public String getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(String TotalPrice) {
        this.TotalPrice = TotalPrice;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    
    
}