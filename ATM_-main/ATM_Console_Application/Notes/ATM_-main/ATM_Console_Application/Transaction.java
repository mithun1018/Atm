package ATM_Console_Application;

public class Transaction {
    public String performedBy;
    public String type;
    public double amount;
    public Transaction(String performerName,String performType,double enterAmount){
        this.performedBy=performerName;
        this.type=performType;
        this.amount=enterAmount;
    }
    public  String getPerformedBy(){
        return performedBy;
    }
    public String getType(){
        return type;
    }
    public double getAmount(){
        return amount;
    }
    public  String getTransaction(){
        String return_value=getPerformedBy()+getType()+getAmount();
        return return_value;
    }
}
