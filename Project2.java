package quiz2;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Project1 extends Application {

    // Database URL
private static final String DB_URL = "jdbc:sqlite:C:\\Users\\Danny\\OneDrive - Rancho Santiago Community College District\\Documents\\NetBeansProjects\\Quiz2\\quiz2.db"; // Update the path if needed

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Username & Password");

        // create UI elements for the login window
        Label lblUsername = new Label("Username:");
        TextField txtUsername = new TextField();

        Label lblPassword = new Label("Password:");
        PasswordField txtPassword = new PasswordField();

        Button button = new Button("Go");

        // layout for the login window
        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        gridPane.add(lblUsername, 0, 0);
        gridPane.add(txtUsername, 1, 0);
        gridPane.add(lblPassword, 0, 1);
        gridPane.add(txtPassword, 1, 1);
        gridPane.add(button, 1, 2);

        // login button action
        button.setOnAction(e -> {
            String username = txtUsername.getText();
            String password = txtPassword.getText();

            if ("dhern".equals(username) && "sac".equals(password)) {
                primaryStage.hide();
                JOptionPane.showMessageDialog(null, "Welcome " + username + "!");
                openSecondWindow(primaryStage);
            }
        });

        // set the scene for the login window
        Scene scene = new Scene(gridPane, 300, 150);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void openSecondWindow(Stage primaryStage) {
    Stage secondStage = new Stage();
    secondStage.setTitle("Employee Information");

    // Create labels and text fields for the form
    Label lblID = new Label("ID:");
    TextField txtID = new TextField();

    Label lblFirstName = new Label("First Name:");
    TextField txtFirstName = new TextField();

    Label lblLastName = new Label("Last Name:");
    TextField txtLastName = new TextField();

    Label lblAddress = new Label("Address:");
    TextField txtAddress = new TextField();

    Label lblCity = new Label("City:");
    TextField txtCity = new TextField();

    Label lblZip = new Label("Zip:");
    TextField txtZip = new TextField();

    Label lblState = new Label("State:");
    ComboBox<String> comboBox = new ComboBox<>();
    comboBox.getItems().addAll("Alabama", "California", "Kansas", "Florida");
    comboBox.setPromptText("Select a State:");

    Button buttonSubmit = new Button("Submit");
    Button btnClose = new Button("Close");
    Button btnUpdateLastName = new Button("Update Last Name");
    Button btnDeleteEmployee = new Button("Delete Employee");

    // layout for the second window
    GridPane gridPane = new GridPane();
    gridPane.setVgap(10);
    gridPane.setHgap(10);
    gridPane.add(lblID, 0, 0);
    gridPane.add(txtID, 1, 0);
    gridPane.add(lblFirstName, 0, 1);
    gridPane.add(txtFirstName, 1, 1);
    gridPane.add(lblLastName, 0, 2);
    gridPane.add(txtLastName, 1, 2);
    gridPane.add(lblAddress, 0, 3);
    gridPane.add(txtAddress, 1, 3);
    gridPane.add(lblCity, 0, 4);
    gridPane.add(txtCity, 1, 4);
    gridPane.add(lblZip, 0, 5);
    gridPane.add(txtZip, 1, 5);
    gridPane.add(lblState, 0, 6);
    gridPane.add(comboBox, 1, 6);

    gridPane.add(buttonSubmit, 0, 7);
    gridPane.add(btnClose, 1, 7);
    gridPane.add(btnUpdateLastName, 0, 8);
    gridPane.add(btnDeleteEmployee, 1, 8);

    // Close button action
    btnClose.setOnAction(e -> secondStage.close());

    // Submit button action
    buttonSubmit.setOnAction(e -> {
        String id = txtID.getText();
        String firstName = txtFirstName.getText();
        String lastName = txtLastName.getText();
        String address = txtAddress.getText();
        String city = txtCity.getText();
        String zip = txtZip.getText();
        String state = comboBox.getValue();

        // Check if any fields are empty
        if (id.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || address.isEmpty() || city.isEmpty() || zip.isEmpty() || state == null) {
            JOptionPane.showMessageDialog(null, "Please fill in all fields.");
        } else {
            // Insert the data into the SQLite database
            insertEmployeeData(id, firstName, lastName, address, city, zip, state);

            // Show success message
            String message = String.format("Employee Info Submitted:\nID: %s\nFirst Name: %s\nLast Name: %s\nAddress: %s\nCity: %s\nZip: %s\nState: %s",
                    id, firstName, lastName, address, city, zip, state);
            JOptionPane.showMessageDialog(null, message);
        }
    });

    // Update Last Name action
    btnUpdateLastName.setOnAction(e -> {
        String id = txtID.getText();
        String newLastName = txtLastName.getText();

        if (id.isEmpty() || newLastName.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill in both ID and new Last Name.");
        } else {
            updateEmployeeLastName(id, newLastName);
            JOptionPane.showMessageDialog(null, "Last Name Updated Successfully!");
        }
    });

    // Delete Employee action
    btnDeleteEmployee.setOnAction(e -> {
        String id = txtID.getText();

        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter an ID to delete.");
        } else {
            deleteEmployee(id);
            JOptionPane.showMessageDialog(null, "Employee Deleted Successfully!");
        }
    });

    // Set the scene for the second window
    Scene scene = new Scene(gridPane, 300, 300);
    secondStage.setScene(scene);
    secondStage.show();
}


    // Method to insert data into the SQLite database
    private void insertEmployeeData(String id, String firstName, String lastName, String address, String city, String zip, String state) {
        // SQLite connection string
        String sql = "INSERT INTO employees (id, first_name, last_name, address, city, zip, state) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL); // Establish connection
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Set values for the prepared statement
            pstmt.setString(1, id); // ID field
            pstmt.setString(2, firstName); // First Name field
            pstmt.setString(3, lastName); // Last Name field
            pstmt.setString(4, address); // Address field
            pstmt.setString(5, city); // City field
            pstmt.setString(6, zip); // Zip field
            pstmt.setString(7, state); // State field

            // Execute the insert statement
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error inserting data: " + e.getMessage());
        }
    }
    
    private void deleteEmployee(String id) {
    String sql = "DELETE FROM employees WHERE id = ?";

    try (Connection conn = DriverManager.getConnection(DB_URL);
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setString(1, id); // Set the employee's ID to delete

        pstmt.executeUpdate(); // Execute the delete statement
    } catch (SQLException e) {
        System.out.println("Error deleting data: " + e.getMessage());
    }
}
    
    private void updateEmployeeLastName(String id, String newLastName) {
    String sql = "UPDATE employees SET last_name = ? WHERE id = ?";

    try (Connection conn = DriverManager.getConnection(DB_URL);
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setString(1, newLastName); // Set the new last name
        pstmt.setString(2, id); // Set the employee's ID for which the last name should be updated

        pstmt.executeUpdate(); // Execute the update statement
    } catch (SQLException e) {
        System.out.println("Error updating data: " + e.getMessage());
    }
}



    public static void main(String[] args) {
        launch(args);
    }
}
