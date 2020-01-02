import java.sql.*;
import java.util.ArrayLiist;
import java.util.ArrayList;

import javax.sound.sampled.SourceDataLine;

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
	// and that the clientID and trainerID exist
	// and that for the date given
	//   the start time is before the end time
	//   and both are either before the start time or both after the end time of all other bookings involving either of the IDs
	//   (equal times is fine)
    public static void add(Booking bookingDetails){
		boolean uniqueID = false;
		boolean existingClient = false;
		boolean existingTrainer = false;
		boolean validTime = false;

		// check for user error
		
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/gymBookings", "root", "");
			Statement statement = conn.createStatement();

			// is the bookingID new?
			String testBookingID = "SELECT bookingID FROM Bookings;";
			ResultSet r1 = statement.executeQuery(testBookingID);

			ArrayList<String> takenIDs = new ArrayList<String>();
			while (r1.next()){
				takenIDs.add(r1s.getString("bookingID"));
			}

			if (!takenIDs.contains(bookingDetails.getId())){
				uniqueID = true;
			} else {
				System.out.println("Booking ID already used");
			}

			// is the Client ID valid?
			String testClientID = "SELECT clientID FROM Bookings;";
			ResultSet r2 = statement.executeQuery(testClientID);

			ArrayList<String> takenClients = new ArrayList<String>();
			while (r2.next()){
				takenClients.add(r2.getString("clientsID"));
			}

			if (!takenClients.contains(bookingDetails.getClientId())){
				existingClient = true;
			} else {
				System.out.println("Client ID already used");
			}

			// is the Trainer ID valid?
			String testTrainerID = "SELECT trainerID FROM Bookings;";
			ResultSet r3 = statement.executeQuery(testTrainerID);

			ArrayList<String> takenTrainers = new ArrayList<String>();
			while (r3.next()){
				takenTrainers.add(r3.getString("trainerID"));
			}

			if (!takenIDs.contains(bookingDetails.getTrainerId())){
				existingTrainer = true;
			} else {
				System.out.println("Trainer ID already used");
			}

			// check for clashes
			String testTimeClient = "SELECT timeStart, timeEnd FROM Bookings WHERE clientID = " + bookingDetails.getClientId() + ";";
			ResultSet r4 = statement.executeQuery(testTimeClient);
			while (r4.next()){
				// if newstart is before newend and both are either before the start of the current result or after the end of the current result, set validTime to true, otherwise false
				int newStartTime = (int)bookingDetails.getTimeStart().substring(0,2);
				int newEndTime = (int)bookingDetails.getTimeEnd().substring(0,2);
				int currentStartTime = (int)r4.getString("timeStart").substring(0,2);
				int currentEndTime = (int)r4.getString("timeEnd").substring(0,2);

				if ((newStartTime < newEndTime) && ((newEndTime < currentStartTime) || (currentEndTime < newStartTime))){
					validTime = true;
				} else {
					validTime = false;
				}
			}


			String testTimeTrainer = "SELECT timeStart, timeEnd FROM Bookings WHERE trainerID = " + bookingDetails.getTrainerId() + ";";
			ResultSet r5 = statement.executeQuery(testTrainerID);

			while (r5.next()){
				// if newstart is before newend and both are either before the start of the current result or after the end of the current result, set validTime to true, otherwise false
				int newStartTime = (int)bookingDetails.getTimeStart().substring(0,2);
				int newEndTime = (int)bookingDetails.getTimeEnd().substring(0,2);
				int currentStartTime = (int)r5.getString("timeStart").substring(0,2);
				int currentEndTime = (int)r5.getString("timeEnd").substring(0,2);

				if ((newStartTime < newEndTime) && ((newEndTime < currentStartTime) || (currentEndTime < newStartTime))){
					validTime = true;
				} else {
					validTime = false;
				}
			}

			if (validTime == false){
				System.out.println("Invalid Time");
			}
			
			// actually add the booking
			if (uniqueID && existingClient && existingTrainer && validTime){
					String sql = "INSERT INTO Bookings VALUES ("
					+ bookingDetails.getId() + ", "
					+ bookingDetails.getClientId() + ", "
					+ bookingDetails.getTrainerId() + ", "
					+ bookingDetails.getDate() + ", "
					+ bookingDetails.getTimeStart() + ", "
					+ bookingDetails.getTimeEnd() + ", "
					+ bookingDetails.getFocus() + ");";
					
					int qty = statement.executeUpdate(sql);
					System.out.println(qty + " records were updated");

					statement.close();
				}
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
	// SQL Exception should catch if the id does not exist
	// bookingID must be unique, ignore tuple with given id
	// same rules as creating a booking
    public static void update(String bookingID, Booking bookingDetails){

		boolean uniqueID = false;
		boolean existingClient = false;
		boolean existingTrainer = false;
		boolean validTime = false;

		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/gymBookings", "root", "");
			Statement statement = conn.createStatement();

			// is the bookingID new?
			String testBookingID = "SELECT bookingID FROM Bookings;";
			ResultSet r1 = statement.executeQuery(testBookingID);

			ArrayList<String> takenIDs = new ArrayList<String>();
			while (r1.next()){
				takenIDs.add(r1s.getString("bookingID"));
			}

			// remove the bookingID of the one they want to change, as if they choose not to change the id it is still a valid change
			takenIDs.remove(takenIDs.indexOf(bookingID));

			if (!takenIDs.contains(bookingDetails.getId())){
				uniqueID = true;
			} else {
				System.out.println("Booking ID already used");
			}

			// is the Client ID valid?
			String testClientID = "SELECT clientID FROM Bookings;";
			ResultSet r2 = statement.executeQuery(testClientID);

			ArrayList<String> takenClients = new ArrayList<String>();
			while (r2.next()){
				takenClients.add(r2.getString("clientsID"));
			}

			if (!takenClients.contains(bookingDetails.getClientId())){
				existingClient = true;
			} else {
				System.out.println("Client ID already used");
			}

			// is the Trainer ID valid?
			String testTrainerID = "SELECT trainerID FROM Bookings;";
			ResultSet r3 = statement.executeQuery(testTrainerID);

			ArrayList<String> takenTrainers = new ArrayList<String>();
			while (r3.next()){
				takenTrainers.add(r3.getString("trainerID"));
			}

			if (!takenIDs.contains(bookingDetails.getTrainerId())){
				existingTrainer = true;
			} else {
				System.out.println("Trainer ID already used");
			}

			// check for clashes
			String testTimeClient = "SELECT timeStart, timeEnd FROM Bookings WHERE clientID = " + bookingDetails.getClientId() + ";";
			ResultSet r4 = statement.executeQuery(testTimeClient);
			while (r4.next()){
				// if newstart is before newend and both are either before the start of the current result or after the end of the current result, set validTime to true, otherwise false
				int newStartTime = (int)bookingDetails.getTimeStart().substring(0,2);
				int newEndTime = (int)bookingDetails.getTimeEnd().substring(0,2);
				int currentStartTime = (int)r4.getString("timeStart").substring(0,2);
				int currentEndTime = (int)r4.getString("timeEnd").substring(0,2);

				if ((newStartTime < newEndTime) && ((newEndTime < currentStartTime) || (currentEndTime < newStartTime))){
					validTime = true;
				} else {
					validTime = false;
				}
			}

			String testTimeTrainer = "SELECT timeStart, timeEnd FROM Bookings WHERE trainerID = " + bookingDetails.getTrainerId() + ";";
			ResultSet r5 = statement.executeQuery(testTrainerID);

			while (r5.next()){
				// if newstart is before newend and both are either before the start of the current result or after the end of the current result, set validTime to true, otherwise false
				int newStartTime = (int)bookingDetails.getTimeStart().substring(0,2);
				int newEndTime = (int)bookingDetails.getTimeEnd().substring(0,2);
				int currentStartTime = (int)r5.getString("timeStart").substring(0,2);
				int currentEndTime = (int)r5.getString("timeEnd").substring(0,2);

				if ((newStartTime < newEndTime) && ((newEndTime < currentStartTime) || (currentEndTime < newStartTime))){
					validTime = true;
				} else {
					validTime = false;
				}
			}

			if (validTime == false){
				System.out.println("Invalid Time");
			}

			
			if (uniqueID && existingClient && existingTrainer && validTime){
				String sql = "UPDATE Bookings SET bookingID = " + bookingDetails.getId() + ", clientID = "  + bookingDetails.getClientId() + ", trainerID = "  + bookingDetails.getTrainerId() + ", dateBooked = "  + bookingDetails.getDate() + ", timeStart = "  + bookingDetails.getTimeStart() + ", timeEnd = "  + bookingDetails.getTimeEnd() + ", focus = "  + bookingDetails.getFocus() + "WHERE bookingID = " + bookingID + ";";
				int qty = statement.executeUpdate(sql);
				System.out.println(qty + " records were updated");

				statement.close();
			}
		} catch (SQLException ex) {
			System.out.println("SQL error: " + ex.getMessage());
		}
    }

	// delete a specific booking defined by its id
	// SQL Exception should catch if the id does not exist
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
