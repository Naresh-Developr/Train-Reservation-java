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

                        try(PreparedStatement preparedStatement = myConnection.prepareStatement(insertRecord)){
                            preparedStatement.setInt(1, pnr_number);
                            preparedStatement.setString(2, passengerName);
                            preparedStatement.setString(3, trainNumber);
                            preparedStatement.setString(4, classtype);
                            preparedStatement.setString(5, journy);
                            preparedStatement.setString(6,from);
                            preparedStatement.setString(7, to);

                            int row_affected = preparedStatement.executeUpdate();
                            if(row_affected>0){
                                System.out.println("Record added sucessfully");
                            } 
                            else{
                                System.out.println("No records were added");
                            }

                        }catch(SQLException e){
                            System.err.println("SQLException: "+ e.getMessage());

                        }
                    }
                    else if(choice == 2){
                        System.out.println("Enter the pnr number to delete record: ");
                        int pnrNumber = sc.nextInt();
                        try(PreparedStatement preparedStatement = myConnection.prepareStatement(deleteRecord)){

                            preparedStatement.setInt(1, pnrNumber);
                            int rowsAffected = preparedStatement.executeUpdate();

                            if(rowsAffected > 0){
                                System.out.println("Record deleted succesfully.");
                            }else{
                                System.out.println("No records were deleted");
                            }
            
                        }catch(SQLException e){
                            System.err.println("SQLException: "+ e.getMessage());
                        }

                    }
                    else if(choice == 3){

                        try(PreparedStatement preparedStatement = myConnection.prepareStatement(ShowQuery);
                                ResultSet resultSet = preparedStatement.executeQuery()){
                            System.out.println("\nAll records were printing: ");
                            while(resultSet.next()){
                                String pnrNumber = resultSet.getString("pnr_number");
                                String passengerName = resultSet.getString("passenger_name");
                                String trainNumber = resultSet.getString("train_number");
                                String classType = resultSet.getString("class_type");
                                String journeyDate = resultSet.getString("journey_date");
                                String fromLocation = resultSet.getString("from_location");
                                String toLocation = resultSet.getString("to_location");

                                System.out.println("PNR Number: " + pnrNumber);
                                System.out.println("Passenger Name: " + passengerName);
                                System.out.println("Train Number: " + trainNumber);
                                System.out.println("Class Type: " + classType);
                                System.out.println("Journey Date: " + journeyDate);
                                System.out.println("From Location: " + fromLocation);
                                System.out.println("To Location: " + toLocation);
                                System.out.println("--------------");

                            }
                        }catch(SQLException e){
                            System.err.println("SQLException: "+e.getMessage());

                        }

                    }
                    else if(choice ==4){
                        System.out.println("Exiting Program ");
                        break;
                    }
                    else{
                        System.out.println("Invalid Choice entered");
                    }
                }

            }catch(SQLException e){
                System.err.println("SQLException: " + e.getMessage());

            };

        }catch(ClassNotFoundException e){
            System.err.println("Error Loading JDBC: " + e.getMessage());

        };
            sc.close();
    }
    
}
