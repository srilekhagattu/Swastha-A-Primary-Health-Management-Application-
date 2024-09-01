
import java.io.IOException;
import java.net.URL;
import java.sql.*;
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

public class DoctorSearchController implements Initializable {

    @FXML
    private Button Back;

    @FXML
    private TableColumn<dataClassSearchDoctor, String> DisplayDoctorName;

    @FXML
    private TableColumn<dataClassSearchDoctor, String> DoctorEmail;

    @FXML
    private TextField DoctorName;

    @FXML
    private TableColumn<dataClassSearchDoctor, String> HospitalID;

    @FXML
    private TableColumn<dataClassSearchDoctor, String> HospitalName;

    @FXML
    private Button Search;

    @FXML
    private TableColumn<dataClassSearchDoctor, String> Specialization;

    @FXML
    private TableView<dataClassSearchDoctor> TableView;

    ObservableList<dataClassSearchDoctor> List;
    int index = -1;

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DisplayDoctorName
                .setCellValueFactory(new PropertyValueFactory<dataClassSearchDoctor, String>("DoctorName"));
        DoctorEmail
                .setCellValueFactory(new PropertyValueFactory<dataClassSearchDoctor, String>("DoctorEmail"));

        HospitalName.setCellValueFactory(new PropertyValueFactory<dataClassSearchDoctor, String>("HospitalName"));
        HospitalID.setCellValueFactory(new PropertyValueFactory<dataClassSearchDoctor, String>("HospitalID"));
        Specialization.setCellValueFactory(new PropertyValueFactory<dataClassSearchDoctor, String>("Specialization"));

        Search.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                List = DBUtilities.getDataDoctors(DoctorName.getText());
                TableView.setItems(List);
            }

        });
        Back.setOnAction(
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