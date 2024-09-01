
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class DoctorHomeController implements Initializable{

    @FXML
    private Button EditProfile;

    @FXML
    private Button LogOut;

    @FXML
    private Button ViewAppointment;

    @FXML
    private Button CreatePrescription;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LogOut.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    DBUtilities.changeScene(event,"DoctorLogin.fxml");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                
            }
            
        });

        EditProfile.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    DBUtilities.changeScene(event,"EditProfileforDoctor.fxml");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                
            }
            
        });

        ViewAppointment.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    DBUtilities.changeScene(event, "AppointmentsofDoctor.fxml");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                
            }
            
        });

        CreatePrescription.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    DBUtilities.changeScene(event, "CreatePrescription.fxml");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                
            }
            
        });
        
    }

}
