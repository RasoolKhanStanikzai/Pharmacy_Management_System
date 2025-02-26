
package Lists;

import java.util.Date;


public class AddingToCart {
     private String catName;
     String empName;
     String cusName,medName,date,quantity,salePrice,totalPrice;
     private int purchaseID;
     private int empID;
     private int catID;
     private int cusID;
     private int medID;
     private Date saleDate;
     private int saleQuantity;
     private int saleP;
     private int tPrice;
    public AddingToCart(String empName, String cusName, String medName, String catName,int purchaseID, String date, String quantity, String salePrice, String totalPrice) {
       this.purchaseID=purchaseID;
        this.empName = empName;
        this.cusName = cusName;
        this.medName = medName;
        this.catName = catName;
        this.date = date;
        this.quantity = quantity;
        this.salePrice = salePrice;
        this.totalPrice = totalPrice;
    }

    public AddingToCart( int empID, int catID, int cusID, int medID,int purchaseID, Date saleDate, int saleQuantity, int saleP, int tPrice) {
        
        this.empID = empID;
        this.catID = catID;
        this.cusID = cusID;
        this.medID = medID;
        this.purchaseID = purchaseID;
        this.saleDate = saleDate;
        this.saleQuantity = saleQuantity;
        this.saleP = saleP;
        this.tPrice = tPrice;
    }

    public int getPurchaseID() {
        return purchaseID;
    }

    public void setPurchaseID(int purchaseID) {
        this.purchaseID = purchaseID;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName=empName;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getMedName() {
        return medName;
    }

    public void setMedName(String medName) {
        this.medName = medName;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getEmpID() {
        return empID;
    }

    public void setEmpID(int empID) {
        this.empID = empID;
    }

    public int getCatID() {
        return catID;
    }

    public void setCatID(int catID) {
        this.catID = catID;
    }

    public int getCusID() {
        return cusID;
    }

    public void setCusID(int cusID) {
        this.cusID = cusID;
    }

    public int getMedID() {
        return medID;
    }

    public void setMedID(int medID) {
        this.medID = medID;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public int getSaleQuantity() {
        return saleQuantity;
    }

    public void setSaleQuantity(int saleQuantity) {
        this.saleQuantity = saleQuantity;
    }

    public int getSaleP() {
        return saleP;
    }

    public void setSaleP(int saleP) {
        this.saleP = saleP;
    }

    public int gettPrice() {
        return tPrice;
    }

    public void settPrice(int tPrice) {
        this.tPrice = tPrice;
    }
    
}