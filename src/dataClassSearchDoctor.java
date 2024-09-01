
public class dataClassSearchDoctor {
    String DoctorName;
    String DoctorEmail;
    String HospitalName;
    String HospitalID;
    String Specialization;

    dataClassSearchDoctor(String hospitalName, String hospitalID, String doctorName, String specialization,
            String doctorEmail) {
        DoctorName = doctorName;
        DoctorEmail = doctorEmail;
        HospitalName = hospitalName;
        HospitalID = hospitalID;
        Specialization = specialization;
    }

    public String getDoctorEmail() {
        return DoctorEmail;
    }

    public String getDoctorName() {
        return DoctorName;
    }

    public String getHospitalID() {
        return HospitalID;
    }

    public String getHospitalName() {
        return HospitalName;
    }

    public String getSpecialization() {
        return Specialization;
    }

    public void setDoctorEmail(String doctorEmail) {
        DoctorEmail = doctorEmail;
    }

    public void setDoctorName(String doctorName) {
        DoctorName = doctorName;
    }

    public void setHospitalID(String hospitalID) {
        HospitalID = hospitalID;
    }

    public void setHospitalName(String hospitalName) {
        HospitalName = hospitalName;
    }

    public void setSpecialization(String specialization) {
        Specialization = specialization;
    }

}