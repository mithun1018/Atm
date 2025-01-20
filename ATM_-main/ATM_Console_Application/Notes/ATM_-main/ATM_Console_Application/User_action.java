package ATM_Console_Application;
import ATM_Console_Application.Notes.Notes;

import java.util.Scanner;
import java.util.ArrayList;


public class User_action {
    public static Account userEntry() throws CloneNotSupportedException {
        Scanner scan = new Scanner(System.in);
        while (true) {
            if (!ATM.getAccountArray().isEmpty()) {
                System.out.print("Enter the User name: ");
                String userName = scan.nextLine();
                System.out.print("Enter the Pin: ");
                String pinNo = scan.nextLine();
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
            } else {
                System.out.println("no user found...");
                ATM.start();
            }
        }
    }


    public static void changePin(Scanner scan, User currentUser) {
        System.out.print("Enter Pin to change :");
        String pin = scan.nextLine();
        currentUser.setPassword(pin);
        System.out.println("Pin changed");
    }

    public static void withdrawCash(Scanner scan,User currentUser) throws CloneNotSupportedException {
        System.out.println("enter the withdraw amount: ");
        long withdrawAmount=Integer.parseInt(scan.nextLine());
        ArrayList<Notes> duplicate=new ArrayList<>();
        ArrayList<String> suppliedNote=new ArrayList<>();
        if(withdrawAmount<=ATM.getBalance()){
            double amount=withdrawAmount;
            if(withdrawAmount<=currentUser.getAccBalance()){
                for (Notes note: ATM.getNoteArray()){
                    duplicate.add((Notes)note.clone());
                }
                if (withdrawAmount!=0){
                    for (Notes currentNote: duplicate){
                        String currentNoteName=currentNote.getNotes();
                        switch (currentNoteName){
                            case "2000","500","200","100":
                                withdrawAmount=User_action.performWithdraw(currentNote,suppliedNote,withdrawAmount);
                                break;
                        }
                    }
                    if (withdrawAmount==0){
                        ATM.setNotesInAtm(duplicate);
                        currentUser.setAccBalance(currentUser.getAccBalance()-amount);
                        ATM.setBalance(ATM.getBalance()-amount);
                        currentUser.getTransactionHistory().add(new Transaction(currentUser.getUsername(), "withdraw rs: ", amount));
                        for (String s:suppliedNote){
                            System.out.println(s);
                        }
                        System.out.println("balance amount is :"+currentUser.getAccBalance());
                    }
                    else {
                        System.out.println("no denomination enter another amount");

                    }
                }
            }
            else {
                System.out.println("insufficiant balance in Acount");
            }
        }
        else {
            System.out.println("insufficiant balance in ATM");
        }
    }
    public static long performWithdraw(Notes note,ArrayList<String> suppliedNote,long withdrawAmount){
        long noteCount=withdrawAmount/Integer.parseInt(note.getNotes());
        if (withdrawAmount>=Integer.parseInt(note.getNotes()) && note.getCount()>0){
            if (noteCount<=note.getCount()){
                long withDraw1=withdrawAmount-noteCount*Integer.parseInt(note.getNotes());
                note.setCount(note.getCount()-noteCount);
                suppliedNote.add("you got"+note.getNotes()+" "+noteCount);
                return withDraw1;
            }
            else {
                long withDraw1=withdrawAmount-Integer.parseInt(note.getNotes())*note.getCount();
                long a=0;
                note.setCount(a);
                return withDraw1;
            }
        }
        return withdrawAmount;

    }
            public static void depositCash (Scanner scan, User currentUser) throws CloneNotSupportedException {

            System.out.print("Enter the deposit amount : ");
            long Depositamount = Long.parseLong(scan.nextLine());
            System.out.println("enter the no of notes to deposit....");
            System.out.println("enter the no.of 2000: ");
            int twoThousandNotes = Integer.parseInt(scan.nextLine());

            System.out.println("enter the no.of 500: ");

            int fiveHundredNotes = Integer.parseInt(scan.nextLine());
            System.out.println("enter the no.of 200: ");

            int twoHundredNotes = Integer.parseInt(scan.nextLine());
            System.out.println("enter the no.of 100: ");

            int oneHundredNotes = Integer.parseInt(scan.nextLine());
            long DepositamountInNotes = 2000 * twoThousandNotes + 500 * fiveHundredNotes + 200 * twoHundredNotes + 100 * oneHundredNotes;
            if (Depositamount == DepositamountInNotes) {
                double currentBalance = currentUser.getAccBalance() + Depositamount;
            double currentBalanceInATM= ATM.getBalance()+Depositamount;
                currentUser.setAccBalance(currentBalance);
                for (Notes availableNotes : ATM.getNoteArray()) {
                    switch (availableNotes.getNotes()) {
                        case "2000":
                            availableNotes.setCount(availableNotes.getCount() + twoThousandNotes);
                        case "500":
                            availableNotes.setCount(availableNotes.getCount() + fiveHundredNotes);
                        case "200":
                            availableNotes.setCount(availableNotes.getCount() + twoThousandNotes);
                        case "100":
                            availableNotes.setCount(availableNotes.getCount() + oneHundredNotes);
                    }

                }
                currentUser.getTransactionHistory().add(new Transaction(currentUser.getUsername(), "deposited rs: ", Depositamount));
//            currentUser.addUserTransactionHistory("Your account is credited with Rs." + Depositamount + "    Balance :" + currentUser.getBalance());
//            Admin.addATMTransactionHistory(currentUser.getUserName() + "'s account is credited with Rs." + Depositamount + "   User Balance : " + currentUser.getBalance() + "--- ATM Balance : " + ATM.getBalance());
                System.out.println("The deposit of Rs." + Depositamount + " is added successfully");
                System.out.println("current balance is " + currentUser.getAccBalance());
                ATM.userAction(scan, currentUser);
            }
        }


            public static void viewTransactions (User currentUser)
            {
                for (Transaction temp : currentUser.getTransactionHistory()) {
                    if (currentUser.getUsername().equals(temp.getPerformedBy())) {
                        System.out.println(temp.getTransaction());
                    } else {
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