
package Lists;
public class StockList {
  
    String proID, proName, proQuantity,proQInHand,saleQuantity;
    public StockList(String proID,String proName,String proQuantity,String saleQuantity,String proQInHand){
        this.proID=proID;
        this.proName=proName;
        this.proQuantity=proQuantity;
        this.saleQuantity=saleQuantity;
        this.proQInHand=proQInHand; 
    }

    public String getProID() {
        return proID;
    }

    public void setProID(String proID) {
        this.proID = proID;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getProQuantity() {
        return proQuantity;
    }

    public void setProQuantity(String proQuantity) {
        this.proQuantity = proQuantity;
    }

    public String getProQInHand() {
        return proQInHand;
    }

    public void setProQInHand(String proQInHand) {
        this.proQInHand = proQInHand;
    }

    public String getSaleQuantity() {
        return saleQuantity;
    }

    public void setSaleQuantity(String saleQuantity) {
        this.saleQuantity = saleQuantity;
    }
   
    
}
