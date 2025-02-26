package Lists;

import java.sql.Blob;

public class EmployeeList{
    String id;
    String name;
    String lastname,position,bloodGroup,contactNO,altNO,email,Salary,
            contractdate,enddate,descriptions,currentadd,permanentadd;
    Blob image;

    public EmployeeList(String id, String name, String lastname, String position, String bloodGroup, String contactNO, String altNO, String email, String Salary, String contractdate, String enddate, String descriptions, String currentadd, String permanentadd, Blob image) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.position = position;
        this.bloodGroup = bloodGroup;
        this.contactNO = contactNO;
        this.altNO = altNO;
        this.email = email;
        this.Salary = Salary;
        this.contractdate = contractdate;
        this.enddate = enddate;
        this.descriptions = descriptions;
        this.currentadd = currentadd;
        this.permanentadd = permanentadd;
        this.image = image;
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

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getContactNO() {
        return contactNO;
    }

    public void setContactNO(String contactNO) {
        this.contactNO = contactNO;
    }

    public String getAltNO() {
        return altNO;
    }

    public void setAltNO(String altNO) {
        this.altNO = altNO;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSalary() {
        return Salary;
    }

    public void setSalary(String Salary) {
        this.Salary = Salary;
    }

    public String getContractdate() {
        return contractdate;
    }

    public void setContractdate(String contractdate) {
        this.contractdate = contractdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public String getCurrentadd() {
        return currentadd;
    }

    public void setCurrentadd(String currentadd) {
        this.currentadd = currentadd;
    }

    public String getPermanentadd() {
        return permanentadd;
    }

    public void setPermanentadd(String permanentadd) {
        this.permanentadd = permanentadd;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }

   
}