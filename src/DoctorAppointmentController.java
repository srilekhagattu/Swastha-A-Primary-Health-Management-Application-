
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class DoctorAppointmentController implements Initializable {

    @FXML
    private TableView<dataClassDoctor> AppointmentTable;

    @FXML
    private Button Back;

    @FXML
    private TextField Email;

    @FXML
    private Button search;

    @FXML
    private TableColumn<dataClassDoctor, String> Date;

    @FXML
    private TableColumn<dataClassDoctor, String> PatientName;

    @FXML
    private TableColumn<dataClassDoctor, String> Time;

    ObservableList<dataClassDoctor> listM;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Date.setCellValueFactory(new PropertyValueFactory<dataClassDoctor, String>("date"));
        PatientName.setCellValueFactory(new PropertyValueFactory<dataClassDoctor, String>("name"));
        Time.setCellValueFactory(new PropertyValueFactory<dataClassDoctor, String>("time"));
        

        search.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                listM = DBUtilities.DoctorAppointmentResult(Email.getText());
                AppointmentTable.getItems().addAll(listM);     
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
