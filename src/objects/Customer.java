package objects;

/**
 * Created by alien on 25.09.2017.
 */
public class Customer
{
    private int id;
    private String name;
    private String adres;
    private String phone;
    private String inn;
    private String kpp;
    private String fio1;
    private String phone1;
    private String fio2;
    private String phone2;
    private String email;
    private String reception1;
    private String reception2;
    private String reception3;
    private String house1;
    private String house2;
    private String house3;
    private String house4;
    private String annotation;

    public Customer() {}

    public Customer(int id, String name, String adres, String phone, String inn, String kpp, String fio1, String phone1, String fio2, String phone2, String email, String reception1, String reception2, String reception3, String house1, String house2, String house3, String house4, String annotation) {
        this.id = id;
        this.name = name;
        this.adres = adres;
        this.phone = phone;
        this.inn = inn;
        this.kpp = kpp;
        this.fio1 = fio1;
        this.phone1 = phone1;
        this.fio2 = fio2;
        this.phone2 = phone2;
        this.email = email;
        this.reception1 = reception1;
        this.reception2 = reception2;
        this.reception3 = reception3;
        this.house1 = house1;
        this.house2 = house2;
        this.house3 = house3;
        this.house4 = house4;
        this.annotation = annotation;
    }

    public Customer(int id, String[] customers)
    {
        this.id = id;
        this.name = customers[0];
        this.adres = customers[1];
        this.phone = customers[2];
        this.inn = customers[3];
        this.kpp = customers[4];
        this.fio1 = customers[5];
        this.phone1 = customers[6];
        this.fio2 = customers[7];
        this.phone2 = customers[8];
        this.email = customers[9];
        this.reception1 = customers[10];
        this.reception2 = customers[11];
        this.reception3 = customers[12];
        this.house1 = customers[13];
        this.house2 = customers[14];
        this.house3 = customers[15];
        this.house4 = customers[16];
        this.annotation = customers[17];
    }

    public String[] getArray()
    {
        String[] array = {this.name, this.adres, this.phone, this.inn, this.kpp, this.fio1, this.phone1, this.fio2, this.phone2, this.email, this.reception1, this.reception2, this.reception3, this.house1, this.house2, this.house3, this.house4, this.annotation};
        return array;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getKpp() {
        return kpp;
    }

    public void setKpp(String kpp) {
        this.kpp = kpp;
    }

    public String getFio1() {
        return fio1;
    }

    public void setFio1(String fio1) {
        this.fio1 = fio1;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getFio2() {
        return fio2;
    }

    public void setFio2(String fio2) {
        this.fio2 = fio2;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getReception1() {
        return reception1;
    }

    public void setReception1(String reception1) {
        this.reception1 = reception1;
    }

    public String getReception2() {
        return reception2;
    }

    public void setReception2(String reception2) {
        this.reception2 = reception2;
    }

    public String getReception3() {
        return reception3;
    }

    public void setReception3(String reception3) {
        this.reception3 = reception3;
    }

    public String getHouse1() {
        return house1;
    }

    public void setHouse1(String house1) {
        this.house1 = house1;
    }

    public String getHouse2() {
        return house2;
    }

    public void setHouse2(String house2) {
        this.house2 = house2;
    }

    public String getHouse3() {
        return house3;
    }

    public void setHouse3(String house3) {
        this.house3 = house3;
    }

    public String getHouse4() {
        return house4;
    }

    public void setHouse4(String house4) {
        this.house4 = house4;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }
}
