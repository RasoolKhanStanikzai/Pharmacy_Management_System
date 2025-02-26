
package Lists;

public class CustomerList {
    String customerID,customerName,customerEmail,customerPhone;

    public CustomerList(String customerID,String customerName,String customerEmail,String customerPhone){
        this.customerID=customerID;
        this.customerName=customerName;
        this.customerEmail=customerEmail;
        this.customerPhone=customerPhone;
    }
    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }
    
}
