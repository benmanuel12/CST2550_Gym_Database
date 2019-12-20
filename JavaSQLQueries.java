import java.sql.*;

public class JavaSQLQueries {

	Client testClient = new Client('C099', 'Test', 'Client');
	Trainer testTrainer = new Trainer('T099', 'Test', 'Trainer');
	Booking testBooking = new Booking('B099', 'C099', 'T099', '2019-12-25', '00:00:00', '02:00:00', 'weight loss');

    int trainerIndex = 6;
    int clientIndex = 11;
    int bookingIndex = 11;

    public static void main(String[] args){
	System.out.println("Program running");
    }
    
	// add a booking, assuming no clashes
	// check that bookingID doesn't exits
    public static void add(Booking bookingDetails){
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

	    String sql = "SELECT * FROM Bookings;";
	    Statement statement = conn.createStatement();
	    ResultSet results = statement.executeQuery(sqlQuery);

	    while (results.next()){
		System.out.println(results.getString("bookingID") + " " 
		+ results.getString("clientID") + " " 
		+ results.getString("trainerID") + " " 
		+ results.getString("dateBooked") + " " 
		+ results.getString("timeStart") + " " 
		+ results.getString("timeEnd") + " " 
		+ results.getString("focus"));
		}
		
		statement.close();
	} catch (SQLException ex) {
	    System.out.println("SQL error: " + ex.getMessage());
	}

    }

    // list all bookings for a given trainer
    public static void listBookingTrainer(String trainerID){
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/gymBookings", "root", "");
	
			String sql = "SELECT * FROM Bookings WHERE trainerID = " + trainerID + ";";
			Statement statement = conn.createStatement();
			ResultSet results = statement.executeQuery(sqlQuery);
	
			while (results.next()) {
			System.out.println(results.getString("bookingID") + " " 
			+ results.getString("clientID") + " " 
			+ results.getString("trainerID") + " " 
			+ results.getString("dateBooked") + " " 
			+ results.getString("timeStart") + " " 
			+ results.getString("timeEnd") + " " 
			+ results.getString("focus"));
			}
			
			statement.close();
		} catch (SQLException ex) {
			System.out.println("SQL error: " + ex.getMessage());
		}
    }

    // list all bookings for a given client
    public static void listBookingClient(String clientID){
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/gymBookings", "root", "");
	
			String sql = "SELECT * FROM Bookings WHERE clientID = " + clientID + ";";
			Statement statement = conn.createStatement();
			ResultSet results = statement.executeQuery(sqlQuery);
	
			while (results.next()) {
			System.out.println(results.getString("bookingID") + " " 
			+ results.getString("clientID") + " " 
			+ results.getString("trainerID") + " " 
			+ results.getString("dateBooked") + " " 
			+ results.getString("timeStart") + " " 
			+ results.getString("timeEnd") + " " 
			+ results.getString("focus"));
			}
			
			statement.close();
		} catch (SQLException ex) {
			System.out.println("SQL error: " + ex.getMessage());
		}
    }

    // list all bookings for a given day
    public static void listBookingDate(String date){
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/gymBookings", "root", "");
	
			String sql = "SELECT * FROM Bookings WHERE dateBooked = " + date + ";";
			Statement statement = conn.createStatement();
			ResultSet results = statement.executeQuery(sqlQuery);
	
			while (results.next()){
			System.out.println(results.getString("bookingID") + " " 
			+ results.getString("clientID") + " " 
			+ results.getString("trainerID") + " " 
			+ results.getString("dateBooked") + " " 
			+ results.getString("timeStart") + " " 
			+ results.getString("timeEnd") + " " 
			+ results.getString("focus"));
			}
			
			statement.close();
		} catch (SQLException ex) {
			System.out.println("SQL error: " + ex.getMessage());
		}
    }

    // update a specific booking defined by its id
    public static void update(String bookingID, Booking bookingDetails){
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/gymBookings", "root", "");
	
			String sql = "UPDATE Bookings SET bookingID = " + bookingDetails.getId() + ", clientID = "  + bookingDetails.getClientId() + ", trainerID = "  + bookingDetails.getTrainerId() + ", dateBooked = "  + bookingDetails.getDate() + ", timeStart = "  + bookingDetails.getTimeStart() + ", timeEnd = "  + bookingDetails.getTimeEnd() + ", focus = "  + bookingDetails.getFocus() + "WHERE bookingID = " + bookingID + ";";
			Statement statement = conn.createStatement();
			ResultSet results = statement.executeQuery(sqlQuery);

			int qty = statement.executeUpdate(sql);
			System.out.println(qty + " records were updated");

			statement.close();
		} catch (SQLException ex) {
			System.out.println("SQL error: " + ex.getMessage());
		}
    }

    // delete a specific booking defined by its id
    public static void deleteBooking(String bookingID){
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/gymBookings", "root", "");
	
			String sqlQuery = "DELETE FROM Bookings WHERE bookingID = " + bookingID + ";";
			Statement statement = conn.createStatement();
			ResultSet results = statement.executeQuery(sqlQuery);
		
			int qty = statement.executeUpdate(sql);
			System.out.println(qty + " records were updated");

			statement.close();
		} catch (SQLException ex) {
			System.out.println("SQL error: " + ex.getMessage());
		}
}
