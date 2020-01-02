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

public class JavaGUI extends Application {
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

        TextField addbookingIDTextField = new TextField("B000");
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
        Label updateBookingLabel = new Label("Delete Booking");
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

        ChoiceBox filterBy = new ChoiceBox();
        filterBy.getItems().addAll("Client ID", "Trainer ID", "Date");
        grid.add(filterBy, 1, 7);

        TextField filteredSelectTextField = new TextField();
        grid.add(filteredSelectTextField, 2, 7);

        Button filteredSelectSubmit = new Button("Submit");
        addBookingSubmit.setOnAction((ActionEvent event) -> {Platform.exit(); });
        grid.add(filteredSelectSubmit, 3, 7);

        // list all

        Scene scene = new Scene(grid, 500, 500);

        stage.setTitle("Database GUI");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}