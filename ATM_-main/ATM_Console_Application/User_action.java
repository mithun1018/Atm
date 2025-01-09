package ATM_Console_Application;
import ATM_Console_Application.Notes.Notes;

import java.util.Scanner;
import java.util.ArrayList;


public class User_action {
    public static Account userEntry() {
        Scanner scan = new Scanner(System.in);
        while (true) {
            if (!ATM.getAccountArray().isEmpty()) {
                System.out.print("Enter the User name: ");
                String userName = scan.next();
                System.out.print("Enter the Pin: ");
                String pinNo = scan.next();
                for (Account induvidualUser : ATM.getAccountArray()) {
                    if (induvidualUser instanceof User) {
                        if (induvidualUser.getUsername().equals(userName) && induvidualUser.getPassword().equals(pinNo)) {
                            return induvidualUser;
                        } else {
                            System.out.println("User not found...");
                            ATM.start();
                        }
                    }
                }
            }
            else {
                System.out.println("no user found...");
                ATM.start();
            }
        }
    }


    public static void changePin(Scanner scan,User currentUser)
    {
        System.out.print("Enter Pin to change :");
        String pin = scan.nextLine();
        currentUser.setPassword(pin);
        System.out.println("Pin changed");
    }

    public static void withdrawCash(Scanner scan,User currentUser )
    {
        System.out.print("Enter the withdraw amount : ");
        long Withdrawamount = Long.parseLong(scan.next());
        if(Withdrawamount<=ATM.getBalance()) {
            if (Withdrawamount <= currentUser.getAccBalance()) {
                System.out.println("the Withdrawl amount :" + Withdrawamount + " is successful");
                double currentBalanceInAcc = currentUser.getAccBalance() - Withdrawamount;
                double currentBalanceInATM = ATM.getBalance() - Withdrawamount;
                currentUser.setAccBalance(currentBalanceInAcc);
                currentUser.getTransactionHistory().add(new Transaction(currentUser.getUsername(),"Withdraw rs: ",Withdrawamount));
//                currentUser.addUserTransactionHistory("Your account is debited with Rs." + Withdrawamount + "Balance :" + currentUser.getBalance());
//                Admin.addATMTransactionHistory(currentUser.getUserName() + "'s account is debited with Rs." + Withdrawamount + "--- User Balance : " + currentUser.getBalance());
                System.out.println("balance is " + currentUser.getAccBalance());
            } else {
                System.out.println(" Insufficent balance");
            }
        }
        else {
            System.out.println("Insufficent balance in ATM \ncome back later");
        }

    }

    public static void depositCash(Scanner scan,User currentUser)
    {

        System.out.print("Enter the deposit amount : ");
        long Depositamount = Long.parseLong(scan.next());
        System.out.println("enter the no of notes to deposit....");
        System.out.println("enter the no.of 2000: ");
        int twoThousandNotes=Integer.parseInt(scan.next());

        System.out.println("enter the no.of 500: ");

        int fiveHundredNotes=Integer.parseInt(scan.next());
        System.out.println("enter the no.of 200: ");

        int twoHundredNotes=Integer.parseInt(scan.next());
        System.out.println("enter the no.of 100: ");

        int oneHundredNotes=Integer.parseInt(scan.next());
        long DepositamountInNotes=2000*twoThousandNotes+500*fiveHundredNotes+200*twoHundredNotes+100*oneHundredNotes;
        if(Depositamount==DepositamountInNotes){
            double currentBalance = currentUser.getAccBalance() + Depositamount;
//            double currentBalanceInATM= ATM.getBalance()+Depositamount;
            currentUser.setAccBalance(currentBalance);
            for (Notes availableNotes:ATM.getNoteArray()){
                switch(availableNotes.getNotes()){
                    case "2000":
                        availableNotes.setCount(availableNotes.getCount()+twoThousandNotes);
                    case "500":
                        availableNotes.setCount(availableNotes.getCount()+fiveHundredNotes);
                    case "200":
                        availableNotes.setCount(availableNotes.getCount()+twoThousandNotes);
                    case "100":
                        availableNotes.setCount(availableNotes.getCount()+oneHundredNotes);
                }

            }
            currentUser.getTransactionHistory().add(new Transaction(currentUser.getUsername(),"deposited rs: ",Depositamount));
//            currentUser.addUserTransactionHistory("Your account is credited with Rs." + Depositamount + "    Balance :" + currentUser.getBalance());
//            Admin.addATMTransactionHistory(currentUser.getUserName() + "'s account is credited with Rs." + Depositamount + "   User Balance : " + currentUser.getBalance() + "--- ATM Balance : " + ATM.getBalance());
            System.out.println("The deposit of Rs." + Depositamount + " is added successfully");
            System.out.println("current balance is " + currentUser.getAccBalance());
            ATM.userAction(scan,currentUser);
        }

    }

    public static void viewTransactions(User currentUser)
    {
        for (Transaction temp:currentUser.getTransactionHistory()){
            if(currentUser.getUsername().equals(temp.getPerformedBy())){
                System.out.println(temp.getTransaction());
            }else {
                return;
            }
        }
//        ArrayList<String> userHistory = currentUser.getUserTransactionHistory();
//        if (!userHistory.isEmpty()) {
//            System.out.println("The Transactions are\n");
//            for (String history : userHistory) {
//                System.out.println(history);
//            }
//        } else {
//            System.out.println("There are no Transactions..");
//        }
    }

}