
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

public class ViewPrescriptionController implements Initializable{

    @FXML
    private Button Back;

    @FXML
    private TextField DoctorEmail;

    @FXML
    private TableColumn<dataClassPrescription, String> DoctorEmailColumn;

    @FXML
    private TableColumn<dataClassPrescription, String> Dosage;

    @FXML
    private TableColumn<dataClassPrescription, String> Dosage1;

    @FXML
    private TableColumn<dataClassPrescription, String> Dosage2;

    @FXML
    private TableColumn<dataClassPrescription, String> Medicine;

    @FXML
    private TableColumn<dataClassPrescription, String> Medicine1;

    @FXML
    private TableColumn<dataClassPrescription, String> Medicine2;

    @FXML
    private TextField PatientEmail;

    @FXML
    private TableColumn<dataClassPrescription, String> PatientEmailColumn;

    @FXML
    private Button Search;

    @FXML
    private TableView<dataClassPrescription> Table;

    ObservableList<dataClassPrescription> listM;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        DoctorEmailColumn.setCellValueFactory(new PropertyValueFactory<dataClassPrescription, String>("DoctorEmail"));
        PatientEmailColumn.setCellValueFactory(new PropertyValueFactory<dataClassPrescription, String>("PatientEmail"));
        Medicine1.setCellValueFactory(new PropertyValueFactory<dataClassPrescription, String>("Medicine1"));
        Medicine2.setCellValueFactory(new PropertyValueFactory<dataClassPrescription, String>("Medicine2"));
        Dosage1.setCellValueFactory(new PropertyValueFactory<dataClassPrescription, String>("Dosage1"));
        Dosage2.setCellValueFactory(new PropertyValueFactory<dataClassPrescription, String>("Dosage2"));
        

        Search.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                listM = DBUtilities.ViewPrescriptionsResult(DoctorEmail.getText(), PatientEmail.getText());
                Table.getItems().addAll(listM);
                
            }
            
        });

        Back.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    DBUtilities.changeScene(event,"PatientHome.fxml");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                
            }
            
        });
    }

}
