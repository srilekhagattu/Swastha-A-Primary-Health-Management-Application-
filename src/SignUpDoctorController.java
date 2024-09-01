
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

public class SignUpDoctorController implements Initializable{

    @FXML
    private TextField Address;

    @FXML
    private DatePicker DOB;

    @FXML
    private TextField Fee;

    @FXML
    private ChoiceBox<String> Gender;

    @FXML
    private TextField HospitalID;

    @FXML
    private TextField Name;

    @FXML
    private TextField Pincode;

    @FXML
    private ChoiceBox<String> Specialization;

    @FXML
    private Button SignUp;

    @FXML
    private TextField Email;

    @FXML
    private PasswordField Password;

    @FXML
    private Button Back;

    private String[] selectGender = {"Male","Female"};

    private String[] specialize = {"IMMUNOLOGY","ANESTHESIOLOGY","DERMATOLOGY","NEUROLOGY","PEDIATRICS","PATHOLOGY","ONCOLOGY","DENTISTRY","OPTHALMOLOGY","EMERGENCY","GYNECOLOGY","ENT"};

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Gender.getItems().addAll(selectGender);
        Specialization.getItems().addAll(specialize);
        
        SignUp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Doctor doc = new Doctor(Name.getText(),Email.getText(), Password.getText(),DOB.getValue().toString(), Gender.getValue(), Address.getText(), Integer.parseInt(Pincode.getText()));
                doc.setSpecialization(Specialization.getValue());
                doc.setHospital_ID(HospitalID.getText());
                doc.setFees(Double.parseDouble(Fee.getText()));
                DBUtilities.SignUpDoctor(event, doc);
            }
            
        });

        Back.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    DBUtilities.changeScene(event, "DoctorLogin.fxml");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                
            }
            
        });
        
    }

}
