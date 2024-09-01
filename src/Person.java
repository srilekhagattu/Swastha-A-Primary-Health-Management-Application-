
abstract class Person {
    // Fields
    protected String Name;
    protected String DateOfBirth;
    protected String Email;
    protected String Password;
    protected String Gender;
    protected String Address;
    protected int Pincode;

    // << Constructor >>
    public Person(String name, String email, String password, String dob, String gender, String address, int pincode) {
        this.Name = name;
        this.DateOfBirth = dob;
        this.Email = email;
        this.Password = password;
        this.Gender = gender;
        this.Address = address;
        this.Pincode = pincode;
    }

    // Respective Accessors and Mutators
    public void setName(String name) {
        this.Name = name;
    }

    public String getName() {
        return Name;
    }

    public void setDob(String dob) {
        this.DateOfBirth = dob;
    }

    public String getDob() {
        return DateOfBirth;
    }

    public void setGender(String gender) {
        this.Gender = gender;
    }

    public String getGender() {
        return Gender;
    }

    public void setAddress(String address) {
        this.Address = address;
    }

    public String getAddress() {
        return Address;
    }

    public void setPincode(int pincode) {
        this.Pincode = pincode;
    }

    public int getPincode() {
        return Pincode;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getEmail() {
        return Email;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getPassword() {
        return Password;
    }

    public void createAccount(String email, String pass) {
        this.Email = email;
        this.Password = pass;
    }

    public abstract int login(String email, String pass);
}