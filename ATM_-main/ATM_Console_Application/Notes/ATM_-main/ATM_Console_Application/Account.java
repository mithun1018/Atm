package ATM_Console_Application;

import java.util.ArrayList;

public class Account {
    private String Username;
    private String Password;
    private ArrayList<Transaction> transactionHistory=new ArrayList<>();

    protected Account(String username,String password){
        this.Username=username;
        this.Password=password;
    }
    public String getUsername() {
        return Username;
    }
    public String getPassword(){
        return Password;
    }
    public ArrayList<Transaction> getTransactionHistory(){
        return transactionHistory;
    }
    public void setPassword(String pin){
        this.Password=pin;
    }


}
