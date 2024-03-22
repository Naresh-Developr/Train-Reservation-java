import java.sql.*;
import java.util.Scanner;
import java.util.Random;

public class trainReservation{

    private static final int min=999;
    private static final int max =9999;


    public static class user{

        private String userName;
        private String password;

        Scanner sc = new Scanner(System.in);

        public String getUsername()
        {
            System.out.println("Enter Username: ");
            userName = sc.nextLine();
            return userName;

        }
        public String getPassword()
        {
            System.out.println("Enter password: ");
            password = sc.nextLine();
            return password;

        }

    }
    public static class pnrRecords{
        private String passengerName;
        private int pnrNumber;
        private String trainNumber;
        private String classtype;
        private String journy;
        private String from;
        private String to;

        Scanner sc = new Scanner(System.in);

        public int getPnrNumber(){
            Random random = new Random();
            pnrNumber = random.nextInt(max) + min;
            return pnrNumber;
        }

        public String getPassengeName(){
            System.out.println("Enter passenger Name: ");
            passengerName = sc.nextLine();
            return passengerName;
        }
        public String getTrainNumber(){
            System.out.println("Enter Train Number: ");
            trainNumber = sc.nextLine();
            return trainNumber;
        }
        public String getClassType(){
            System.out.println("Enter class type: ");
            classtype = sc.nextLine();
            return classtype;
        }
        public String getJourny(){
            System.out.println("Enter journy: ");
            journy = sc.nextLine();
            return journy;
        } 
        public String getFrom(){
            System.out.println("Enter From: ");
            from = sc.nextLine();
            return from;
        }
        public String getTo(){
            System.out.println("Enter To : ");
            to = sc.nextLine();
            return to;
        }
            
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        user u1 = new user();
        String username = u1.getUsername();
        String password = u1.getPassword();

        String Url = "jdbc:mysql://localhost:3306/task1";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            try(Connection myConnection = DriverManager.getConnection(Url, username, password)){
                System.out.println("Connection Granted");
                while(true){
                    String insertRecord = "insert into reservation value(?,?,?,?,?,?,?)";
                    String deleteRecord = "Delete from reservation where pnr_number = ?";
                    String ShowQuery = "SELECT * FROM reservation";

                    System.out.println("Enter Choice: ");
                    System.out.println("1. Insert Record\n");
                    System.out.println("2. Delete Record\n");
                    System.out.println("3. Show all Records\n");
                    System.out.println("4. Exit\n");
                    int choice  = sc.nextInt();

                    if(choice == 1){
                        pnrRecords pnr = new pnrRecords();

                        int pnr_number = pnr.getPnrNumber();
                        String trainNumber = pnr.getTrainNumber();
                        String passengerName = pnr.getPassengeName();
                        String classtype = pnr.getClassType();
                        String journy = pnr.getJourny();
                        String from = pnr.getFrom();
                        String to = pnr.getTo();

                        try(PreparedStatement preparedStatement = Connection.prepareStatement(insertRecord)){

                        }catch(Exception e){

                        }
                    }


                    

                }

            }catch(Exception e){

            };

        }catch(Exception e){

        };

    }
    
}
