
public class dataClassAppt {
    String DoctorName;
    String DoctorEmail;
    String HospitalName;
    Double Fees;

    dataClassAppt(String doctorName, String doctorEmail, String hospitalName, Double fees) {
        this.DoctorEmail = doctorEmail;
        this.DoctorName = doctorName;
        this.HospitalName = hospitalName;
        this.Fees = fees;
    }

    public void setDoctorEmail(String doctorEmail) {
        DoctorEmail = doctorEmail;
    }

    public void setDoctorName(String doctorName) {
        DoctorName = doctorName;
    }

    public void setFees(Double fees) {
        Fees = fees;
    }

    public void setHospitalName(String hospitalName) {
        HospitalName = hospitalName;
    }

    public String getDoctorName()
    {
        return DoctorName;
    }

    public String getDoctorEmail()
    {
        return DoctorEmail;
    }

    public String getHospitalName()
    {
        return HospitalName;
    }

    public double getFees()
    {
        return Fees;
    }

}