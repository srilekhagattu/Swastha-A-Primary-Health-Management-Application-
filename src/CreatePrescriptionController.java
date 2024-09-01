
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CreatePrescriptionController implements Initializable{

    @FXML
    private TextField DoctorEmail;

    @FXML
    private TextField Dosage1;

    @FXML
    private TextField Dosage2;

    @FXML
    private TextField Medicine1;

    @FXML
    private TextField Medicine2;

    @FXML
    private TextField PatientEmail;

    @FXML
    private Button SaveButton;

    @FXML
    private Button Back;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        SaveButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                DBUtilities.createPrescription(event, DoctorEmail.getText(), PatientEmail.getText(), Medicine1.getText(), Dosage1.getText(), Medicine2.getText(), Dosage2.getText());
                
            }
            
        });

        Back.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
               try {
                DBUtilities.changeScene(event, "HomeDoctor.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
                
            }
            
        });
        
    }

}
