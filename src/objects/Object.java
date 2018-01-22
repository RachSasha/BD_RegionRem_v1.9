package objects;

import java.util.Date;

/**
 * Created by alien on 25.09.2017.
 */
public class Object
{
    private int id;
    private int idCustomer;
    private int idStaff;
    private String adres;
    private String custNumb;
    private String date;
    private String type;
    private String cost;
    private String warranty;
    private String path;
    private String customer;
    private boolean go;
    private String annotation;

    public Object() {}

    public Object(int id, int idCustomer, int idStaff, String adres, String custNumb, String date, String type, String cost, String warranty, String path, boolean go, String annotation) {
        this.id = id;
        this.idCustomer = idCustomer;
        this.idStaff = idStaff;
        this.adres = adres;
        this.custNumb = custNumb;
        this.date = date;
        this.type = type;
        this.cost = cost;
        this.warranty = warranty;
        this.path = path;
        this.go = go;
        this.annotation = annotation;
    }

    public Object(int id, int idCustomer, int idStaff, boolean go, String[] objects) {
        this.id = id;
        this.idCustomer = idCustomer;
        this.idStaff = idStaff;
        this.go = go;
        this.adres = objects[0];
        this.custNumb = objects[1];
        this.date = objects[2];
        this.type = objects[3];
        this.cost = objects[4];
        this.warranty = objects[5];
        this.path = objects[6];
        this.annotation = objects[7];
    }

    public void setCustomer(String customer)
    {
        this.customer = customer;
    }

    public String[] getArray()
    {
        String[] array = {this.adres, this.custNumb, this.date, this.type, this.cost, this.warranty, this.path, this.annotation};
        return array;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public int getIdStaff() {
        return idStaff;
    }

    public void setIdStaff(int idStaff) {
        this.idStaff = idStaff;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getCustNumb() {
        return custNumb;
    }

    public void setCustNumb(String custNumb) {
        this.custNumb = custNumb;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getWarranty() {
        return warranty;
    }

    public void setWarranty(String warranty) {
        this.warranty = warranty;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isGo() {
        return go;
    }

    public void setGo(boolean go) {
        this.go = go;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }
}
