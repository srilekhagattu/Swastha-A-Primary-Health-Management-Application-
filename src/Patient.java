
import java.util.ArrayList;



public class Patient extends Person {

    public Patient(String name, String email, String password, String dob, String gender, String address, int pincode) {
        super(name, email, password, dob, gender, address, pincode);
    }

    protected String Blood_Group;
    protected String Patient_ID;
    static ArrayList<Appointment> Patient_Appointments = new ArrayList<Appointment>();
    protected int weight;
    protected double height;

    public void setBlood_Group(String Blood_Group) {
        this.Blood_Group = Blood_Group;
    }

    public String getBlood_Group() {
        return Blood_Group;
    }

    public void cancelAppointment(int ID) {
        Patient_Appointments.remove(ID--);
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getHeight() {
        return height;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
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