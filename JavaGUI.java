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
	// defining Textfields and ChoiceBoxes here so their contents can be extracted
	// for Queries outside of initUI
	static TextField addbookingIDTextField = new TextField("B000");
	static TextField addclientIDTextField = new TextField("C000");
	static TextField addtrainerIDTextField = new TextField("T000");
	static TextField adddateTextField = new TextField("YYYY-MM-DD");
	static TextField addtimeStartTextField = new TextField("HH:MM:SS");
	static TextField addtimeEndTextField = new TextField("HH:MM:SS");
	static TextField addfocusTextField = new TextField("muscle gain");

	static TextField updatetargetID = new TextField("B000");
	static TextField updatebookingIDTextField = new TextField("B000");
	static TextField updateclientIDTextField = new TextField("C000");
	static TextField updatetrainerIDTextField = new TextField("T000");
	static TextField updatedateTextField = new TextField("YYYY-MM-DD");
	static TextField updatetimeStartTextField = new TextField("HH:MM:SS");
	static TextField updatetimeEndTextField = new TextField("HH:MM:SS");
	static TextField updatefocusTextField = new TextField("muscle gain");

	static TextField deletebookingIDTextField = new TextField("B000");

	static ChoiceBox<String> filterBy = new ChoiceBox<>();
	static TextField filteredSelectTextField = new TextField();

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
		grid.add(addclientIDTextField, 1, 1);
		grid.add(addtrainerIDTextField, 2, 1);
		grid.add(adddateTextField, 3, 1);
		grid.add(addtimeStartTextField, 4, 1);
		grid.add(addtimeEndTextField, 5, 1);
		grid.add(addfocusTextField, 6, 1);

		Button addBookingSubmit = new Button("Submit");
		addBookingSubmit.setOnAction((ActionEvent event) -> {
			addBookingFunction();
		});
		grid.add(addBookingSubmit, 0, 2);

		// UI elements for updating a booking
		Label updateBookingLabel = new Label("Update Booking");
		grid.add(updateBookingLabel, 0, 3);

		Label updateTargetLabel = new Label("Target ID");
		grid.add(updateTargetLabel, 0, 4);

		grid.add(updatetargetID, 1, 4);
		grid.add(updatebookingIDTextField, 0, 5);
		grid.add(updateclientIDTextField, 1, 5);
		grid.add(updatetrainerIDTextField, 2, 5);
		grid.add(updatedateTextField, 3, 5);
		grid.add(updatetimeStartTextField, 4, 5);
		grid.add(updatetimeEndTextField, 5, 5);
		grid.add(updatefocusTextField, 6, 5);

		Button updateBookingSubmit = new Button("Submit");
		addBookingSubmit.setOnAction((ActionEvent event) -> {
			updateBookingFunction();
		});
		grid.add(updateBookingSubmit, 0, 6);

		// UI elements for deleting a booking
		Label deleteBookingLabel = new Label("Delete Booking");
		grid.add(deleteBookingLabel, 0, 7);

		grid.add(deletebookingIDTextField, 1, 7);

		Button deleteBookingSubmit = new Button("Submit");
		addBookingSubmit.setOnAction((ActionEvent event) -> {
			deleteBookingFunction();
		});
		grid.add(deleteBookingSubmit, 2, 7);

		// UI elements for listing all bookings filtered by data
		Label filteredSelectLabel = new Label("List all bookings by:");
		grid.add(filteredSelectLabel, 0, 8);

		filterBy.getItems().addAll("Client ID", "Trainer ID", "Date");
		grid.add(filterBy, 1, 8);

		grid.add(filteredSelectTextField, 2, 8);

		Button filteredSelectSubmit = new Button("Submit");
		addBookingSubmit.setOnAction((ActionEvent event) -> {
			filteredSelectFunction();
		});
		grid.add(filteredSelectSubmit, 3, 8);

		// UI elements for listing all bookings
		Button listAllSubmit = new Button("List All");
		listAllSubmit.setOnAction((ActionEvent event) -> {
			listAll();
		});
		grid.add(listAllSubmit, 0, 9);

		// placeholder for output
		TextField outputTextField = new TextField("Output");
		outputTextField.setPrefWidth(1000);
		outputTextField.setMaxWidth(1000);
		grid.setColumnSpan(outputTextField, 7);
		grid.add(outputTextField, 0, 10);

		Scene scene = new Scene(grid, 1000, 500);

		stage.setTitle("Database GUI");
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	public static void addBookingFunction() {
		String newbookingID = addbookingIDTextField.getText();
		String newclientID = addclientIDTextField.getText();
		String newtrainerID = addtrainerIDTextField.getText();
		String newdate = adddateTextField.getText();
		String newtimeStart= addtimeStartTextField.getText();
		String newtimeEnd = addtimeEndTextField.getText();
		String newfocus = addfocusTextField.getText();
		Booking newBooking = new Booking(newbookingID, newclientID, newtrainerID, newdate, newtimeStart, newtimeEnd, newfocus);
		add(newBooking);
	}

	public static void updateBookingFunction() {
		String targetID = updatetargetID.getText();
		String newbookingID = updatebookingIDTextField.getText();
		String newclientID = updateclientIDTextField.getText();
		String newtrainerID = updatetrainerIDTextField.getText();
		String newdate = updatedateTextField.getText();
		String newtimeStart= updatetimeStartTextField.getText();
		String newtimeEnd = updatetimeEndTextField.getText();
		String newfocus = updatefocusTextField.getText();
		Booking newBooking = new Booking(newbookingID, newclientID, newtrainerID, newdate, newtimeStart, newtimeEnd, newfocus);
		update(targetID, newBooking);
	}

	public static void deleteBookingFunction() {
		String chosenID = deletebookingIDTextField.getText();
		delete(chosenID);
	}

	public static void filteredSelectFunction() {
		String chosenType = filterBy.getValue();
		String input = filteredSelectTextField.getText();
		listBookingFiltered(chosenType, input);
	}

	// add a booking, assuming no clashes
	public static void add(Booking bookingDetails) {
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
			while (r1.next()) {
				takenIDs.add(r1.getString("bookingID"));
			}

			if (!takenIDs.contains(bookingDetails.getId())) {
				uniqueID = true;
			} else {
				System.out.println("Booking ID already used");
			}

			// is the Client ID valid?
			String testClientID = "SELECT clientID FROM Bookings;";
			ResultSet r2 = statement.executeQuery(testClientID);

			ArrayList<String> takenClients = new ArrayList<String>();
			while (r2.next()) {
				takenClients.add(r2.getString("clientsID"));
			}

			if (!takenClients.contains(bookingDetails.getClientId())) {
				existingClient = true;
			} else {
				System.out.println("Client ID already used");
			}

			// is the Trainer ID valid?
			String testTrainerID = "SELECT trainerID FROM Bookings;";
			ResultSet r3 = statement.executeQuery(testTrainerID);

			ArrayList<String> takenTrainers = new ArrayList<String>();
			while (r3.next()) {
				takenTrainers.add(r3.getString("trainerID"));
			}

			if (!takenIDs.contains(bookingDetails.getTrainerId())) {
				existingTrainer = true;
			} else {
				System.out.println("Trainer ID already used");
			}

			// check for clashes
			String testTimeClient = "SELECT timeStart, timeEnd FROM Bookings WHERE clientID = "
					+ bookingDetails.getClientId() + ";";
			ResultSet r4 = statement.executeQuery(testTimeClient);
			while (r4.next()) {
				// if newstart is before newend and both are either before the start of the
				// current result or after the end of the current result, set validTime to true,
				// otherwise false
				int newStartTime = Integer.parseInt(bookingDetails.getTimeStart().substring(0, 2));
				int newEndTime = Integer.parseInt(bookingDetails.getTimeEnd().substring(0, 2));
				int currentStartTime = Integer.parseInt(r4.getString("timeStart").substring(0, 2));
				int currentEndTime = Integer.parseInt(r4.getString("timeEnd").substring(0, 2));

				if ((newStartTime < newEndTime)
						&& ((newEndTime < currentStartTime) || (currentEndTime < newStartTime))) {
					validTime = true;
				} else {
					validTime = false;
				}
			}

			String testTimeTrainer = "SELECT timeStart, timeEnd FROM Bookings WHERE trainerID = "
					+ bookingDetails.getTrainerId() + ";";
			ResultSet r5 = statement.executeQuery(testTimeTrainer);

			while (r5.next()) {
				// if newstart is before newend and both are either before the start of the
				// current result or after the end of the current result, set validTime to true,
				// otherwise false
				int newStartTime = Integer.parseInt(bookingDetails.getTimeStart().substring(0, 2));
				int newEndTime = Integer.parseInt(bookingDetails.getTimeEnd().substring(0, 2));
				int currentStartTime = Integer.parseInt(r5.getString("timeStart").substring(0, 2));
				int currentEndTime = Integer.parseInt(r5.getString("timeEnd").substring(0, 2));

				if ((newStartTime < newEndTime)
						&& ((newEndTime < currentStartTime) || (currentEndTime < newStartTime))) {
					validTime = true;
				} else {
					validTime = false;
				}
			}

			if (validTime == false) {
				System.out.println("Invalid Time");
			}

			// actually add the booking
			if (uniqueID && existingClient && existingTrainer && validTime) {
				String sql = "INSERT INTO Bookings VALUES (" + bookingDetails.getId() + ", "
						+ bookingDetails.getClientId() + ", " + bookingDetails.getTrainerId() + ", "
						+ bookingDetails.getDate() + ", " + bookingDetails.getTimeStart() + ", "
						+ bookingDetails.getTimeEnd() + ", " + bookingDetails.getFocus() + ");";

				int qty = statement.executeUpdate(sql);
				System.out.println(qty + " records were updated");

				statement.close();
			}
		} catch (SQLException ex) {
			System.out.println("SQL error: " + ex.getMessage());
		}
	}

	// list all bookings
	public static void listAll() {
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/gymBookings", "root", "");

			String sql = "SELECT * FROM Bookings;";
			Statement statement = conn.createStatement();
			ResultSet results = statement.executeQuery(sql);

			while (results.next()) {
				System.out.println(results.getString("bookingID") + " " + results.getString("clientID") + " "
						+ results.getString("trainerID") + " " + results.getString("dateBooked") + " "
						+ results.getString("timeStart") + " " + results.getString("timeEnd") + " "
						+ results.getString("focus"));
			}

			statement.close();
		} catch (SQLException ex) {
			System.out.println("SQL error: " + ex.getMessage());
		}

	}

	// list all bookings for a given client/trainer/date
	public static void listBookingFiltered(String type, String data) {
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/gymBookings", "root", "");

			String sql = "SELECT * FROM Bookings WHERE " + type + " = " + data + ";";
			Statement statement = conn.createStatement();
			ResultSet results = statement.executeQuery(sql);

			while (results.next()) {
				System.out.println(results.getString("bookingID") + " " + results.getString("clientID") + " "
						+ results.getString("trainerID") + " " + results.getString("dateBooked") + " "
						+ results.getString("timeStart") + " " + results.getString("timeEnd") + " "
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
	public static void update(String bookingID, Booking bookingDetails) {

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
			while (r1.next()) {
				takenIDs.add(r1.getString("bookingID"));
			}

			// remove the bookingID of the one they want to change, as if they choose not to
			// change the id it is still a valid change
			takenIDs.remove(takenIDs.indexOf(bookingID));

			if (!takenIDs.contains(bookingDetails.getId())) {
				uniqueID = true;
			} else {
				System.out.println("Booking ID already used");
			}

			// is the Client ID valid?
			String testClientID = "SELECT clientID FROM Bookings;";
			ResultSet r2 = statement.executeQuery(testClientID);

			ArrayList<String> takenClients = new ArrayList<String>();
			while (r2.next()) {
				takenClients.add(r2.getString("clientsID"));
			}

			if (!takenClients.contains(bookingDetails.getClientId())) {
				existingClient = true;
			} else {
				System.out.println("Client ID already used");
			}

			// is the Trainer ID valid?
			String testTrainerID = "SELECT trainerID FROM Bookings;";
			ResultSet r3 = statement.executeQuery(testTrainerID);

			ArrayList<String> takenTrainers = new ArrayList<String>();
			while (r3.next()) {
				takenTrainers.add(r3.getString("trainerID"));
			}

			if (!takenIDs.contains(bookingDetails.getTrainerId())) {
				existingTrainer = true;
			} else {
				System.out.println("Trainer ID already used");
			}

			// check for clashes
			String testTimeClient = "SELECT timeStart, timeEnd FROM Bookings WHERE clientID = "
					+ bookingDetails.getClientId() + ";";
			ResultSet r4 = statement.executeQuery(testTimeClient);
			while (r4.next()) {
				// if newstart is before newend and both are either before the start of the
				// current result or after the end of the current result, set validTime to true,
				// otherwise false
				int newStartTime = Integer.parseInt(bookingDetails.getTimeStart().substring(0, 2));
				int newEndTime = Integer.parseInt(bookingDetails.getTimeEnd().substring(0, 2));
				int currentStartTime = Integer.parseInt(r4.getString("timeStart").substring(0, 2));
				int currentEndTime = Integer.parseInt(r4.getString("timeEnd").substring(0, 2));

				if ((newStartTime < newEndTime)
						&& ((newEndTime < currentStartTime) || (currentEndTime < newStartTime))) {
					validTime = true;
				} else {
					validTime = false;
				}
			}

			String testTimeTrainer = "SELECT timeStart, timeEnd FROM Bookings WHERE trainerID = "
					+ bookingDetails.getTrainerId() + ";";
			ResultSet r5 = statement.executeQuery(testTimeTrainer);

			while (r5.next()) {
				// if newstart is before newend and both are either before the start of the
				// current result or after the end of the current result, set validTime to true,
				// otherwise false
				int newStartTime = Integer.parseInt(bookingDetails.getTimeStart().substring(0, 2));
				int newEndTime = Integer.parseInt(bookingDetails.getTimeEnd().substring(0, 2));
				int currentStartTime = Integer.parseInt(r5.getString("timeStart").substring(0, 2));
				int currentEndTime = Integer.parseInt(r5.getString("timeEnd").substring(0, 2));

				if ((newStartTime < newEndTime)
						&& ((newEndTime < currentStartTime) || (currentEndTime < newStartTime))) {
					validTime = true;
				} else {
					validTime = false;
				}
			}

			if (validTime == false) {
				System.out.println("Invalid Time");
			}

			if (uniqueID && existingClient && existingTrainer && validTime) {
				String sql = "UPDATE Bookings SET bookingID = " + bookingDetails.getId() + ", clientID = "
						+ bookingDetails.getClientId() + ", trainerID = " + bookingDetails.getTrainerId()
						+ ", dateBooked = " + bookingDetails.getDate() + ", timeStart = "
						+ bookingDetails.getTimeStart() + ", timeEnd = " + bookingDetails.getTimeEnd() + ", focus = "
						+ bookingDetails.getFocus() + "WHERE bookingID = " + bookingID + ";";
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
	public static void delete(String bookingID) {
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
