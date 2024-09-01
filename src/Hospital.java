
import java.util.*;

public class Hospital {

    protected String Hospital_ID;
    protected String Hospital_Name;
    protected String Ph_no;
    protected ArrayList<Doctor> doctors = new ArrayList<>();
    protected String Address;
    protected int Pincode;

    Hospital(String hospital_name, String ph_no, String address, int pincode) {
        Hospital_Name = hospital_name;
        Ph_no = ph_no;
        Address = address;
        Pincode = pincode;
        Hospital_ID = hospital_name + ph_no;
    }

    public String getHospital_ID() {
        return Hospital_ID;
    }

    public String getHospitalName() {
        return Hospital_Name;
    }

    public void setHospitalName(String hospital_Name) {
        Hospital_Name = hospital_Name;
    }

    public int getPincode() {
        return Pincode;
    }

    public void setPincode(int pincode) {
        Pincode = pincode;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public ArrayList<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(Doctor doctor) {
        doctors.add(doctor);
    }

    public String getPh_no() {
        return Ph_no;
    }

    public void setPh_no(String ph_no) {
        this.Ph_no = ph_no;
    }
}