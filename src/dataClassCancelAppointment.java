
public class dataClassCancelAppointment {
    private String DoctorEmail;
    private String PatientEmail;
    private String AppointmentID;
    private String Date;
    private String Time;

    dataClassCancelAppointment(String DoctorEmail,String PatientEmail,String AppointmentID,String Date,String Time)
    {
        this.AppointmentID = AppointmentID;
        this.Date = Date;
        this.DoctorEmail = DoctorEmail;
        this.PatientEmail = PatientEmail;
        this.Time = Time;
    }

    public void setAppointmentID(String appointmentID) {
        AppointmentID = appointmentID;
    }

    public void setDate(String date) {
        Date = date;
    }

    public void setDoctorEmail(String doctorEmail) {
        DoctorEmail = doctorEmail;
    }
    
    public void setPatientEmail(String patientEmail) {
        PatientEmail = patientEmail;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getAppointmentID() {
        return AppointmentID;
    }

    public String getDate() {
        return Date;
    }

    public String getDoctorEmail() {
        return DoctorEmail;
    }


    public String getPatientEmail() {
        return PatientEmail;
    }

    public String getTime() {
        return Time;
    }

}
