
public class dataClassPatient {
    String Date;
    String Time;
    String DoctorName;
    double DoctorFees;
    String DoctorHospital;
    String DoctorSpecialization;

    dataClassPatient(String doctorName, String doctorSpecialization, String date, String time,
            String doctorHospital, double fees) {

        this.Date = date;
        this.Time = time;
        this.DoctorName = doctorName;
        this.DoctorHospital = doctorHospital;
        this.DoctorSpecialization = doctorSpecialization;
        this.DoctorFees = fees;

    }

    public void setDate(String date) {
        Date = date;
    }

    public void setDoctorFees(double doctorFees) {
        DoctorFees = doctorFees;
    }

    public void setDoctorHospital(String doctorHospital) {
        DoctorHospital = doctorHospital;
    }

    public void setDoctorName(String doctorName) {
        DoctorName = doctorName;
    }

    public void setDoctorSpecialization(String doctorSpecialization) {
        DoctorSpecialization = doctorSpecialization;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getDate() {
        return Date;
    }

    public double getDoctorFees() {
        return DoctorFees;
    }

    public String getDoctorHospital() {
        return DoctorHospital;
    }

    public String getDoctorName() {
        return DoctorName;
    }

    public String getDoctorSpecialization() {
        return DoctorSpecialization;
    }

    public String getTime() {
        return Time;
    }

}