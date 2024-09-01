
public class dataClassPrescription
{
    String DoctorEmail;
    String PatientEmail;
    String Medicine1;
    String Medicine2;
    String Dosage1;
    String Dosage2;

    dataClassPrescription(String DoctorEmail,String PatientEmail,String Medicine1,String Medicine2,String Dosage1,String Dosage2){
        this.DoctorEmail = DoctorEmail;
        this.PatientEmail = PatientEmail;
        this.Medicine1 = Medicine1;
        this.Medicine2 = Medicine2;
        this.Dosage1 = Dosage1;
        this.Dosage2 = Dosage2;
    }

    public String getDoctorEmail() {
        return DoctorEmail;
    }

    public String getDosage1() {
        return Dosage1;
    }

    public String getDosage2() {
        return Dosage2;
    }
    
    public String getMedicine1() {
        return Medicine1;
    }

    public String getMedicine2() {
        return Medicine2;
    }

    public String getPatientEmail() {
        return PatientEmail;
    }
    
    public void setDoctorEmail(String doctorEmail) {
        DoctorEmail = doctorEmail;
    }

    public void setDosage1(String dosage1) {
        Dosage1 = dosage1;
    }

    public void setDosage2(String dosage2) {
        Dosage2 = dosage2;
    }
    public void setMedicine1(String medicine1) {
        Medicine1 = medicine1;
    }
    
    public void setMedicine2(String medicine2) {
        Medicine2 = medicine2;
    }

    public void setPatientEmail(String patientEmail) {
        PatientEmail = patientEmail;
    }


}