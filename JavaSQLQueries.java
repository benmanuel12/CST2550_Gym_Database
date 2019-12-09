import java.sql.*;

public class JavaSQLQueries {

    int trainerIndex = 6;
    int clientIndex = 11;
    int bookingIndex = 11;

    public static void main(String[] args){
	// This just holds the Java code to run the queries with variables
    }

    public static void addBooking(clientID, trainerID, date, timeStart, timeEnd, focus){
	try{
	    Connection conn = DriverManger.getConnection("jdbc:mysql://localhost/gymBookings", "root", "");

	    String newBooking = "INSERT INTO Bookings VALUES (b0" + bookingIndex.toString() + ', ' + clientID
	} catch(SQLException ex) {
	    System.out.println("SQL Error: " + ex.getMessage())
	}
    }

    public static void deleteBooking(clientID, trainerID, date){
	try{
	} catch(SQLException ex) {
	    System.out.println("SQL Error: " + ex.getMessage())
	}
    }

    public static void listBookingTrainer(trainerID){
	try{
	} catch(SQLException ex) {
	    System.out.println("SQL Error: " + ex.getMessage())
	}
    }

    public static void listBookingClient(clientID){
	try{
	} catch(SQLException ex) {
	    System.out.println("SQL Error: " + ex.getMessage())
	}
    }

    public static void listBookingDate(date){
	try{
	} catch(SQLException ex) {
	    System.out.println("SQL Error: " + ex.getMessage())
	}
    }
}
