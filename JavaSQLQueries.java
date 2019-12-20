import java.sql.*;

public class JavaSQLQueries {

    int trainerIndex = 6;
    int clientIndex = 11;
    int bookingIndex = 11;

    public static void main(String[] args){
	System.out.println("Program running");
    }

    public static void basicTemplate(){
	try {
	    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/gymBookings", "root", "");

	    String sqlQuery = "SELECT * FROM User";
	    Statement statement = conn.createStatement();
	    ResultSet results = statement.executeQuery(sqlQuery);

	    while (results.next()){
		System.out.println(results.getString(1) + " " + results.getString(2));
	    }
	} catch (SQLException ex) {
	    System.out.println("SQL error: " + ex.getMessage());
	}
    }
    
    // add a booking, assuming no clashes
    public static void add(bookingDetails){
	try {
	    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/gymBookings", "root", "");

	    String sql = "INSERT INTO Bookings VALUES ("
		+ bookingDetails.getId() + ", "
		+ bookingDetails.getClientId() + ", "
		+ bookingDetails.getTrainerId() + ", "
		+ bookingDetails.getDate() + ", "
		+ bookingDetails.getTimeStart() + ", "
		+ bookingDetails.getTimeEnd() + ", "
		+ bookingDetails.getFocus() + ");";
	    
	    Statement statement = conn.createStatement();
	    int qty = statement.executeUpdate(sql);
	    System.out.println(qty + " records were updated");

	    statement.close();
	} catch (SQLException ex) {
	    System.out.println("SQL error: " + ex.getMessage());
	}
    }

    // list all bookings
    public static void listAll(){
		try {
	    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/gymBookings", "root", "");

	    String sqlQuery = "SELECT * FROM User";
	    Statement statement = conn.createStatement();
	    ResultSet results = statement.executeQuery(sqlQuery);

	    while (results.next()){
		System.out.println(results.getString(1) + " " + results.getString(2));
	    }
	} catch (SQLException ex) {
	    System.out.println("SQL error: " + ex.getMessage());
	}

    }

    // list all bookings for a given trainer
    public static void listBookingTrainer(trainerID){
	try{
	} catch(SQLException ex) {
	    System.out.println("SQL Error: " + ex.getMessage())
	}
    }

    // list all bookings for a given client
    public static void listBookingClient(clientID){
	try{
	} catch(SQLException ex) {
	    System.out.println("SQL Error: " + ex.getMessage())
	}
    }

    // list all bookings for a given day
    public static void listBookingDate(date){
	try{
	} catch(SQLException ex) {
	    System.out.println("SQL Error: " + ex.getMessage())
	}
    }

    // update a specific booking defined by its id
    public static void update(bookingID, bookingDetails){
	
    }

    // delete a specific booking defined by its id
    public static void deleteBooking(clientID, trainerID, date){
	try{
	} catch(SQLException ex) {
	    System.out.println("SQL Error: " + ex.getMessage())
	}
    }
}
