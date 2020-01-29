import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class JavaClient extends Application {

	static String HOST;
	static int PORT;

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

	static Text outputText = new Text("Output");

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
			addBooking();
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
			updateBooking();
		});
		grid.add(updateBookingSubmit, 0, 6);

		// UI elements for deleting a booking
		Label deleteBookingLabel = new Label("Delete Booking");
		grid.add(deleteBookingLabel, 0, 7);

		grid.add(deletebookingIDTextField, 1, 7);

		Button deleteBookingSubmit = new Button("Submit");
		addBookingSubmit.setOnAction((ActionEvent event) -> {
			deleteBooking();
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
			filteredSelect();
		});
		grid.add(filteredSelectSubmit, 3, 8);

		// UI elements for listing all bookings
		Button listAllSubmit = new Button("List All");
		listAllSubmit.setOnAction((ActionEvent event) -> {
			listAll();
		});
		grid.add(listAllSubmit, 0, 9);

		// placeholder for output

		grid.setColumnSpan(outputText, 7);
		grid.add(outputText, 0, 10);

		Scene scene = new Scene(grid, 1000, 500);

		stage.setTitle("Database GUI");
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
		if (args.length == 2) {
			HOST = args[0];
			PORT = Integer.parseInt(args[1]);
		} else {
			HOST = "localhost";
			PORT = 13;
		}

	}

	public static void addBooking() {
		String newbookingID = addbookingIDTextField.getText();
		String newclientID = addclientIDTextField.getText();
		String newtrainerID = addtrainerIDTextField.getText();
		String newdate = adddateTextField.getText();
		String newtimeStart = addtimeStartTextField.getText();
		String newtimeEnd = addtimeEndTextField.getText();
		String newfocus = addfocusTextField.getText();
		Booking newBooking = new Booking(newbookingID, newclientID, newtrainerID, newdate, newtimeStart, newtimeEnd,
				newfocus);

		try {
			Socket socket = new Socket(HOST, PORT);

			ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
			output.writeObject("Add");
			output.writeObject(newBooking);

			Scanner input = new Scanner(socket.getInputStream());
			String data = "";
			while (input.hasNextLine()) {
				data = data + input.nextLine();
			}
			outputText.setText(data);

		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}

	}

	public static void updateBooking() {
		String targetID = updatetargetID.getText();
		String newbookingID = updatebookingIDTextField.getText();
		String newclientID = updateclientIDTextField.getText();
		String newtrainerID = updatetrainerIDTextField.getText();
		String newdate = updatedateTextField.getText();
		String newtimeStart = updatetimeStartTextField.getText();
		String newtimeEnd = updatetimeEndTextField.getText();
		String newfocus = updatefocusTextField.getText();
		Booking newBooking = new Booking(newbookingID, newclientID, newtrainerID, newdate, newtimeStart, newtimeEnd,
				newfocus);
		try {
			Socket socket = new Socket(HOST, PORT);

			ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
			output.writeObject("Update");
			output.writeObject(newBooking);
			output.flush();

			Scanner input = new Scanner(socket.getInputStream());
			String data = "";
			while (input.hasNextLine()) {
				data = data + input.nextLine();
			}
			outputText.setText(data);

		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
	}

	public static void deleteBooking() {
		String chosenID = deletebookingIDTextField.getText();
		try {
			Socket socket = new Socket(HOST, PORT);

			ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
			output.writeObject("Delete");
			output.writeObject(chosenID);
			output.flush();

			Scanner input = new Scanner(socket.getInputStream());
			String data = "";
			while (input.hasNextLine()){
				data = data + input.nextLine();
			}
			outputText.setText(data);
			
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
	}

	public static void filteredSelect() {
		String chosenType = filterBy.getValue();
		String value = filteredSelectTextField.getText();
		try {
			Socket socket = new Socket(HOST, PORT);

			ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
			output.writeObject("Filter");
			output.writeObject(chosenType);
			output.writeObject(value);
			output.flush();

			Scanner input = new Scanner(socket.getInputStream());
			String data = "";
			while (input.hasNextLine()){
				data = data + input.nextLine();
			}
			outputText.setText(data);

		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
	}

	public static void listAll() {
		try {
			Socket socket = new Socket(HOST, PORT);

			ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
			output.writeObject("All");
			output.flush();

			Scanner input = new Scanner(socket.getInputStream());
			String data = "";
			while (input.hasNextLine()){
				data = data + input.nextLine();
			}
			outputText.setText(data);

		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
	}
}