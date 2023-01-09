import helpers.Keyboard;
import java.util.concurrent.TimeUnit;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class App{
    public static String divider = "---------------------------------------------";  
    public static String transactionsFileName = "transactions.txt";
    public static File transactionFile = new File(transactionsFileName); 
    public static String purpose;
    public static int amount_in_euros;
    public static String balanceFileName = "balance.txt";
    public static File balanceFile = new File(balanceFileName);
    public static int balance = readBalance();
    
    public static void main(String[] args){
        createTransactionFile();
        createBalanceFile();
        System.out.println(divider);	
        System.out.println("Hello! :)");
        int whileloop_exit = 0;
        while (whileloop_exit < 1){
            menu();
            redirect();
            int whileloop_yes_no = 0;
            while(whileloop_yes_no < 1){
                String yes_or_no;
                System.out.println(divider);
                pause(3);
                System.out.println("do you want to go back to menu?");
                System.out.print("yes/no: ");
                yes_or_no = Keyboard.readString().toLowerCase();
                System.out.println(yes_or_no);
                if (yes_or_no.compareTo("yes") == 0){
                    whileloop_exit = 0;
                    whileloop_yes_no = 1;                   
                }else{
                   
                    if (yes_or_no.compareTo("no") == 0 ){
                            whileloop_yes_no = 1;
                            whileloop_exit = 1; 
                        }else{
                            System.out.println(divider);
                            System.out.println("Error: wrong input");
                            whileloop_yes_no = 0;
                    } 
                    
                }
               
            }
        
    }
    System.out.println("Okay, goodbye :(");
}
    
    public static void menu(){
        System.out.println("Select action:");
        System.out.println(divider);
        System.out.println("1. Account Info");
        System.out.println("2. Manage your money");
        System.out.println("3. Quit");
        System.out.println(divider);
        System.out.print("Type the particular number for our choice: ");
    }
        public static void redirect(){
            int selection;
            int selection_manage_money;
            String name_of_recipient;
            selection = Keyboard.readInt();
            //code all redirections
            if (selection == 1){
                System.out.println(divider);
                System.out.print("Your balance: ");
                printBalance();
                System.out.println("----");
                System.out.println("Your last transactions: ");
                printTransactions();
            }else{
                if (selection == 2){
                    System.out.println(divider);
                    System.out.println("Select action:");
                    System.out.println(divider);
                    System.out.println("1. Add money");
                    System.out.println("2. Send money");
                    System.out.println(divider);
                    System.out.print("Type the particular number for our choice: ");
                    selection_manage_money = Keyboard.readInt();
                    if (selection_manage_money == 1) {
                        System.out.println(divider);
                        System.out.println("What is the purpose of your transaction?");
                        purpose = Keyboard.readString();
                        System.out.println(divider);
                        System.out.println("How much money money do you want to add?");
                        System.out.println(divider);
                        System.out.print("Please type in the amount in euros: ");
                        amount_in_euros = Keyboard.readInt();
                        App.balance += amount_in_euros;
                        if (amount_in_euros == 1){
                        System.out.println("Nice, " + amount_in_euros + " euro has been added to your balance");
                        }else{
                        System.out.println("Nice, " + amount_in_euros + " euro have been added to your balance");    
                    }
                        storeTransaction(purpose + " +" + amount_in_euros);
                        storeBalance(balance);
                    } else {
                        if (selection_manage_money == 2) {
                            System.out.println(divider);
                            System.out.println("Alright, who should be the recipient?");
                            name_of_recipient = Keyboard.readString();
                            System.out.println(divider);
                            System.out.println("How much money do you want to send?");
                            System.out.println(divider);
                            System.out.print("Please type in the amount in euros: ");
                            amount_in_euros = Keyboard.readInt();
                            App.balance -= amount_in_euros;
                            System.out.println(divider);
                            System.out.println("Okay, the money has been deducted from your balance and has been sent to " + name_of_recipient);
                            storeTransaction("Money transfer to " + name_of_recipient + " -" + amount_in_euros);
                            storeBalance(balance);
                        }
                        
                    
                    }
                } else{
                    if (selection == 3){
                        System.out.println("Okay, goodbye :(");
                        System.exit(0);
                    }
                }
            }    
    }
    private static void pause(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
        }
    }
    
        public static void createTransactionFile() {
          try {
            if (App.transactionFile.createNewFile()) {
              System.out.println("File created: " + App.transactionFile.getName());
            } else {
              System.out.println(transactionsFileName + " is been used.");
            }
          } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
        }
    public static void storeTransaction(String transaction){
        try {
            FileWriter myWriter = new FileWriter(transactionsFileName, true);
            myWriter.write(transaction);
            myWriter.write("\n");
            myWriter.close();
          } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
    }
    public static void printTransactions(){ 
        try {
            boolean atleastonetransaction = false;
            Scanner myReader = new Scanner(transactionFile);
            while (myReader.hasNextLine()) {
              String data = myReader.nextLine();
              atleastonetransaction = true;
              System.out.println(data);
            }       
            if(atleastonetransaction == false) {

              System.out.println("none");
            }
            myReader.close();
          } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();          

          }

          

    }
    public static void createBalanceFile() {
        try {
          if (App.balanceFile.createNewFile()) {
            System.out.println("File created: " + App.balanceFile.getName());
          } else {
            System.out.println(balanceFileName + " is been used.");
          }
        } catch (IOException e) {
          System.out.println("An error occurred.");
          e.printStackTrace();
        }
      }
      public static void storeBalance(int balance){
        try {
            FileWriter myWriter = new FileWriter(balanceFileName, false);
            String balanceString = Integer.toString(balance);  
            myWriter.write(balanceString);
            myWriter.write("\n");
            myWriter.close();
          } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
        }

        public static void printBalance(){ 
            try {
                Scanner myReader = new Scanner(balanceFile);
                while (myReader.hasNextLine()) {
                  String data = myReader.nextLine();
                  int balance = Integer.parseInt(data);
                  System.out.println(balance);
                }       
                myReader.close();
              } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();          
    
              
            }
        }
        public static int readBalance(){
            int balance = 0;
            try {
                Scanner myReader = new Scanner(balanceFile);
                while (myReader.hasNextLine()) {
                  String data = myReader.nextLine();
                  balance = Integer.parseInt(data); 
                  
                }  
                   
                myReader.close();
              } catch (FileNotFoundException e) {
                e.printStackTrace();        
                }
            return(balance);  
        }
}