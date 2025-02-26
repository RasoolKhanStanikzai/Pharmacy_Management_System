
package Lists;

public class CategoryList {
    String id;
    String name;

    public CategoryList(String id,String name){
        this.id=id;
        this.name=name;
        
    }
    public void setID(String id){
        this.id=id;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getID(){
        return id;
    }
    public String getName(){
        return name;
    }
   
}
