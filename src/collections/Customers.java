package collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import objects.Customer;
import objects.Staff;
import utils.Connector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by alien on 25.09.2017.
 */
public class Customers
{
    private static Connection c = Connector.getConnection();
    private static Statement stmt;
    private static String sql;
    private static int addId = 0;

    private int id;
//    private String name;
//    private String adres;
//    private String phone;
//    private String inn;
//    private String kpp;
//    private String fio1;
//    private String phone1;
//    private String fio2;
//    private String phone2;
//    private String email;
//    private String reception1;
//    private String reception2;
//    private String reception3;
//    private String house1;
//    private String house2;
//    private String house3;
//    private String house4;
//    private String annotation;
    private static String[] names = {"name", "adres", "phone", "inn", "kpp", "fio1", "phone1", "fio2", "phone2", "email",
        "reception1", "reception2", "reception3", "house1", "house2", "house3", "house4", "annotation"};
    private static String[] customers;
    private static ObservableMap<Integer, Customer> hash = FXCollections.observableHashMap();

    public static Customer getCustomer(int id)
    {
        return hash.get(id);
    }

    public ObservableList select()
    {
        ObservableList<Customer> customerList = FXCollections.observableArrayList();
        customers = new String[names.length];

        try
        {
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM CUSTOMERS;");
            while ((rs.next()))
            {
                id = rs.getInt("id");
                for (int i = 0; i < customers.length; i++)
                {
                    customers[i] = rs.getString(names[i]);
                    if (customers[i].equals("null")) customers[i] = "---";
                }

                customerList.add(new Customer(id, customers));
                hash.put(id, new Customer(id, customers));
                if (addId <= id)
                    addId = ++id;
            }
            rs.close();
            stmt.close();
            c.commit();
            System.out.println("Operation select done successfully");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        return customerList;
    }

    public ObservableList find(String word)
    {
        ArrayList<Integer> check = new ArrayList<>();
        ObservableList<Customer> customerList = FXCollections.observableArrayList();
        ResultSet rs;

        String[] wordAr = word.split(" ");
        word="";
        for (int i = 0; i < wordAr.length; i++)
        {
            word+="%"+wordAr[i];
        }

        try
        {
            stmt = c.createStatement();
            for (int i = 0; i < names.length; i++) {
                rs = stmt.executeQuery("SELECT * FROM CUSTOMERS WHERE "+ names[i] +" ILIKE '"+word+"%';");
                while (rs.next())
                {
                    id = rs.getInt("id");
                    if (check.contains(id)) continue;

                    for (int j = 0; j < customers.length; j++)
                    {
                        customers[j] = rs.getString(names[j]);
                        if (customers[j].equals("null")) customers[j] = "---";
                    }

                    check.add(id);
                    customerList.add(new Customer(id, customers));
                }
            }
            stmt.close();
            c.commit();
            System.out.println("-- Operation CUSTOMERS SELECT done successfully");
        } catch (Exception e)
        {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        return customerList;
    }

    public static void add(Customer customer)
    {
        try {
            stmt = c.createStatement();
            sql = "INSERT INTO CUSTOMERS (id, name, adres, phone, inn, kpp, fio1, phone1, fio2, phone2, email," +
                    " reception1, reception2, reception3, house1, house2, house3, house4, annotation)" +
                    " VALUES ('" + addId + "', '" + customer.getName() + "', '" + customer.getAdres() + "', '" + customer.getPhone() + "', '" + customer.getInn() +
                    "', '" + customer.getKpp() + "', '" + customer.getFio1() + "', '" + customer.getPhone1() +
                    "', '" + customer.getFio2() + "', '" + customer.getPhone2() + "', '" + customer.getEmail() +
                    "', '" + customer.getReception1() + "', '" + customer.getReception2() + "', '" + customer.getReception3() +
                    "', '" + customer.getHouse1() + "', '" + customer.getHouse2() + "', '" + customer.getHouse3() +
                    "', '" + customer.getHouse4() + "', '" + customer.getAnnotation() + "');";
            stmt.executeUpdate(sql);

            stmt.close();
            c.commit();
            System.out.println("-- Records created successfully");
        } catch (Exception e)
        {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
    }

    public static void update(Customer customer)
    {
        customers = customer.getArray();
        int id = customer.getId();

        try
        {
            stmt = c.createStatement();
            for (int i = 0; i < customers.length; i++) {
                sql = "UPDATE CUSTOMERS set " + names[i] + " = '" + customers[i] +"' where ID= '" + id + "';";
                stmt.executeUpdate(sql);
            }


            c.commit();
            stmt.close();

            System.out.println("-- Operation UPDATE done successfully");
        } catch (Exception e)
        {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }

    }

    public static void delete(int id)
    {
        try
        {
            stmt = c.createStatement();
            sql = "DELETE from CUSTOMERS where ID=" + id + ";";
            stmt.executeUpdate(sql);
            c.commit();
            stmt.close();
            System.out.println("-- Operation DELETE90 done successfully");
        } catch (Exception e)
        {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
    }
}
