package Lists;

public class ShelfList{
    String id,name,numericNumber,date,descriptions;
    public ShelfList(String id,String name,String numericNumber,String date,String descriptions){
        this.id=id;
        this.name=name;
        this.numericNumber=numericNumber;
        this.date=date;
        this.descriptions=descriptions;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumericNumber() {
        return numericNumber;
    }

    public void setNumericNumber(String numericNumber) {
        this.numericNumber = numericNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }
    
}