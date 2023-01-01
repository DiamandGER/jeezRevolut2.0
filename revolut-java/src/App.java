import helpers.Keyboard;


public class App{
    public static String divider = "---------------------------------------------";
    public static int balance = 0;
    public static String[] transactions = new String[5];
    public static void main(String[] args){
        System.out.println("Hello! :)");
        int whileloop_exit = 0;
        while (whileloop_exit < 1){
            menu();
            redirect();
            int whileloop_yes_no = 0;
            while(whileloop_yes_no < 1){
                String yes_or_no;
                System.out.println(divider);
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
            int amount_in_euros;
            String name_of_recipient;
            selection = Keyboard.readInt();
            //code all redirections
            if (selection == 1){
                System.out.println(divider);
                System.out.println("Your balance: " + App.balance);
                System.out.println("Your last transactions: ");
                boolean atleastonetransaction = false;
                for (String transaction : App.transactions) {
                    if (transaction != null) {
                        System.out.println(transaction);
                        atleastonetransaction = true;
                    }

                } 
                if(atleastonetransaction == false) {

                    System.out.println("none");
                }
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
                        System.out.println("How much money money do you want to add?");
                        System.out.println(divider);
                        System.out.print("Please type in the amount in euros: ");
                        amount_in_euros = Keyboard.readInt();
                        // add money affects transactions
                        App.balance += amount_in_euros;
                        System.out.println("Nice, " + amount_in_euros + " euro have/has been added to your balance");
                        // maybe decide if its mor or less then 9 and change the string each
                    } else {
                        if (selection_manage_money == 2) {
                            System.out.println(divider);
                            System.out.println("Alright, who should be the recipient?");
                            name_of_recipient = Keyboard.readString();
                            // save name
                            System.out.println(divider);
                            System.out.println("How much money do you want to send?");
                            System.out.println(divider);
                            System.out.print("Please type in the amount in euros: ");
                            amount_in_euros = Keyboard.readInt();
                            //save data and add to transactions
                            App.balance -= amount_in_euros;
                            System.out.println(divider);
                            System.out.println("Okay, the money has been deducted from your balance and has been sent to " + name_of_recipient);
                        }
                    }
                }
        }

    }
}
