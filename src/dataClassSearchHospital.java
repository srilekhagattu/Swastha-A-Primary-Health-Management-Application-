
public class dataClassSearchHospital {
    String HospitalName;
    String PhoneNumber;
    String HospitalID;

    dataClassSearchHospital(String hospitalName, String hospitalID, String phoneNumber) {
        this.HospitalName = hospitalName;
        this.PhoneNumber = phoneNumber;
        this.HospitalID = hospitalID;
    }

    public String getHospitalID() {
        return HospitalID;
    }
    
    public String getHospitalName() {
        return HospitalName;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }
    
    public void setHospitalID(String hospitalID) {
        HospitalID = hospitalID;
    }

    public void setHospitalName(String hospitalName) {
        HospitalName = hospitalName;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    
}