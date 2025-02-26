package Lists;

import java.sql.Blob;

public class UserList{
    String id;
    String name,password,jobStatus,email,phone,date;
    Blob photo;
    
    // construcotr for loading special data
    public UserList(String jobStatus)
    {
        this.jobStatus=jobStatus;
    }
    //Constructor for loading all user data
    
    public UserList(String id,String name,String password,
            String jobStatus,Blob photo,String email,String phone,
            String date)
    {
       
        this.id=id;
        this.name=name;
        this.password=password;
        this.jobStatus=jobStatus;
        this.photo=photo;
        this.email=email;
        this.phone=phone;
        this.date=date;
    }

    public UserList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    // Setters
    public void setID(String id){
        this.id= id;
    }
    public void setName(String name){
        this.name=name;
    }
    public void setPassword(String password){
        this.password=password;
    }
    public void setJobStatus(String jobStatus){
        this.jobStatus=jobStatus;
    }
    public void setPhoto(Blob photo){
        this.photo=photo;
    }
    public void setEmail(String email){
        this.email=email;
    }
    public void setPhone(String phone){
        this.phone=phone;
    }
    public void setDate(String date){
        this.date=date;
    }
    
    //getters
    public String getID(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getPassword(){
        return password;
    }
    public String getJobStatus(){
        return jobStatus;
    }
    public Blob getPhoto(){
        return photo;
    }
    public String getEmail(){
        return email;
    }
    public String getPhone(){
        return phone;
    }
    public String getDate(){
        return date;
    }
            
}