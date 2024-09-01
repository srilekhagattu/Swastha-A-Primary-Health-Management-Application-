import java.util.UUID;

public class Appointment {

    protected String id; // uuid
    protected String Time;
    protected String Date;
    protected Doctor doc;
    protected Patient p;

    public Appointment(Doctor doc, String time, String date) {

        this.Time = time;
        this.Date = date;
        this.doc = doc;
        this.id = UUID.randomUUID().toString();

    }

    public Appointment(Patient p, String time, String date) {

        this.Time = time;
        this.Date = date;
        this.p = p;
    }

    public void setTime(String time) {
        this.Time = time;
    }

    public String getTime() {
        return Time;
    }

    public void setDate(String date) {
        this.Date = date;
    }

    public String getDate() {
        return Date;
    }

    public Doctor getDoctor() {
        return doc;
    }

    public Patient getPatient() {
        return p;
    }

    public String getId() {
        return id;
    }
}
