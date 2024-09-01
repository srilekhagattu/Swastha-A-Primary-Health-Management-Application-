
public class dataClassDoctor {
    private String Name;
    private String Date;
    private String Time;

    dataClassDoctor(String name, String date, String time) {
        this.Name = name;
        this.Date = date;
        this.Time = time;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getName() {
        return Name;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getDate() {
        return Date;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getTime() {
        return Time;
    }

}