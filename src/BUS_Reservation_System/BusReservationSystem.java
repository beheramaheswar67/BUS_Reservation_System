package BUS_Reservation_System;

import java.sql.*;
import java.util.Scanner;

 class Function{
    public static void bookTicket(Connection connection, Scanner scanner){
        try{
            System.out.print("Enter Passenger Name: ");
            String passengerName = scanner.next();
            scanner.nextLine();
            System.out.print("Enter Seat Number: ");
            int seatNumber = scanner.nextInt();
            System.out.print("Enter Amount: ");
            int amount = scanner.nextInt();
            System.out.print("Enter Starting Point: ");
            String startingPoint = scanner.next();
            System.out.print("Enter Destination: ");
            String destination = scanner.next();
            System.out.print("Enter Contact Number: ");
            String contactNumber = scanner.next();

            String sql = "INSERT INTO booking (passenger_name, seat_number, amount, starting_point, destination_point, contact_number)" +
                    "VALUES ('"+passengerName+"','"+seatNumber+"','"+amount+"','"+startingPoint+"','"+destination+"','"+contactNumber+"')";
            try(Statement statement = connection.createStatement()){
                int RowAffected = statement.executeUpdate(sql);
                if(RowAffected>0){
                    System.out.println("Booking Successfully Done..!");
                }
                else {
                    System.out.println("Booking Failed Please Try Again..!");
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void viewAllbooking(Connection connection)throws Exception{
        String sql= "SELECT booking_id, passenger_name, seat_number, amount, starting_point, destination_point, contact_number, booking_date FROM booking ";
        try(Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql)
        ){
            System.out.println("Current Booking: ");
            System.out.println("+------------+-------------------------+-------------+-----------------+-----------------+----------------------+-----------------+-----------------------+");
            System.out.println("| Booking ID | Passenger Name          | Seat Number |  Amount         | Starting Point  | Destination Point    | Contact Number  |  Booking Date         |");
            System.out.println("+------------+-------------------------+-------------+-----------------+-----------------+----------------------+-----------------+-----------------------+");

            while (resultSet.next()){
                int bookingId = resultSet.getInt("booking_id");
                String passengerName = resultSet.getString("passenger_name");
                int seatNumber = resultSet.getInt("seat_number");
                int amount = resultSet.getInt("amount");
                String startingPoint = resultSet.getString("starting_point");
                String destination = resultSet.getString("destination_point");
                String contactNumber = resultSet.getString("contact_number");
                String bookingDate = resultSet.getTimestamp("booking_date").toString();

                System.out.printf("| %-10d | %-23s | %-11d | %-15s | %-14s  | %-20s | %-14s  | %-15s |\n",
                        bookingId, passengerName, seatNumber, amount, startingPoint, destination, contactNumber, bookingDate);

            }
            System.out.println("+------------+-------------------------+-------------+-----------------+-----------------+----------------------+-----------------+-----------------------+");


        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void findSeatnumber(Connection connection, Scanner scanner) throws SQLException {
        try {
            System.out.print("Enter Booking ID: ");
            int bookingID = scanner.nextInt();
            System.out.print("Enter Passenger Name: ");
            String passengerName = scanner.next();
            String sql = "SELECT seat_number FROM booking " + "WHERE booking_id = " + bookingID + " AND passenger_name = '" + passengerName + "'";

            try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)){
                if(resultSet.next()){
                    int seatNumber = resultSet.getInt("seat_number");
                    System.out.println("The seat number of: "+passengerName+"  is: "+seatNumber);
                }
                else {
                    System.out.println("Seat not found for the Booking ID.");
                }

            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    public static void updateBooking(Connection connection, Scanner scanner)throws SQLException{
        try{
            System.out.print("Enter Booking ID for Update: ");
            int bookingId = scanner.nextInt();

            if(!exitBookingId(connection, bookingId)){
                System.out.println("Booking ID not Available..!");
                return;
            }
            System.out.print("Enter new Passenger name: ");
            String newPassenger = scanner.next();
            scanner.nextLine();
            System.out.print("Enter new Seat number: ");
            int newSeatNumber = scanner.nextInt();
            System.out.print("Enter new Amount: ");
            int newAmount = scanner.nextInt();
            System.out.print("Enter new Starting point: ");
            String newStartingPoint = scanner.next();
            System.out.print("Enter new Destination point: ");
            String newDestination = scanner.next();
            System.out.print("Enter new Contact Number: ");
            String newContactNumber = scanner.next();

            // String sql = "UPDATE booking SET passenger_name = '" + newPassenger + "',"+"seat_number = " +newSeatNumber+"',"+"amount = " +newAmount+"',"+"starting_point = " +newStartingPoint+","+"destination_point = "+newDestination+ "',"+"contact_number = "+newContactNumber ;
            String sql = "UPDATE booking SET passenger_name = '" + newPassenger + "', seat_number = '" + newSeatNumber + "', amount = " + newAmount + ", starting_point = '" + newStartingPoint + "', destination_point = '" + newDestination + "', contact_number = '" + newContactNumber + "' WHERE booking_id = " + bookingId;

            try(Statement statement = connection.createStatement()){
                int AffectedRow = statement.executeUpdate(sql);

                if(AffectedRow>0){
                    System.out.println("Booking Update Successfully Done..!");
                }
                else {
                    System.out.println("Updation Failed.. Please try Again..!");
                }

            }
    }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    public static void cancleTicket(Connection connection, Scanner scanner)throws SQLException{
        try{
            System.out.print("Enter Booking ID: ");
            int bookingID = scanner.nextInt();
            if(!exitBookingId(connection,bookingID)){
                System.out.println("Booking ID not Available..!");
                return;
            }
            String sql = "DELETE FROM booking WHERE booking_id ="+bookingID;

            try(Statement statement = connection.createStatement()){
                int AffectedRow = statement.executeUpdate(sql);
                if(AffectedRow>0){
                    System.out.println("Ticket Canceled..!");
                }
                else {
                    System.out.println("Ticket Cancellation Failed..! Please try Again..! ");
                }

            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void exit()throws InterruptedException{
        System.out.print("Exiting from Application");
        int i =5;
        while (i !=0){
            System.out.print(".");
            Thread.sleep(450);
            i--;
        }
        System.out.println();
        System.out.println("Thank you for using BUS Ticket Booking System..");



    }

    public static boolean exitBookingId(Connection connection, int bookingId) throws SQLException {
        try {
            String sql = "SELECT booking_id FROM booking WHERE booking_id  = " + bookingId;
//            String sql = "SELECT reservation_id FROM reservation WHERE reservation_id = " + reservationId;

            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {
                return resultSet.next();

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

}

public class BusReservationSystem {
    private  static final String url = "jdbc:mysql://localhost:3306/bus_db";
    private static final String username = "root"; // By default it's your user name
    private static final String password = "mahesh123"; // This is your MySQL login password

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Function f = new Function();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        try{
            Connection connection= DriverManager.getConnection(url, username, password);
            while(true){
                System.out.println();
                System.out.println("BUS TICKET BOOKING SYSTEM");
                Scanner scanner =new Scanner(System.in);
                System.out.println("1. Book Ticket");
                System.out.println("2. View All Bookings");
                System.out.println("3. Find Seat Number");
                System.out.println("4. Update Booking");
                System.out.println("5. Cancel Ticket");
                System.out.println("0. Exit");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        f.bookTicket(connection, scanner);
                        break;
                    case 2:
                        f.viewAllbooking(connection);
                        break;
                    case 3:
                        f.findSeatnumber(connection, scanner);
                        break;
                    case 4:
                        f.updateBooking(connection, scanner);
                        break;
                    case 5:
                        f.cancleTicket(connection, scanner);
                        break;
                    case 0:
                        f.exit();
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid input please try Again...");
                }
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
