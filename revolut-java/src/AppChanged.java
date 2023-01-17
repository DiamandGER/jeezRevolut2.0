import helpers.Keyboard;
import java.util.concurrent.TimeUnit;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Ms. Camilleri's Technical Review:
 * 1. Fix the indentations and spacings
 * 2. Address TODOs
 */
public class AppChanged {
    // decarling global variables for the application
    /**
     * TODO: These seem like they should be constants rather than variables (Idk what that means...)
     */
    public static final String DIVIDER = "---------------------------------------------";  
    
    public static String transactionsFileName = "transactions.txt";

    public static String balanceFileName = "balance.txt";
    
    public static File transactionFile = new File(transactionsFileName); 

    public static File balanceFile = new File(balanceFileName);
    
    
    public static String purpose;
    
    public static int amountInEuros;
    
    public static int balance = readBalance();
    
    public static void main(String[] args) {
        // TODO: Move all variable declarations here at the top so that it will look less cluttered

        // setting up
        createTransactionFile();
        
        createBalanceFile();
        
        // onboarding
        displayDividedMessage("Hello! :)");
        
        // TODO: Your program should read like a story... (Well, I'm not that advanced yet)
        int exitApp = 0;
        
        while (exitApp < 1) {
            // displaying the menu
            menu(); 
            // displaying the next step after you made your choice ine the menu
            redirect(); 
            
            
            int whileloopGoBackToMenu = 0; 
            String yes_or_no;
            // giving the user a possibilty to resume an go back to menu
            while(whileloopGoBackToMenu < 1) { 
            
                System.out.println(DIVIDER);
                pause(3); 
                
                // Pause the operation for 3 seconds to give the customer time to read his selection and therfore the information displayed befor going on
                System.out.println("Do you want to go back to menu?");
                System.out.print("yes/no: ");
                yes_or_no = Keyboard.readString().toLowerCase();
                System.out.println(yes_or_no);
                
                // only accept certain answers
                if (yes_or_no.compareTo("yes") == 0){
                    exitApp = 0;
                    whileloopGoBackToMenu = 1;                   
                } else {
                    if (yes_or_no.compareTo("no") == 0 ) {
                        whileloopGoBackToMenu = 1;
                        exitApp = 1; 
                    } else {
                        System.out.println(DIVIDER);
                        System.out.println("Error: wrong input");
                        whileloopGoBackToMenu = 0;
                    }  
                }
            }
          }
        
        // offboard
        System.out.println("Okay, goodbye :(");
}
   // print the menu 
    public static void menu(){
        System.out.println("Select action:");
        displayDividedMessage("1. Account Info\n2. Manage your money\n3. Quit\n");
        // TODO: replacing DIVIDER and text with displayDividedMessage can help with readability
        System.out.println(DIVIDER);
        System.out.print("Type the particular number for our choice: ");
    }
      public static void redirect(){
        // display all redirections and read the specific number of choice
          int selection;
          int selection_manage_money;
          String name_of_recipient;
          selection = Keyboard.readInt();
          // TODO: I think a switch case for the selection ()
          // display the 2nd menu after choosing 'account info'
          if (selection == 1){
              System.out.println(DIVIDER);
              System.out.print("Your balance: ");
              printBalance();
              System.out.println("----");
              System.out.println("Your last transactions: ");
              printTransactions();
          }else{
            // display the 2nd menu after choosing 'manage your money'
              if (selection == 2){
                  System.out.println(DIVIDER);
                  System.out.println("Select action:");
                  System.out.println(DIVIDER);
                  System.out.println("1. Add money");
                  System.out.println("2. Send money");
                  System.out.println(DIVIDER);
                  System.out.print("Type the particular number for our choice: ");
                  selection_manage_money = Keyboard.readInt();
                  //display the 3rd menu after choosing 'add money'
                  if (selection_manage_money == 1) {
                      System.out.println(DIVIDER);
                      System.out.println("What is the purpose of your transaction?");
                      purpose = Keyboard.readString();
                      System.out.println(DIVIDER);
                      System.out.println("How much money money do you want to add?");
                      System.out.println(DIVIDER);
                      System.out.print("Please type in the amount in euros: ");
                      amountInEuros = Keyboard.readInt();
                      App.balance += amountInEuros;
                      if (amountInEuros == 1){
                      System.out.println("Nice, " + amountInEuros + " euro has been added to your balance");
                      }else{
                      System.out.println("Nice, " + amountInEuros + " euro have been added to your balance");    
                  }
                  // sync your actions with the balance and transactions
                      storeTransaction(purpose + " +" + amountInEuros);
                      storeBalance(balance);
                  } else {
                    // display the 3rd menu after choosing 'send money'
                      if (selection_manage_money == 2) {
                          System.out.println(DIVIDER);
                          System.out.println("Alright, who should be the recipient?");
                          name_of_recipient = Keyboard.readString();
                          System.out.println(DIVIDER);
                          System.out.println("How much money do you want to send?");
                          System.out.println(DIVIDER);
                          System.out.print("Please type in the amount in euros: ");
                          amountInEuros = Keyboard.readInt();
                          App.balance -= amountInEuros;
                          System.out.println(DIVIDER);
                          System.out.println("Okay, the money has been deducted from your balance and has been sent to " + name_of_recipient);
                          storeTransaction("Money transfer to " + name_of_recipient + " -" + amountInEuros);
                          storeBalance(balance);
                      }
                  }
              } else{
                // offboard after choosing 'quit'
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
    // create transaction file
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
        // store changes in transaction file
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
    // read transaction file and display it in 'account info'
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
    // create balance file
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
       // store changes in balance file
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
        // read transaction file and display it in 'account info'
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
        // read balance file and display it in 'account info'
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
    
    // TODO: Use this method to make your program code look cleaner
    public static void displayDividedMessage(String message) {
       System.out.println(DIVIDER);
       System.out.println(message);
    }
}