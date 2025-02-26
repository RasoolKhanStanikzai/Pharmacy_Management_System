
package Lists;
public class MedicineList {
    String id,catID,shelfID,name,batchNO,date;
    public MedicineList(String id,String catID,String shelfID,String name,
            String batchNO,String date){
        this.id=id;
        this.catID=catID;
        this.shelfID=shelfID;
        this.name=name;
        this.batchNO=batchNO;
        this.date=date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCatID() {
        return catID;
    }

    public void setCatID(String catID) {
        this.catID = catID;
    }

    public String getShelfID() {
        return shelfID;
    }

    public void setShelfID(String shelfID) {
        this.shelfID = shelfID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBatchNO() {
        return batchNO;
    }

    public void setBatchNO(String batchNO) {
        this.batchNO = batchNO;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    
}
