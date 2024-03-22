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

        public int pnrNumber(){
            Random random = new Random();
            pnrNumber = random.nextInt(max) + min;
            return pnrNumber;
        }

        public String passengeName(){
            System.out.println("Enter passenger Name: ");
            passengerName = sc.nextLine();
            return passengerName;
        }
            
    }
    
}
