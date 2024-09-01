import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class PatientLoginController implements Initializable{
    
    @FXML
    private Button BackButton;

    @FXML
    private Button SignInButton;

    @FXML
    private Hyperlink createAccountLink;

    @FXML
    private TextField Username;

    @FXML
    private PasswordField password;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        SignInButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtilities.LoginPatient(event, Username.getText(), password.getText());
            }
        });

        BackButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    DBUtilities.changeScene(event, "Home.fxml");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                
            }
            
        });;
    }

    public void changetoSignUp(ActionEvent event)
    {
        try {
            DBUtilities.changeScene(event, "PatientSignUp.fxml");
        } catch (IOException e) {
            
            System.err.println(e);
        }
    }
}