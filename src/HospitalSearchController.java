
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

public class HospitalSearchController implements Initializable {

    @FXML
    private Button BackButton;

    @FXML
    private TableColumn<dataClassSearchHospital, String> HospitalIDColumn;

    @FXML
    private TableColumn<dataClassSearchHospital, String> HospitalNameColumn;

    @FXML
    private TextField HospitalNameField;

    @FXML
    private TableColumn<dataClassSearchHospital, String> PhoneNumberColumn;

    @FXML
    private Button Search;

    @FXML
    private TableView<dataClassSearchHospital> TableView;

    ObservableList<dataClassSearchHospital> List;

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        HospitalNameColumn
                .setCellValueFactory(new PropertyValueFactory<dataClassSearchHospital, String>("HospitalName"));
        PhoneNumberColumn
                .setCellValueFactory(new PropertyValueFactory<dataClassSearchHospital, String>("PhoneNumber"));
        HospitalIDColumn
                .setCellValueFactory(new PropertyValueFactory<dataClassSearchHospital, String>("HospitalID"));

        Search.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                List = DBUtilities.getDataHospitals(HospitalNameField.getText());
                TableView.setItems(List);
            }

        });

        BackButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                try {
                    DBUtilities.changeScene(arg0, "PatientHome.fxml");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                
            }
            
        });

    }
    
    }

