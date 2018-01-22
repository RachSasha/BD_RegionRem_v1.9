package objects;

/**
 * Created by alien on 25.09.2017.
 */
public class Staff
{
    private int id;
    private String FIO;
    private String passport;
    private String phone;
    private String email;
    private String annotation;

    public Staff() {}

    public Staff(int id, String FIO, String passport, String phone, String email, String annotation) {
        this.id = id;
        this.FIO = FIO;
        this.passport = passport;
        this.phone = phone;
        this.email = email;
        this.annotation = annotation;
    }

    public Staff(int id, String[] staffs)
    {
        this.id = id;
        this.FIO = staffs[0];
        this.passport = staffs[1];
        this.phone = staffs[2];
        this.email = staffs[3];
        this.annotation = staffs[4];
    }

    public String[] getArray()
    {
        String[] array = {this.FIO, this.passport, this.phone, this.email, this.annotation};
        return array;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }
}
