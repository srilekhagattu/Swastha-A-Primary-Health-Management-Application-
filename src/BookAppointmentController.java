
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class BookAppointmentController implements Initializable {

    @FXML
    private Button BackButton;

    @FXML
    private Button BookButton;

    @FXML
    private DatePicker DateField;

    @FXML
    private TableColumn<dataClassAppt, String> DoctorEmailColumn;

    @FXML
    private TextField DoctorEmailField;

    @FXML
    private TableColumn<dataClassAppt, String> DoctorNameColumn;

    @FXML
    private TextField DoctorNameField;

    @FXML
    private TableColumn<dataClassAppt, Double> FeesColumn;

    @FXML
    private TableColumn<dataClassAppt, String> HospitalNameColumn;

    @FXML
    private TextField HospitalNameField;

    @FXML
    private Button SearchButton;

    @FXML
    private TableView<dataClassAppt> TableView;

    @FXML
    private TextField TimeField;

    @FXML
    private TextField PatientEmailField;

    ObservableList<dataClassAppt> listM;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        DoctorEmailColumn.setCellValueFactory(new PropertyValueFactory<dataClassAppt, String>("DoctorEmail"));
        DoctorNameColumn.setCellValueFactory(new PropertyValueFactory<dataClassAppt, String>("DoctorName"));
        HospitalNameColumn.setCellValueFactory(new PropertyValueFactory<dataClassAppt, String>("HospitalName"));
        FeesColumn.setCellValueFactory(new PropertyValueFactory<dataClassAppt, Double>("Fees"));

        BookButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {

                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/swastha", "root",
                            "MOHAN RAO");
                    PreparedStatement stmt = con.prepareStatement("insert into appointment values(?,?,?,?,?)");
                    String uuid = UUID.randomUUID().toString();
                    stmt.setString(1, uuid);
                    stmt.setString(2, TimeField.getText());
                    stmt.setString(3, DateField.getValue().toString());
                    stmt.setString(4, DoctorEmailField.getText());
                    stmt.setString(5, PatientEmailField.getText());
                    stmt.executeUpdate();
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Appointment Booked Sucessfully");
                    alert.show();

                } catch (Exception e) {
                    System.err.println(e);
                }
            }

        });

        SearchButton.setOnAction(
                new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
                        listM = DBUtilities.bookAppointmentresult(DoctorNameField.getText(), HospitalNameField.getText());
                        TableView.getItems().addAll(listM);
                    }

                });

        BackButton.setOnAction(
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