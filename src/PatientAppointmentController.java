
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

public class PatientAppointmentController implements Initializable{

    @FXML
    private TableView<dataClassPatient> AppointmentTable;

    @FXML
    private Button Back;

    @FXML
    private TableColumn<dataClassPatient, String> Date;

    @FXML
    private TableColumn<dataClassPatient, String> DoctorName;

    @FXML
    private TextField Email;

    @FXML
    private TableColumn<dataClassPatient, String> Time;

    @FXML
    private Button search;

    @FXML
    private TableColumn<dataClassPatient,Double> Fees;

    @FXML
    private TableColumn<dataClassPatient,String> HospitalID;

    @FXML
    private TableColumn<dataClassPatient,String> Specialization;

    ObservableList<dataClassPatient> listM;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Date.setCellValueFactory(new PropertyValueFactory<dataClassPatient, String>("Date"));
        Time.setCellValueFactory(new PropertyValueFactory<dataClassPatient, String>("Time"));
        DoctorName.setCellValueFactory(new PropertyValueFactory<dataClassPatient, String>("DoctorName"));
        Fees.setCellValueFactory(new PropertyValueFactory<dataClassPatient, Double>("DoctorFees"));
        HospitalID.setCellValueFactory(new PropertyValueFactory<dataClassPatient, String>("DoctorHospital"));
        Specialization.setCellValueFactory(new PropertyValueFactory<dataClassPatient, String>("DoctorSpecialization"));

        search.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                listM = DBUtilities.PatientAppointmentResult(Email.getText());
                AppointmentTable.getItems().setAll(listM);
                
            }
            
        });


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
        
    }

    

}
