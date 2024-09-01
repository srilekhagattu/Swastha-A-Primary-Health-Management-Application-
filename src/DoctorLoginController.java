
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

public class DoctorLoginController implements Initializable{

    @FXML
    private TextField Email;

    @FXML
    private Button LoginButton;

    @FXML
    private PasswordField Password;

    @FXML
    private Hyperlink CreateAccountLink;

    @FXML
    private Button BackButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        LoginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                
                DBUtilities.LoginDoctor(event, Email.getText(), Password.getText());
                
            }
        });

        BackButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    DBUtilities.changeScene(event,"Home.fxml");
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                
            }
        });
    }

    public void changetoSignUp(ActionEvent event)
    {
        try {
            DBUtilities.changeScene(event,"SignUpforDoctor.fxml");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
