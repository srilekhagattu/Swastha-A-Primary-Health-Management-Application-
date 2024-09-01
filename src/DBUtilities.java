
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class DBUtilities {

    public static void createTables() throws SQLException {
        Connection con = null;
        Statement stmt = null;
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/swastha", "root",
                    "MOHAN RAO");
            stmt = con.createStatement();

            stmt.executeUpdate(
                    "create table if not exists patient(name varchar(50), Blood_Group varchar(20), DateOfBirth varchar(20), email varchar(50), password varchar(50), Gender varchar(20), Address varchar(300), Pincode int, Patient_Weight int, Patient_Height decimal(10,2))");

            stmt.executeUpdate(
                    "create table if not exists doctor(Hospital_ID varchar(30), Name varchar(50), Gender varchar(20), DateOfBirth varchar(20), Specialization varchar(50), Email varchar(50), Password varchar(50), Address varchar(200), Pincode int,Fees double)");
            stmt.executeUpdate(
                    "create table if not exists hospital(hospital_name varchar(100), hospital_ID varchar(50), ph_no varchar(15))");
            stmt.executeUpdate(
                    "create table if not exists appointment(appointmentID varchar(50),time varchar(50),date varchar(50),doctorEmail varchar(50) , patientEmail varchar(50))");
            stmt.executeUpdate(
                    "create table if not exists prescription(DoctorEmail varchar(100) , PatientEmail varchar(100) , Medicine1 varchar(100) , Dosage1 varchar(100), Medicine2 varchar(100) , Dosage2 varchar(100))");
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public static void changeScene(ActionEvent event, String fxmlfile) throws IOException {
        Parent root = null;
        FXMLLoader loader = new FXMLLoader(DBUtilities.class.getResource(fxmlfile));
        root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void LoginDoctor(ActionEvent event, String Email, String Password) {
        Connection con = null;
        PreparedStatement ps = null;
        PreparedStatement stmt = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/swastha", "root", "MOHAN RAO");
            ps = con.prepareStatement("select * from doctor where email = ?");
            ps.setString(1, Email);
            rs1 = ps.executeQuery();
            if (rs1.isBeforeFirst()) {
                stmt = con.prepareStatement("select * from doctor where email = ? and password = ?");
                stmt.setString(1, Email);
                stmt.setString(2, Password);
                rs2 = stmt.executeQuery();
                if (rs2.isBeforeFirst()) {
                    changeScene(event, "HomeDoctor.fxml");
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Incorrect email or password");
                    alert.show();
                }

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Please recheck your entered email or password");
                alert.show();
            }
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            if (rs1 != null) {
                try {
                    rs1.close();
                } catch (Exception e) {
                    System.err.println(e);
                }
            }
            if (rs2 != null) {
                try {
                    rs2.close();
                } catch (Exception e) {
                    System.err.println(e);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (Exception e) {
                    System.err.println(e);
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (Exception e) {
                    System.err.println(e);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    System.err.println(e);
                }
            }
        }
    }

    public static void SignUpDoctor(ActionEvent event, Doctor doc) {
        Connection con = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExists = null;
        ResultSet rs = null;

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SWASTHA", "root", "MOHAN RAO");
            psCheckUserExists = con.prepareStatement("select * from doctor where email = ?");
            psCheckUserExists.setString(1, doc.getEmail());
            rs = psCheckUserExists.executeQuery();

            if (rs.isBeforeFirst()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("There is already an account registered with this email");
                alert.show();
            } else {
                psInsert = con.prepareStatement("insert into doctor values(?,?,?,?,?,?,?,?,?,?)");
                psInsert.setString(1, doc.getHospital_ID());
                psInsert.setString(2, doc.getName());
                psInsert.setString(3, doc.getGender());
                psInsert.setString(4, doc.getDob());
                psInsert.setString(5, doc.getSpecialization());
                psInsert.setString(6, doc.getEmail());
                psInsert.setString(7, doc.getPassword());
                psInsert.setString(8, doc.getAddress());
                psInsert.setInt(9, doc.getPincode());
                psInsert.setDouble(10, doc.getFees());
                psInsert.executeUpdate();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Registered Data");
                alert.show();

                changeScene(event, "DoctorLogin.fxml");

                String csvFile = "/Users/saiteja/git/SwasthaPersonal/Swastha/lib/csv/Doctor_CSV.csv";

                CSVWriter cw = new CSVWriter(new FileWriter(csvFile, true));

                String p = Integer.toString(doc.getPincode());
                String f = Double.toString(doc.getFees());

                String Add_Data_To_CSV[] = { doc.getHospital_ID(), doc.getName(), doc.getGender(), doc.getDob(),
                        doc.getSpecialization(), doc.getEmail(), doc.getPassword(), doc.getAddress(), p, f };

                cw.writeNext(Add_Data_To_CSV);

                cw.close();

            }

        }

        catch (Exception e) {
            System.err.println(e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                    System.err.println(e);
                }
            }
            if (psCheckUserExists != null) {
                try {
                    psCheckUserExists.close();
                } catch (Exception e) {
                    System.err.println(e);
                }
            }
            if (psInsert != null) {
                try {
                    psInsert.close();
                } catch (Exception e) {
                    System.err.println(e);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    System.err.println(e);
                }
            }
        }

    }

    public static void SignUpPatient(ActionEvent event, Patient Patient_Object) {

        Connection connection = null;
        PreparedStatement ps = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/SWASTHA", "root", "MOHAN RAO");
            ps = connection.prepareStatement("select * from Patient where email = ?");
            ps.setString(1, Patient_Object.getEmail());

            rs = ps.executeQuery();

            if (rs.isBeforeFirst()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("There is already an account registered with this email");
                alert.show();
            } else {

                String Name, DateOfBirth, Email, Password, Gender, Address, Blood_Group;
                int Pincode, Patient_Weight;
                double Patient_Height;
                Name = Patient_Object.getName();
                Blood_Group = Patient_Object.getBlood_Group();
                DateOfBirth = Patient_Object.getDob();
                Email = Patient_Object.getEmail();
                Password = Patient_Object.getPassword();
                Gender = Patient_Object.getGender();
                Address = Patient_Object.getAddress();
                Pincode = Patient_Object.getPincode();
                Patient_Weight = Patient_Object.getWeight();
                Patient_Height = Patient_Object.getHeight();

                stmt = connection.prepareStatement("insert into patient values(?,?,?,?,?,?,?,?,?,?)");
                stmt.setString(1, Name);
                stmt.setString(2, Blood_Group);
                stmt.setString(3, DateOfBirth);
                stmt.setString(4, Email);
                stmt.setString(5, Password);
                stmt.setString(6, Gender);
                stmt.setString(7, Address);
                stmt.setInt(8, Pincode);
                stmt.setInt(9, Patient_Weight);
                stmt.setDouble(10, Patient_Height);
                stmt.executeUpdate();

                changeScene(event, "PatientLogin.fxml");

                String csvFile = "/Users/saiteja/git/SwasthaPersonal/Swastha/lib/csv/Patient_CSV.csv";

                CSVWriter cw = new CSVWriter(new FileWriter(csvFile, true));

                String p = Integer.toString(Pincode);
                String w = Integer.toString(Patient_Weight);
                String h = Double.toString(Patient_Height);

                String Add_Data_To_CSV[] = { Name, Blood_Group, DateOfBirth, Email, Password, Gender, Address, p, w,
                        h };

                cw.writeNext(Add_Data_To_CSV);

                cw.close();

            }

        } // End try block

        catch (Exception e) {
            System.err.print(e);
        }

        finally {

            if (rs != null) {
                try {
                    rs.close();
                }

                catch (Exception e) {
                    System.err.println(e);
                }

            }

            if (stmt != null) {
                try {
                    stmt.close();
                }

                catch (Exception e) {
                    System.err.println(e);
                }

            }

            if (ps != null) {
                try {
                    ps.close();
                }

                catch (Exception e) {
                    System.err.println(e);
                }

            }

            if (connection != null) {
                try {
                    connection.close();
                }

                catch (Exception e) {
                    System.err.println(e);
                }

            }

        } // End finally

    } // End Sign_Up_Patient Method

    public static void LoginPatient(ActionEvent event, String Email, String Password) {

        Connection connection = null;
        PreparedStatement ps = null;
        PreparedStatement stmt = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;

        try {

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/swastha", "root", "MOHAN RAO");
            ps = connection.prepareStatement("select * from patient where email = ?");
            ps.setString(1, Email);

            rs1 = ps.executeQuery();

            if (rs1.isBeforeFirst()) {
                stmt = connection.prepareStatement("select * from patient where email = ? and password = ?");
                stmt.setString(1, Email);
                stmt.setString(2, Password);
                rs2 = stmt.executeQuery();
                if (rs2.isBeforeFirst()) {
                    changeScene(event, "PatientHome.fxml");
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Incorrect email or password");
                    alert.show();
                }

            }

            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("No account found with entered email , please recheck your entered email");
                alert.show();
            }

        } // End try block

        catch (Exception e) {
            System.err.print(e);
        }

        finally {

            if (rs1 != null) {
                try {
                    rs1.close();
                }

                catch (Exception e) {
                    System.err.println(e);
                }

            }

            if (rs2 != null) {
                try {
                    rs2.close();
                }

                catch (Exception e) {
                    System.err.println(e);
                }

            }

            if (ps != null) {
                try {
                    ps.close();
                }

                catch (Exception e) {
                    System.err.println(e);
                }

            }

            if (stmt != null) {
                try {
                    stmt.close();
                }

                catch (Exception e) {
                    System.err.println(e);
                }

            }

            if (connection != null) {
                try {
                    connection.close();
                }

                catch (Exception e) {
                    System.err.println(e);
                }

            }

        } // End finally

    } // End Login_Patient Class

    public static void CreateAppointment(ActionEvent event, Appointment app) {
        Connection con = null;
        PreparedStatement psInsert = null;
        ResultSet rs = null;

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/swastha", "root", "MOHAN RAO");
            psInsert = con.prepareStatement("insert into appointment values(?,?,?,?,?)");
            psInsert.setString(1, app.getId());
            psInsert.setString(2, app.getTime());
            psInsert.setString(3, app.getDate());
            psInsert.setString(4, app.getDoctor().getEmail());
            psInsert.setString(5, app.getPatient().getEmail());
            changeScene(event, "AppointmentsofPatient.fxml");

        } catch (Exception e) {
            System.err.println(e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                    System.err.println(e);
                }
            }
            if (psInsert != null) {
                try {
                    psInsert.close();
                } catch (Exception e) {
                    System.err.println(e);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    System.err.println(e);
                }
            }
        }

    }

    public static void Cancel_Appointment(ActionEvent event, String Date, String DoctorEmail, String Time) {
        Connection con = null;
        PreparedStatement psDelete = null;
        PreparedStatement psCheckAppointmentExists = null;
        ResultSet rs = null;

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/swastha", "root", "MOHAN RAO");
            psCheckAppointmentExists = con
                    .prepareStatement("select * from appointment where date = ? and doctorEmail = ? and time = ?");
            psCheckAppointmentExists.setString(1, Date);
            psCheckAppointmentExists.setString(2, DoctorEmail);
            psCheckAppointmentExists.setString(3, Time);
            rs = psCheckAppointmentExists.executeQuery();

            if (rs.isBeforeFirst()) {
                psDelete = con
                        .prepareStatement("delete from appointment where date = ? and doctorEmail = ? and time = ?");
                psDelete.setString(1, Date);
                psDelete.setString(2, DoctorEmail);
                psDelete.setString(3, Time);
                psDelete.executeUpdate();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Cancelled Appointment Succesfully");
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("No Appointment found to delete with given Appointment ID ");
                alert.show();
            }

        } catch (Exception e) {
            System.err.println(e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                    System.err.println(e);
                }
            }
            if (psCheckAppointmentExists != null) {
                try {
                    psCheckAppointmentExists.close();
                } catch (Exception e) {
                    System.err.println(e);
                }
            }
            if (psDelete != null) {
                try {
                    psDelete.close();
                } catch (Exception e) {
                    System.err.println(e);
                }
            }

            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    System.err.println(e);
                }
            }
        }

    }

    public static void Search_Appointment(ActionEvent event, Appointment app) {
        Connection con = null;
        PreparedStatement psCheckAppointmentExists = null;
        ResultSet rs = null;

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/swastha", "root", "MOHAN RAO");
            psCheckAppointmentExists = con.prepareStatement("select * from appointment where date= ?");
            psCheckAppointmentExists.setString(1, app.getDate());
            rs = psCheckAppointmentExists.executeQuery();

            if (rs.isBeforeFirst()) {
                changeScene(event, "AppointmentsofPatient.fxml");
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("No Appointments found");
                alert.show();
            }

        } catch (Exception e) {
            System.err.println(e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                    System.err.println(e);
                }
            }
            if (psCheckAppointmentExists != null) {
                try {
                    psCheckAppointmentExists.close();
                } catch (Exception e) {
                    System.err.println(e);
                }
            }

            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    System.err.println(e);
                }
            }
        }

    }

    public static void searchHospital(ActionEvent event, Hospital hospital) {

        Connection connection = null;
        PreparedStatement psCheckHospitalStatus = null;
        ResultSet resultset = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/swastha", "root", "MOHAN RAO");
            psCheckHospitalStatus = connection.prepareStatement("select * from hospital where hospital_name = ?");
            resultset = psCheckHospitalStatus.executeQuery();

            java.sql.ResultSetMetaData rsmd = resultset.getMetaData();
            int columnCount = rsmd.getColumnCount();

            ArrayList<String> hospitalResultList = new ArrayList<>(columnCount);
            while (resultset.next()) {
                int i = 1;
                while (i <= columnCount) {
                    hospitalResultList.add(resultset.getString(i++));
                }

                if (resultset.isBeforeFirst()) {
                    changeScene(event, "HospitalList.fxml");
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("No Hospitals found");
                    alert.show();
                }
            }
        }

        catch (Exception e) {
            System.err.println(e);
        }

        finally {
            if (resultset != null) {
                try {
                    resultset.close();
                }

                catch (Exception e) {
                    System.err.println(e);
                }
            }
            if (psCheckHospitalStatus != null) {
                try {
                    psCheckHospitalStatus.close();
                }

                catch (Exception e) {
                    System.err.println(e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    System.err.println(e);
                }
            }
        }
    }

    public static void searchDoctor(ActionEvent event, Doctor doctor) {

        Connection connection = null;
        PreparedStatement psCheckDoctorStatus = null;
        ResultSet resultset = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/swastha", "root", "MOHAN RAO");
            psCheckDoctorStatus = connection.prepareStatement("select * from doctor where name = ?");
            resultset = psCheckDoctorStatus.executeQuery();
            java.sql.ResultSetMetaData rsmd = resultset.getMetaData();
            int columnCount = rsmd.getColumnCount();

            ArrayList<String> doctorResultList = new ArrayList<>(columnCount);
            while (resultset.next()) {
                int i = 1;
                while (i <= columnCount) {
                    doctorResultList.add(resultset.getString(i++));
                }

                if (resultset.isBeforeFirst()) {
                    changeScene(event, "DoctorList.fxml");
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("No Doctors found");
                    alert.show();
                }
            }
        }

        catch (Exception e) {
            System.err.println(e);
        }

        finally {
            if (resultset != null) {
                try {
                    resultset.close();
                }

                catch (Exception e) {
                    System.err.println(e);
                }
            }

            if (psCheckDoctorStatus != null) {
                try {
                    psCheckDoctorStatus.close();
                }

                catch (Exception e) {
                    System.err.println(e);
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                }

                catch (Exception e) {
                    System.err.println(e);
                }
            }
        }
    }

    public static void editDoctor(ActionEvent event, String email, String password, String Attribute,
            String change_to) {
        Connection con = null;
        PreparedStatement ps = null;
        PreparedStatement stmt = null;
        PreparedStatement psupdate = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;

        try {

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SWASTHA", "root", "MOHAN RAO");
            ps = con.prepareStatement("select * from doctor where email = ?");
            ps.setString(1, email);
            rs1 = ps.executeQuery();

            if (rs1.isBeforeFirst()) {

                stmt = con.prepareStatement("select * from doctor where email = ? and password = ?");
                stmt.setString(1, email);
                stmt.setString(2, password);
                rs2 = stmt.executeQuery();

                if (rs2.isBeforeFirst()) {

                    if (Attribute.equals("Hospital_ID")) {
                        psupdate = con
                                .prepareStatement("update doctor set Hospital_ID = ? where email = ? and password = ?");
                    } else if (Attribute.equals("Address")) {
                        psupdate = con
                                .prepareStatement("update doctor set Address = ? where email = ? and password = ?");
                    } else if (Attribute.equals("Specialization")) {
                        psupdate = con.prepareStatement(
                                "update doctor set Specialization = ? where email = ? and password = ?");
                    } else if (Attribute.equals("Pincode")) {
                        psupdate = con
                                .prepareStatement("update doctor set Pincode = ? where email = ? and password = ?");
                    }

                    psupdate.setString(1, change_to);
                    psupdate.setString(2, email);
                    psupdate.setString(3, password);

                    psupdate.executeUpdate();
                    System.out.print(email + password + Attribute);
                    Parent root = null;
                    FXMLLoader loader = new FXMLLoader(DBUtilities.class.getResource("/HomeDoctor.fxml"));
                    root = loader.load();
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(new Scene(root));
                    stage.show();

                    BufferedReader bufferedReader = new BufferedReader(new FileReader(
                            "/Users/saiteja/git/SwasthaPersonal/Swastha/lib/csv/Doctor_CSV.csv"));

                    int count = 0;
                    String data = "";

                    while ((data = bufferedReader.readLine()) != null) {
                        count++;
                    }

                    count--;

                    int index = 0;

                    if (Attribute.equals("Hospital_ID"))
                        index = 0;

                    else if (Attribute.equals("Specialization"))
                        index = 4;

                    else if (Attribute.equals("Address"))
                        index = 7;

                    else if (Attribute.equals("Pincode"))
                        index = 8;

                    CSVReader csv_read = new CSVReader(new FileReader(new File(
                            "/Users/saiteja/git/SwasthaPersonal/Swastha/lib/csv/Doctor_CSV.csv")));

                    List<String[]> All_Doctor_Data = csv_read.readAll();

                    for (int i = 1; i <= count; i++) {
                        if ((All_Doctor_Data.get(i)[5].equals(email))
                                && ((All_Doctor_Data.get(i)[6]).equals(password))) { // System.out.print(i);
                            All_Doctor_Data.get(i)[index] = change_to;

                        }

                    }

                    CSVWriter csv_write = new CSVWriter(new FileWriter(new File(
                            "/Users/saiteja/git/SwasthaPersonal/Swastha/lib/csv/Doctor_CSV.csv")));
                    csv_write.writeAll(All_Doctor_Data);
                    csv_write.flush();

                    bufferedReader.close();

                }

                else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Wrong Password!");
                    alert.show();
                }

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("No account found with entered email , please recheck your entered email");
                alert.show();
            }

        } catch (Exception e) {
            System.err.println(e);
        } finally {
            if (rs1 != null) {
                try {
                    rs1.close();
                }

                catch (Exception e) {
                    System.err.println(e);
                }
            }
            if (rs2 != null) {
                try {
                    rs2.close();
                }

                catch (Exception e) {
                    System.err.println(e);
                }
            }
            if (psupdate != null) {
                try {
                    psupdate.close();
                }

                catch (Exception e) {
                    System.err.println(e);
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                }

                catch (Exception e) {
                    System.err.println(e);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                }

                catch (Exception e) {
                    System.err.println(e);
                }
            }
            if (con != null) {
                try {
                    con.close();
                }

                catch (Exception e) {
                    System.err.println(e);
                }
            }
        }
    }

    public static void editPatient(ActionEvent event, String email, String password, String Attribute,
            String change_to) {
        Connection con = null;
        PreparedStatement ps = null;
        PreparedStatement stmt = null;
        PreparedStatement psupdate = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;

        try {

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SWASTHA", "root", "MOHAN RAO");
            ps = con.prepareStatement("select * from patient where email = ?");
            ps.setString(1, email);
            rs1 = ps.executeQuery();

            if (rs1.isBeforeFirst()) {

                stmt = con.prepareStatement("select * from patient where email = ? and password = ?");
                stmt.setString(1, email);
                stmt.setString(2, password);
                rs2 = stmt.executeQuery();

                if (rs2.isBeforeFirst()) {

                    if (Attribute.equals("Address")) {
                        psupdate = con
                                .prepareStatement("update patient set Address = ? where email = ? and password = ?");
                                
                                
                    } else if (Attribute.equals("Patient_Weight")) {
                        psupdate = con.prepareStatement(
                                "update patient set Patient_Weight = ? where email = ? and password = ?");
                                
                    } else if (Attribute.equals("Pincode")) {
                        psupdate = con
                                .prepareStatement("update patient set Pincode = ? where email = ? and password = ?");
                                
                    }

                    psupdate.setString(1, change_to);
                    psupdate.setString(2, email);
                    psupdate.setString(3, password);

                    psupdate.executeUpdate();

                    changeScene(event, "/PatientHome.fxml");

                    BufferedReader bufferedReader = new BufferedReader(new FileReader(
                            "/Users/saiteja/git/SwasthaPersonal/Swastha/lib/csv/Patient_CSV.csv"));

                    int count = 0;
                    String data = "";

                    while ((data = bufferedReader.readLine()) != null) {
                        count++;
                    }

                    count--;

                    int index = 0;

                    if (Attribute.equals("Address"))
                        index = 6;

                    else if (Attribute.equals("Patient_Weight"))
                        index = 8;

                    else if (Attribute.equals("Pincode"))
                        index = 7;

                    CSVReader csv_read = new CSVReader(new FileReader(new File(
                            "/Users/saiteja/git/SwasthaPersonal/Swastha/lib/csv/Patient_CSV.csv")));

                    List<String[]> All_Patient_Data = csv_read.readAll();

                    for (int i = 1; i <= count; i++) {
                        if ((All_Patient_Data.get(i)[3].equals(email))
                                && ((All_Patient_Data.get(i)[4]).equals(password))) { // System.out.print(i);
                            if (index == 6)
                                All_Patient_Data.get(i)[index] = change_to;
                            else if (index == 8)
                                All_Patient_Data.get(i)[index] = change_to;
                            else if (index == 7)
                                All_Patient_Data.get(i)[index] = change_to;
                        }

                    }

                    CSVWriter csv_write = new CSVWriter(new FileWriter(new File(
                            "/Users/saiteja/git/SwasthaPersonal/Swastha/lib/csv/Patient_CSV.csv")));
                    csv_write.writeAll(All_Patient_Data);
                    csv_write.flush();

                    bufferedReader.close();

                }

                else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Wrong Password!");
                    alert.show();
                }

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("No account found with entered email , please recheck your entered email");
                alert.show();
            }

        } catch (Exception e) {
            System.err.println(e);
        } finally {
            if (rs1 != null) {
                try {
                    rs1.close();
                }

                catch (Exception e) {
                    System.err.println(e);
                }
            }
            if (rs2 != null) {
                try {
                    rs2.close();
                }

                catch (Exception e) {
                    System.err.println(e);
                }
            }
            if (psupdate != null) {
                try {
                    psupdate.close();
                }

                catch (Exception e) {
                    System.err.println(e);
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                }

                catch (Exception e) {
                    System.err.println(e);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                }

                catch (Exception e) {
                    System.err.println(e);
                }
            }
            if (con != null) {
                try {
                    con.close();
                }

                catch (Exception e) {
                    System.err.println(e);
                }
            }
        }
    }

    public static void createPrescription(ActionEvent event, String DoctorEmail, String PatienEmail, String Medicine1,
            String Dosage1, String Medicine2, String Dosage2) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/swastha", "root", "MOHAN RAO");
            ps = con.prepareStatement("insert into prescription values(?,?,?,?,?,?)");
            ps.setString(1, DoctorEmail);
            ps.setString(2, PatienEmail);
            ps.setString(3, Medicine1);
            ps.setString(4, Dosage1);
            ps.setString(5, Medicine2);
            ps.setString(6, Dosage2);
            ps.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Prescription Created");
            alert.show();
            changeScene(event, "HomeDoctor.fxml");
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (Exception e) {
                    System.err.println(e);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    System.err.println(e);
                }
            }
        }
    }

    public static ObservableList<dataClassAppt> bookAppointmentresult(String DoctorName, String HospitalName) {
        ObservableList<dataClassAppt> list = FXCollections.observableArrayList();
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/swastha", "root", "MOHAN RAO");
            PreparedStatement ps = null;
            ps = con.prepareStatement(
                    "select hospital_name,Name,Email,Fees from doctor natural join hospital where Name = ? and hospital_name = ?");
            ps.setString(1, DoctorName);
            ps.setString(2, HospitalName);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new dataClassAppt(rs.getString("Name"), rs.getString("Email"), rs.getString("hospital_name"),
                        rs.getDouble("Fees")));

            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return list;
    }

    public static ObservableList<dataClassPatient> PatientAppointmentResult(String Email) {
        ResultSet rs;
        ObservableList<dataClassPatient> list = FXCollections.observableArrayList();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/swastha", "root", "MOHAN RAO");
            PreparedStatement st = null;
            st = con.prepareStatement(
                    "select name,Hospital_ID,Specialization,date,time,Fees from doctor natural join appointment where patientemail = ?");
            st.setString(1, Email);
            rs = st.executeQuery();

            while (rs.next()) {

                list.add(new dataClassPatient(rs.getString("name"), rs.getString("Specialization"),
                        rs.getString("date"), rs.getString("time"), rs.getString("Hospital_ID"), rs.getDouble("Fees")));

            }
        } catch (Exception e) {
            System.err.println(e);
        }

        return list;
    }

    public static ObservableList<dataClassDoctor> DoctorAppointmentResult(String email) {
        ResultSet rs;
        ObservableList<dataClassDoctor> list = FXCollections.observableArrayList();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/swastha", "root", "MOHAN RAO");
            PreparedStatement ps = null;
            ps = con.prepareStatement(
                    "select name , date , time from patient natural join appointment where doctorEmail = ?");
            ps.setString(1, email);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new dataClassDoctor(rs.getString("name"), rs.getString("date"), rs.getString("time")));
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return list;
    }

    public static ObservableList<dataClassCancelAppointment> CancelAppointmentResult(String email) {
        ResultSet rs;
        ObservableList<dataClassCancelAppointment> list = FXCollections.observableArrayList();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/swastha", "root", "MOHAN RAO");
            PreparedStatement ps = null;
            ps = con.prepareStatement("select * from appointment where patientEmail = ?");
            ps.setString(1, email);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new dataClassCancelAppointment(rs.getString("doctorEmail"), rs.getString("patientEmail"),
                        rs.getString("appointmentID"), rs.getString("date"), rs.getString("time")));
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return list;

    }

    public static ObservableList<dataClassPrescription> ViewPrescriptionsResult(String DoctorEmail,
            String PatientEmail) {
        ResultSet rs;
        ObservableList<dataClassPrescription> list = FXCollections.observableArrayList();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/swastha", "root", "MOHAN RAO");
            PreparedStatement ps = null;
            ps = con.prepareStatement("select * from prescription where DoctorEmail = ? and PatientEmail = ?");
            ps.setString(1, DoctorEmail);
            ps.setString(2, PatientEmail);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new dataClassPrescription(rs.getString("DoctorEmail"), rs.getString("PatientEmail"),
                        rs.getString("Medicine1"), rs.getString("Medicine2"), rs.getString("Dosage1"),
                        rs.getString("Dosage2")));
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return list;
    }

    public static ObservableList<dataClassSearchHospital> getDataHospitals(String HospitalName) {
        
        ObservableList<dataClassSearchHospital> List = FXCollections.observableArrayList();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/swastha", "root","MOHAN RAO");
            PreparedStatement ps = conn.prepareStatement("select hospital_name,hospital_ID,ph_no from Hospital where hospital_name = ?");
            ps.setString(1, HospitalName);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                dataClassSearchHospital data = new dataClassSearchHospital(rs.getString(1), rs.getString(2),
                        rs.getString(3));
                List.add(data);
            }

        } catch (Exception exception) {
            System.err.println(exception);
        }
        return List;
    }

    public static ObservableList<dataClassSearchDoctor> getDataDoctors(String DoctorName) {
        
        ObservableList<dataClassSearchDoctor> List = FXCollections.observableArrayList();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/swastha", "root","MOHAN RAO");
            PreparedStatement ps = conn
                    .prepareStatement(
                            "select name, email,hospital_name, hospital_id, specialization from doctor natural join hospital where name = ?");
            ps.setString(1, DoctorName);
            ResultSet rs = ps.executeQuery();
            int i = 0;
            while (rs.next()) {
                i = 1;
                dataClassSearchDoctor data = new dataClassSearchDoctor(rs.getString(3), rs.getString(4),
                        rs.getString(1), rs.getString(5), rs.getString(2));
                List.add(data);
            }
            if (i == 0) {
                System.out.println("No doctor with given name.");
            }

        } catch (Exception exception) {
            System.err.println(exception);
        }
        return List;
    }


    public static void Load_Patient_CSV() {

        try {

            String csvFilePath = "/Users/saiteja/git/SwasthaPersonal/Swastha/lib/csv/Patient_CSV.csv";

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Swastha", "root",
                    "MOHAN RAO");
            connection.setAutoCommit(false);

            Statement stmt = connection.createStatement();
            stmt.execute("drop table if exists patient");

            stmt.execute(
                    "create table if not exists patient(Name varchar(50), Blood_Group varchar(20), DateOfBirth varchar(20), Email varchar(50), Password varchar(50), Gender varchar(20), Address varchar(300), Pincode int, Patient_Weight int, Patient_Height decimal(10,2))");
            stmt.execute("truncate table patient");

            PreparedStatement statement = connection
                    .prepareStatement("insert into Patient values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            BufferedReader lineReader = new BufferedReader(new FileReader(csvFilePath));

            String lineText = null;
            int count = 0, batchSize = 100;
            lineReader.readLine();

            while ((lineText = lineReader.readLine()) != null) {

                String[] data = lineText.split(",");

                String name = data[0];
                String blood_group = data[1];
                String dob = data[2];
                String email = data[3];
                String password = data[4];
                String gender = data[5];

                String address = data[6];

                int pincode = Integer.parseInt(data[7]);
                int weight = Integer.parseInt(data[8]);
                double height = Double.parseDouble(data[9]);

                statement.setString(1, name);
                statement.setString(2, blood_group);
                statement.setString(3, dob);
                statement.setString(4, email);
                statement.setString(5, password);
                statement.setString(6, gender);
                statement.setString(7, address);
                statement.setInt(8, pincode);
                statement.setInt(9, weight);
                statement.setDouble(10, height);

                statement.addBatch();

                if (count % batchSize == 0)
                    statement.executeBatch();

            }

            lineReader.close();

            statement.executeBatch();
            connection.commit();
            connection.close();

            System.out.print("\nInsertion of Patient Data into Database Server is Successfully Implemented\n");

        }

        catch (Exception e) {
            System.out.println("\nAn Exception has Occurred\n");
            System.err.println(e);
        }

    } // End Load_Patient_CSV Class

    public static void Load_Doctor_CSV() {

        try {

            String csvFilePath = "/Users/saiteja/git/SwasthaPersonal/Swastha/lib/csv/Doctor_CSV.csv";

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Swastha", "root",
                    "MOHAN RAO");
            connection.setAutoCommit(false);

            Statement stmt = connection.createStatement();
            stmt.execute("drop table if exists doctor");
            stmt.execute(
                    "create table if not exists doctor(Hospital_ID varchar(30), Name varchar(50), Gender varchar(20), DateOfBirth varchar(20), Specialization varchar(50), Email varchar(50), Password varchar(50), Address varchar(200), Pincode int, Fees decimal(10,3))");
            stmt.execute("truncate table doctor");

            PreparedStatement statement = connection
                    .prepareStatement("insert into doctor values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            BufferedReader lineReader = new BufferedReader(new FileReader(csvFilePath));

            String lineText = null;
            int count = 0, batchSize = 100;
            lineReader.readLine();

            while ((lineText = lineReader.readLine()) != null) {

                String[] data = lineText.split(",");

                String ID = data[0];
                String name = data[1];
                String gender = data[2];
                String dob = data[3];
                String specialization = data[4];
                String email = data[5];
                String password = data[6];

                String address = data[7];

                int pincode = Integer.parseInt(data[8]);
                double Fees = Double.parseDouble(data[9]);

                statement.setString(1, ID);
                statement.setString(2, name);
                statement.setString(3, gender);
                statement.setString(4, dob);
                statement.setString(5, specialization);
                statement.setString(6, email);
                statement.setString(7, password);
                statement.setString(8, address);
                statement.setInt(9, pincode);
                statement.setDouble(10, Fees);

                statement.addBatch();

                if (count % batchSize == 0)
                    statement.executeBatch();

            }

            lineReader.close();

            statement.executeBatch();
            connection.commit();
            connection.close();

            System.out.print("\nInsertion of Doctor Data into Database Server is Successfully Implemented\n");

        }

        catch (Exception e) {
            System.out.println("\nAn Exception has Occurred\n");
            System.err.println(e);
        }

    } // End Load_Doctor_CSV Class

    public static void Hospital_CSV_To_Database() {
        try {

            String csvFilePath = "/Users/saiteja/git/SwasthaPersonal/Swastha/lib/csv/Hospital_CSV.csv";

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Swastha", "root",
                    "MOHAN RAO");
            connection.setAutoCommit(false);

            Statement stmt = connection.createStatement();
            stmt.execute("drop table if exists Hospital");
            stmt.execute(
                    "create table if not exists Hospital(hospital_name varchar(50), hospital_ID varchar(50), ph_no varchar(15))");
            stmt.execute("truncate table Hospital");

            PreparedStatement statement = connection.prepareStatement("insert into Hospital values (?, ?, ?)");
            BufferedReader lineReader = new BufferedReader(new FileReader(csvFilePath));

            String lineText = null;
            int count = 0, batchSize = 100;
            lineReader.readLine();

            while ((lineText = lineReader.readLine()) != null) {

                String[] data = lineText.split(",");

                String name = data[0];
                String ID = data[1];
                String ph_no = data[2];

                statement.setString(1, name);
                statement.setString(2, ID);
                statement.setString(3, ph_no);

                statement.addBatch();

                if (count % batchSize == 0)
                    statement.executeBatch();

            }

            lineReader.close();

            statement.executeBatch();
            connection.commit();
            connection.close();

            System.out.print("\nInsertion of Hospital Data into Database Server is Successfully Implemented\n");

        }

        catch (Exception e) {
            System.out.println("\nAn Exception has Occurred\n");
            System.err.println(e);
        }

    }

    public static void Appointment_CSV_To_Database() {
        try {

            String csvFilePath = "/Users/saiteja/git/SwasthaPersonal/Swastha/lib/csv/Appointment_CSV.csv";

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Swastha", "root",
                    "MOHAN RAO");
            connection.setAutoCommit(false);

            Statement stmt = connection.createStatement();
            stmt.execute("drop table if exists Appointment");
            stmt.execute(
                    "create table if not exists appointment(appointmentID varchar(50),time varchar(50),date varchar(50),doctorEmail varchar(50) , patientEmail varchar(50))");
            stmt.execute("truncate table Appointment");

            PreparedStatement statement = connection.prepareStatement("insert into Appointment values (?, ?, ?, ?, ?)");
            BufferedReader lineReader = new BufferedReader(new FileReader(csvFilePath));

            String lineText = null;
            int count = 0, batchSize = 100;
            lineReader.readLine();

            while ((lineText = lineReader.readLine()) != null) {

                String[] data = lineText.split(",");

                String ID = data[0];
                String time = data[1];
                String date = data[2];
                String doctor_email = data[3];
                String patient_email = data[4];

                statement.setString(1, ID);
                statement.setString(2, time);
                statement.setString(3, date);
                statement.setString(4, doctor_email);
                statement.setString(5, patient_email);

                statement.addBatch();

                if (count % batchSize == 0)
                    statement.executeBatch();

            }

            lineReader.close();

            statement.executeBatch();
            connection.commit();
            connection.close();

            System.out.print("\nInsertion of Appointment Data into Database Server is Successfully Implemented\n");

        }

        catch (Exception e) {
            System.out.println("\nAn Exception has Occurred\n");
            System.err.println(e);
        }

    }

    public static void Load_Prescription_CSV() {

        try {

            String csvFilePath = "/Users/saiteja/git/SwasthaPersonal/Swastha/lib/csv/Prescription_CSV.csv";

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/SWASTHA", "root",
                    "MOHAN RAO");
            connection.setAutoCommit(false);

            Statement stmt = connection.createStatement();
            stmt.execute("drop table if exists prescription");
            stmt.execute(
                    "create table if not exists prescription(DoctorEmail varchar(100) , PatientEmail varchar(100) , Medicine1 varchar(100) , Dosage1 varchar(100), Medicine2 varchar(100) , Dosage2 varchar(100))");
            stmt.execute("truncate table prescription");

            PreparedStatement statement = connection
                    .prepareStatement("insert into prescription values (?, ?, ?, ?, ?, ?)");
            BufferedReader lineReader = new BufferedReader(new FileReader(csvFilePath));

            String lineText = null;
            int count = 0, batchSize = 20;
            lineReader.readLine();

            while ((lineText = lineReader.readLine()) != null) {

                String[] data = lineText.split(",");

                String E1 = data[0];
                String E2 = data[1];
                String Med1 = data[2];
                String D1 = data[3];
                String Med2 = data[4];
                String D2 = data[5];

                statement.setString(1, E1);
                statement.setString(2, E2);
                statement.setString(3, Med1);
                statement.setString(4, D1);
                statement.setString(5, Med2);
                statement.setString(6, D2);

                statement.addBatch();

                if (count % batchSize == 0)
                    statement.executeBatch();

            }

            lineReader.close();

            statement.executeBatch();
            connection.commit();
            connection.close();

            System.out.print("\nInsertion of Prescription Data into Database Server is Successfully Implemented\n");

        }

        catch (Exception e) {
            System.out.println("\nAn Exception has Occurred\n");
            System.err.println(e);
        }

    } // End Load_Prescription_CSV Class

}
