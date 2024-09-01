
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class PatientHomeController implements Initializable{

    @FXML
    private Button BookAppointment;

    @FXML
    private Button CancelAppointment;

    @FXML
    private Button EditProfile;

    @FXML
    private Button LogOut;

    @FXML
    private Button SearchDoctor;

    @FXML
    private Button SearchHospital;

    @FXML
    private Button ViewAppointments;

    @FXML
    private Button ViewPrescription;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LogOut.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    DBUtilities.changeScene(event, "PatientLogin.fxml");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                
            }
            
        });

        ViewAppointments.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    DBUtilities.changeScene(event,"AppointmentsofPatient.fxml");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                
            }
            
        });

        EditProfile.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    DBUtilities.changeScene(event, "EditProfileforPatient.fxml");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                
            }
            
        });

        SearchDoctor.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    DBUtilities.changeScene(event, "SearchDoctor.fxml");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                
            }
            
        });

        SearchHospital.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    DBUtilities.changeScene(event, "SearchHospital.fxml");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                
            }
            
        });

        BookAppointment.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    DBUtilities.changeScene(event, "BookAppointment.fxml");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                
            }
            
        });

        CancelAppointment.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    DBUtilities.changeScene(event, "CancelAppointment.fxml");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                
            }
            
        });

        ViewPrescription.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    DBUtilities.changeScene(event, "ViewPrescription.fxml");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                
            }
            
        });
        
    }


}
