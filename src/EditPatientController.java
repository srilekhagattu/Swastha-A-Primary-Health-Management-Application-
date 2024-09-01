
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class EditPatientController implements Initializable {


    @FXML
    private Button back;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Button save;

    @FXML 
    private TextField ChangeTo;
    
    @FXML
    private ChoiceBox<String> Attribute;

    private String[] choices = {"Address","Patient_Weight","Pincode"};

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        Attribute.getItems().addAll(choices);
        save.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                DBUtilities.editPatient(event,username.getText(), password.getText(),Attribute.getValue(),ChangeTo.toString());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Updated!");
                alert.show();

            }

        });

        back.setOnAction(
                new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {

                        try {
                            DBUtilities.changeScene(event, "PatientHome.fxml");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }

                });
    }

}