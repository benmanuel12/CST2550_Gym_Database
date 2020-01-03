import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.*;
import java.util.ArrayList;

public class JavaGUI extends Application {

    Client testClient = new Client("C099", "Test", "Client");
	Trainer testTrainer = new Trainer("T099", "Test", "Trainer");
    Booking testBooking = new Booking("B099", "C099", "T099", "2019-12-25", "00:00:00", "02:00:00", "weight loss");
    
    static TextField addbookingIDTextField = new TextField("B000");

    @Override
    public void start(Stage stage) {
        initUI(stage);
    }

    private void initUI(Stage stage) {

        // create grid
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setMinSize(300, 300);
        grid.setVgap(5);
        grid.setHgap(5);

        // UI elements for adding a new booking
        Label addBookingLabel = new Label("Add Booking");
        grid.add(addBookingLabel, 0, 0);

        
        grid.add(addbookingIDTextField, 0, 1);

        TextField addclientIDTextField = new TextField("C000");
        grid.add(addclientIDTextField, 1, 1);

        TextField addtrainerIDTextField = new TextField("T000");
        grid.add(addtrainerIDTextField, 2, 1);

        TextField adddateTextField = new TextField("YYYY-MM-DD");
        grid.add(adddateTextField, 3, 1);

        TextField addtimeStartTextField = new TextField("HH:MM:SS");
        grid.add(addtimeStartTextField, 4, 1);

        TextField addtimeEndTextField = new TextField("HH:MM:SS");
        grid.add(addtimeEndTextField, 5, 1);

        TextField addfocusTextField = new TextField("muscle gain");
        grid.add(addfocusTextField, 6, 1);

        Button addBookingSubmit = new Button("Submit");
        addBookingSubmit.setOnAction((ActionEvent event) -> {Platform.exit(); });
        grid.add(addBookingSubmit, 0, 2);


        // UI elements for updating a booking
        Label updateBookingLabel = new Label("Update Booking");
        grid.add(updateBookingLabel, 0, 3);

        TextField updatebookingIDTextField = new TextField("B000");
        grid.add(updatebookingIDTextField, 0, 4);

        TextField updateclientIDTextField = new TextField("C000");
        grid.add(updateclientIDTextField, 1, 4);

        TextField updatetrainerIDTextField = new TextField("T000");
        grid.add(updatetrainerIDTextField, 2, 4);

        TextField updatedateTextField = new TextField("YYYY-MM-DD");
        grid.add(updatedateTextField, 3, 4);

        TextField updatetimeStartTextField = new TextField("HH:MM:SS");
        grid.add(updatetimeStartTextField, 4, 4);

        TextField updatetimeEndTextField = new TextField("HH:MM:SS");
        grid.add(updatetimeEndTextField, 5, 4);

        TextField updatefocusTextField = new TextField("muscle gain");
        grid.add(updatefocusTextField, 6, 4);

        Button updateBookingSubmit = new Button("Submit");
        addBookingSubmit.setOnAction((ActionEvent event) -> {Platform.exit(); });
        grid.add(updateBookingSubmit, 0, 5);


        // UI elements for deleting a booking
        Label deleteBookingLabel = new Label("Delete Booking");
        grid.add(deleteBookingLabel, 0, 6);

        TextField deletebookingIDTextField = new TextField("B000");
        grid.add(deletebookingIDTextField, 1, 6);

        Button deleteBookingSubmit = new Button("Submit");
        addBookingSubmit.setOnAction((ActionEvent event) -> {Platform.exit(); });
        grid.add(deleteBookingSubmit, 2, 6);


        // UI elements for listing all bookings filtered by data
        Label filteredSelectLabel = new Label("List all bookings by:");
        grid.add(filteredSelectLabel, 0, 7);

        ChoiceBox<String> filterBy = new ChoiceBox<>();
        filterBy.getItems().addAll("Client ID", "Trainer ID", "Date");
        grid.add(filterBy, 1, 7);

        TextField filteredSelectTextField = new TextField();
        grid.add(filteredSelectTextField, 2, 7);

        Button filteredSelectSubmit = new Button("Submit");
        addBookingSubmit.setOnAction((ActionEvent event) -> {Platform.exit(); });
        grid.add(filteredSelectSubmit, 3, 7);

        // UI elements for listing all bookings 
        Button listAllSubmit = new Button("List All");
        listAllSubmit.setOnAction((ActionEvent event) -> {Platform.exit(); });
        grid.add(listAllSubmit, 0, 8);

        // placeholder for output
        TextField outputTextField = new TextField("Output");
        grid.add(outputTextField, 0, 8);

        Scene scene = new Scene(grid, 500, 750);

        stage.setTitle("Database GUI");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void addBookingFunction(){
        Booking newBooking = new Booking(addbookingIDTextField.getText())
    }

    public static void updateBookingFunction(){
        pass;
    }

    public static void deleteBookingFunction(){
        pass;
    }

    public static void filteredSelectFunction(){
        pass;
    }

    public static void listAllFunction(){
        pass;
    }

    
	// add a booking, assuming no clashes
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
				takenIDs.add(r1.getString("bookingID"));
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
				int newStartTime = Integer.parseInt(bookingDetails.getTimeStart().substring(0,2));
				int newEndTime = Integer.parseInt(bookingDetails.getTimeEnd().substring(0,2));
				int currentStartTime = Integer.parseInt(r4.getString("timeStart").substring(0,2));
				int currentEndTime = Integer.parseInt(r4.getString("timeEnd").substring(0,2));

				if ((newStartTime < newEndTime) && ((newEndTime < currentStartTime) || (currentEndTime < newStartTime))){
					validTime = true;
				} else {
					validTime = false;
				}
			}


