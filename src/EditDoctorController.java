
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
import javafx.scene.control.TextField;

public class EditDoctorController implements Initializable {

    

    @FXML
    private Button back;

    @FXML
    private TextField password;

    @FXML
    private Button save;

    @FXML
    private TextField username;

    @FXML
    private ChoiceBox <String> Attribute;

    @FXML
    private TextField Change;

    private String[] Choices = {"Hospital_ID","Specialization","Address","Pincode"};

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        Attribute.getItems().addAll(Choices);
        save.setOnAction(new EventHandler<ActionEvent>() {
            

            @Override
            public void handle(ActionEvent event) {

               

                DBUtilities.editDoctor(event, username.getText(), password.getText(), Attribute.getValue(), Change.getText());
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
                            DBUtilities.changeScene(event, "HomeDoctor.fxml");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }

                });
    }

}