
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

public class HomeController implements Initializable{

    @FXML
    private Button DoctorButton;

    @FXML
    private Button PatientButton;

    @FXML
    private Button CSVLoad;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DoctorButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                try {
                    DBUtilities.createTables();
                    DBUtilities.changeScene(event, "DoctorLogin.fxml");
                } catch (IOException | SQLException e) {
                    e.printStackTrace();
                }
            }
            
        });

        PatientButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    DBUtilities.createTables();

                    DBUtilities.changeScene(event,"PatientLogin.fxml");
                } catch (IOException | SQLException e) {
                    e.printStackTrace();
                }
                
            }
            
        });

        CSVLoad.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                DBUtilities.Load_Doctor_CSV();
                DBUtilities.Load_Patient_CSV();
                DBUtilities.Hospital_CSV_To_Database();
                DBUtilities.Appointment_CSV_To_Database();
                DBUtilities.Load_Prescription_CSV();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Data Loaded From CSV");
                alert.show();
            }
            
        });;
        
    }
}