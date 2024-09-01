
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class CancelAppointmentController implements Initializable{

    @FXML
    private TableView<dataClassCancelAppointment> Appointment;

    @FXML
    private TableColumn<dataClassCancelAppointment, String> AppointmentID;

    @FXML
    private Button Back;

    @FXML
    private Button CancelAppointment;

    @FXML
    private TableColumn<dataClassCancelAppointment, String> Date;

    @FXML
    private TableColumn<dataClassCancelAppointment, String> DoctorEmail;

    @FXML
    private TextField EnteredDoctorEmail;

    @FXML
    private TextField EnteredTime;

    @FXML
    private DatePicker EnteredDate;

    @FXML
    private TableColumn<dataClassCancelAppointment, String> PatientEmail;

    @FXML
    private TableColumn<dataClassCancelAppointment, String> Time;

    @FXML
    private TextField email;

    @FXML
    private Button search;

    ObservableList<dataClassCancelAppointment> listM;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        AppointmentID.setCellValueFactory(new PropertyValueFactory<dataClassCancelAppointment, String>("AppointmentID"));
        DoctorEmail.setCellValueFactory(new PropertyValueFactory<dataClassCancelAppointment, String>("DoctorEmail"));
        PatientEmail.setCellValueFactory(new PropertyValueFactory<dataClassCancelAppointment, String>("PatientEmail"));
        Date.setCellValueFactory(new PropertyValueFactory<dataClassCancelAppointment, String>("Date"));
        Time.setCellValueFactory(new PropertyValueFactory<dataClassCancelAppointment, String>("Time"));

        Back.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    DBUtilities.changeScene(event, "PatientHome.fxml");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                
            }
            
        });

        search.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                listM = DBUtilities.CancelAppointmentResult(email.getText());
                Appointment.getItems().addAll(listM);
                
            }
            
        });

        CancelAppointment.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                DBUtilities.Cancel_Appointment(event, EnteredDate.getValue().toString(),EnteredDoctorEmail.getText(),EnteredTime.getText());
            }
            
        });
        
        
    }

}
