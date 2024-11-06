
package quiz2;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author Danny
 */
public class Project1 extends Application {
    
    private static final String DB_URL = "jdbc:sqlite:employee.db"; // Update the path if needed
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Username & Password");
        
        //create UI elements for the login window
        Label lblUsername = new Label("Username:");
        TextField txtUsername = new TextField();
        
        Label lblPassword = new Label("Password:");
        PasswordField txtPassword = new PasswordField();
        
        Button button = new Button("Go");
        
        //layout for the login window
        //create a gridPane layout for arranging elements in
        GridPane gridPane = new GridPane();
        
        //set vertical and horizontal spacing between rows in the grid
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        
        gridPane.add(lblUsername, 0, 0);
        gridPane.add(txtUsername, 1, 0);
        gridPane.add(lblPassword, 0, 1);
        gridPane.add(txtPassword, 1, 1);
        gridPane.add(button, 1, 2);
        
        //login button action
        button.setOnAction(e -> {
         
            String username = txtUsername.getText();
            String password = txtPassword.getText();
            
            if("dhern".equals(username) && "sac".equals(password))
            {
                primaryStage.hide();
                
                JOptionPane.showMessageDialog(null, "Welcome " + username + "!");
                
                
                openSecondWindow(primaryStage);
                
            }
        }
        
        
        );
        
        //set the scene for the login window
        Scene scene = new Scene(gridPane, 300, 150);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private void openSecondWindow(Stage primaryStage)
    {
     Stage secondStage = new Stage();
     secondStage.setTitle("Employee Information");
     
     Label lblID = new Label("ID:");
     TextField txtID = new TextField();
     
     Label lblFirstName = new Label("First Name:");
     TextField txtFirstName = new TextField();
     
     Label lblLastName = new Label("Last Name:");
     TextField txtLastName = new TextField();
     
     Label lblAddress = new Label("Addrss:");
     TextField txtAddress = new TextField();
     
     Label lblCity = new Label("City:");
     TextField txtCity = new TextField();
     
     Label lblZip = new Label("Zip:");
     TextField txtZip = new TextField();
     
     Label lblState = new Label("State:");
     ComboBox<String> comboBox = new ComboBox<>();
     
     comboBox.getItems().addAll(
             "Alabama", "California", "Kansas", "Florida" 
     
     );
     
     Button buttonSubmit = new Button("Submit");
     Button btnClose = new Button("Close");
     
     comboBox.setPromptText("Select a State:");
     
     //layout for the second window
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
     
     //set action for close button
     btnClose.setOnAction(e -> secondStage.close());
     
     
     buttonSubmit.setOnAction(e ->
     {
      
         String id = txtID.getText();
         String firstName = txtFirstName.getText();
         String lastName = txtLastName.getText();
         String address = txtAddress.getText();
         String city = txtCity.getText();
         String zip = txtZip.getText();
         String state = comboBox.getValue();
         
          if (id.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || address.isEmpty() 
                || city.isEmpty() || zip.isEmpty() || state == null) {
            // If any field is empty or state is not selected, show an error message
            JOptionPane.showMessageDialog(null, "Please fill in all fields.");
        } else {
            // Display a success message with the entered data
            String message = String.format("Employee Info Submitted:\nID: %s\nFirst Name: %s\nLast Name: %s\nAddress: %s\nCity: %s\nZip: %s\nState: %s",
                                           id, firstName, lastName, address, city, zip, state);
            JOptionPane.showMessageDialog(null, message);
        }
         
     });
     
     
     
     //set the scene for the second window
     Scene scene = new Scene(gridPane, 300, 300);
     secondStage.setScene(scene);
     secondStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