			String testTimeTrainer = "SELECT timeStart, timeEnd FROM Bookings WHERE trainerID = " + bookingDetails.getTrainerId() + ";";
			ResultSet r5 = statement.executeQuery(testTimeTrainer);

			while (r5.next()){
				// if newstart is before newend and both are either before the start of the current result or after the end of the current result, set validTime to true, otherwise false
				int newStartTime = Integer.parseInt(bookingDetails.getTimeStart().substring(0,2));
				int newEndTime = Integer.parseInt(bookingDetails.getTimeEnd().substring(0,2));
				int currentStartTime = Integer.parseInt(r5.getString("timeStart").substring(0,2));
				int currentEndTime = Integer.parseInt(r5.getString("timeEnd").substring(0,2));

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
	    ResultSet results = statement.executeQuery(sql);

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
			ResultSet results = statement.executeQuery(sql);
	
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
			ResultSet results = statement.executeQuery(sql);
	
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
			ResultSet results = statement.executeQuery(sql);
	
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
				takenIDs.add(r1.getString("bookingID"));
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
				int newStartTime = Integer.parseInt(bookingDetails.getTimeStart().substring(0,2));
				int newEndTime = Integer.parseInt(bookingDetails.getTimeEnd().substring(0,2));
				int currentStartTime = Integer.parseInt(r4.getString("timeStart").substring(0,2));
				int currentEndTime = Integer.parseInt(r4.getString("timeEnd").substring(0,2));

				if ((newStartTime < newEndTime) && ((newEndTime < currentStartTime) || (currentEndTime < newStartTime))){
					validTime = true;
				} else {
					validTime = false;
				}
			}

			String testTimeTrainer = "SELECT timeStart, timeEnd FROM Bookings WHERE trainerID = " + bookingDetails.getTrainerId() + ";";
			ResultSet r5 = statement.executeQuery(testTimeTrainer);

			while (r5.next()){
				// if newstart is before newend and both are either before the start of the current result or after the end of the current result, set validTime to true, otherwise false
				int newStartTime = Integer.parseInt(bookingDetails.getTimeStart().substring(0,2));
				int newEndTime = Integer.parseInt(bookingDetails.getTimeEnd().substring(0,2));
				int currentStartTime = Integer.parseInt(r5.getString("timeStart").substring(0,2));
				int currentEndTime = Integer.parseInt(r5.getString("timeEnd").substring(0,2));

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
	
			String sql = "DELETE FROM Bookings WHERE bookingID = " + bookingID + ";";
			Statement statement = conn.createStatement();
		
			int qty = statement.executeUpdate(sql);
			System.out.println(qty + " records were updated");

			statement.close();
		} catch (SQLException ex) {
			System.out.println("SQL error: " + ex.getMessage());
        }
    }
}