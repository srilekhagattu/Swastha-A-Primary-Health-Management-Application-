
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SignUpPatientController implements Initializable{

    @FXML
    private TextField Address;

    @FXML
    private ChoiceBox<String> BloodGroup;

    @FXML
    private DatePicker DOB;

    @FXML
    private TextField Email;

    @FXML
    private ChoiceBox<String> Gender;

    @FXML
    private TextField Height;

    @FXML
    private TextField Name;

    @FXML
    private PasswordField Password;

    @FXML
    private TextField Pincode;

    @FXML
    private Button SignUp;

    @FXML
    private Button Back;

    @FXML
    private TextField Weight;

    private String[] selectGender = {"Male","Female"};

    private String[]  selectBlood = {"A+", "A-","B+","B-","O+","AB+","AB-"};

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Gender.getItems().addAll(selectGender);
        BloodGroup.getItems().addAll(selectBlood);

        SignUp.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
               Patient patient = new Patient(Name.getText(), Email.getText(), Password.getText(), DOB.getValue().toString(), Gender.getValue(), Address.getText(), Integer.parseInt(Pincode.getText()));
               patient.setBlood_Group(BloodGroup.getValue());
               patient.setHeight(Double.parseDouble(Height.getText()));
               patient.setWeight(Integer.parseInt(Weight.getText()));
               DBUtilities.SignUpPatient(event, patient);
            }
            
        });

        Back.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    DBUtilities.changeScene(event,"PatientLogin.fxml");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                
            }
            
        });
        
        
    }



}