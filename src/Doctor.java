
import java.util.ArrayList;



class Doctor extends Person {

    public Doctor(String name, String email, String password, String dob, String gender, String address, int pincode) {
        super(name, email, password, dob, gender, address, pincode);
    }

    protected String Specialization;
    protected String Hospital_ID;
    protected double Fees;
    ArrayList<Appointment> Patient_list = new ArrayList<Appointment>();
    protected int Patient_index = 1;
    protected Patient p;

    public void setHospital_ID(String Hospital_ID) {
        this.Hospital_ID = Hospital_ID;
    }

    public String getHospital_ID() {
        return Hospital_ID;
    }

    public void setSpecialization(String Specialization) {
        this.Specialization = Specialization;
    }

    public String getSpecialization() {
        return Specialization;
    }

    public void setFees(double fees) {
        this.Fees = fees;
    }

    public double getFees() {
        return Fees;
    }

    public int addPatient(Patient p, String time, String date) {
        Patient_list.add(new Appointment(p, time, date));
        return Patient_index++;
    }

    public void removePatient(int index) {
        Patient_list.remove(index);
    }

    @Override
    public int login(String email, String pass) {
        if (Email.equals(email)) {
            if (Password.equals(pass)) {
                return 1;
            } else
                return 0;
        } else
            return 0;
    }

}