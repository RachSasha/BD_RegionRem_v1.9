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
public class Staffs
{
    private static Connection c = Connector.getConnection();
    private static Statement stmt;
    private static String sql;
    private static int addId = 0;

    private int id;
//    private String FIO;
//    private String passport;
//    private String phone;
//    private String email;
//    private String annotation;

    private static String[] names = {"fio", "passport", "phone", "email", "annotation"};
    private static String[] staffs;
    private static ObservableMap<Integer, Staff> hash = FXCollections.observableHashMap();

    public static Staff getStaff(int id)
    {
        return hash.get(id);
    }

    public Staff findOfFio(String fio)
    {
        staffs = new String[names.length];

        try
        {
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM STAFFS WHERE FIO ILIKE '"+fio+"';");
            while (rs.next())
            {
                id = rs.getInt("id");
                for (int i = 0; i < staffs.length; i++)
                {
                    staffs[i] = rs.getString(names[i]);
                    if (staffs[i].equals("null")) staffs[i] = "---";
                }

                return new Staff(id, staffs);
            }
            rs.close();
            stmt.close();
            c.commit();
            System.out.println("-- Operation STAFFS SELECT done successfully");
        } catch (Exception e)
        {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        return new Staff();
    }

    public ObservableList select()
    {
        ObservableList<Staff> staffList = FXCollections.observableArrayList();
        staffs = new String[names.length];

        try
        {
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM STAFFS;");
            while ((rs.next()))
            {
                id = rs.getInt("id");
                for (int i = 0; i < staffs.length; i++)
                {
                    staffs[i] = rs.getString(names[i]);
                    if (staffs[i].equals("null")) staffs[i] = "---";
                }

                staffList.add(new Staff(id, staffs));
                hash.put(id, new Staff(id, staffs));
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
        return staffList;
    }

    public ObservableList find(String word)
    {
        ArrayList<Integer> check = new ArrayList<>();
        ObservableList<Staff> staffList = FXCollections.observableArrayList();
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
                rs = stmt.executeQuery("SELECT * FROM STAFFS WHERE "+ names[i] +" ILIKE '"+word+"%';");
                while (rs.next())
                {
                    id = rs.getInt("id");
                    if (check.contains(id)) continue;

                    for (int j = 0; j < staffs.length; j++)
                    {
                        staffs[j] = rs.getString(names[j]);
                        if (staffs[j].equals("null")) staffs[j] = "---";
                    }

                    check.add(id);
                    staffList.add(new Staff(id, staffs));
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
        return staffList;
    }

    public static void add(Staff staff)
    {
        try {
            stmt = c.createStatement();
            sql = "INSERT INTO STAFFS (id, fio, passport, phone, email, annotation)" +
                    " VALUES ('" + addId + "', '" + staff.getFIO() + "', '" + staff.getPassport() + "', '" +
                    staff.getPhone() + "', '" + staff.getEmail() + "', '" + staff.getAnnotation() + "');";
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

    public static void update(Staff staff)
    {
        staffs = staff.getArray();
        int id = staff.getId();

        try
        {
            stmt = c.createStatement();
            for (int i = 0; i < staffs.length; i++) {
                sql = "UPDATE STAFFS set " + names[i] + " = '" + staffs[i] +"' where ID= '" + id + "';";
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
            sql = "DELETE from STAFFS where ID=" + id + ";";
            stmt.executeUpdate(sql);
            c.commit();
            stmt.close();
            System.out.println("-- Operation DELETE done successfully");
        } catch (Exception e)
        {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
    }
}
