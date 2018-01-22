package collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import objects.Customer;
import objects.Object;
import utils.Connector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by alien on 25.09.2017.
 */
public class Objects
{
    private static Connection c = Connector.getConnection();
    private static Statement stmt;
    private static String sql;
    private static int addId = 0;

    private int id;
    private int idCustomer;
    private int idStaff;
    private String date;
    private boolean go;
//    private String adres;
//    private String custNumb;
//    private String type;
//    private String cost;
//    private String warranty;
//    private String path;
//    private boolean go;
//    private String annotation;
    private static String[] names = {"adres", "custNumb", "date", "type", "cost", "warranty", "path", "annotation"};
    private static String[] objects;

    public ObservableList select()
    {
        ObservableList<Object> objectList = FXCollections.observableArrayList();
        objects = new String[names.length];

        try
        {
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM OBJECTS;");

            while ((rs.next()))
            {
                id = rs.getInt("id");
                idCustomer = rs.getInt("idCustomer");
                idStaff = rs.getInt("idStaff");
                go = rs.getBoolean("go");
                for (int i = 0; i < objects.length; i++)
                {
                    objects[i] = rs.getString(names[i]);
                    if ("null".equals(objects[i])) objects[i] = "---";
                }

                objectList.add(new Object(id, idCustomer, idStaff, go, objects));
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
        return objectList;
    }

    public ObservableList selectByCustomer(int idCust)
    {
        ObservableList<Object> objectList = FXCollections.observableArrayList();
        objects = new String[names.length];

        try
        {
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM OBJECTS WHERE IDCUSTOMER ='"+idCust+"';");

            while ((rs.next()))
            {
                id = rs.getInt("id");
                idCustomer = rs.getInt("idCustomer");
                idStaff = rs.getInt("idStaff");
                go = rs.getBoolean("go");
                for (int i = 0; i < objects.length; i++)
                {
                    objects[i] = rs.getString(names[i]);
                    if ("null".equals(objects[i])) objects[i] = "---";
                }

                objectList.add(new Object(id, idCustomer, idStaff, go, objects));
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
        return objectList;
    }

    public ObservableList selectByCustomerIsSelect(int idCust)
    {
        ObservableList<Object> objectList = FXCollections.observableArrayList();
        objects = new String[names.length];

        try
        {
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM OBJECTS WHERE IDCUSTOMER ='"+idCust+"' AND GO IS TRUE;");

            while ((rs.next()))
            {
                id = rs.getInt("id");
                idCustomer = rs.getInt("idCustomer");
                idStaff = rs.getInt("idStaff");
                go = rs.getBoolean("go");
                for (int i = 0; i < objects.length; i++)
                {
                    objects[i] = rs.getString(names[i]);
                    if ("null".equals(objects[i])) objects[i] = "---";
                }

                objectList.add(new Object(id, idCustomer, idStaff, go, objects));
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
        return objectList;
    }

    public ObservableList find(String word)
    {
        ArrayList<Integer> check = new ArrayList<>();
        ObservableList<Object> objectList = FXCollections.observableArrayList();
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
                rs = stmt.executeQuery("SELECT * FROM OBJECTS WHERE "+ names[i] +" ILIKE '"+word+"%';");
                while (rs.next())
                {
                    id = rs.getInt("id");
                    if (check.contains(id)) continue;

                    idCustomer = rs.getInt("idCustomer");
                    idStaff = rs.getInt("idStaff");
                    go = rs.getBoolean("go");

                    for (int j = 0; j < objects.length; j++)
                    {
                        objects[j] = rs.getString(names[j]);
                        if ("null".equals(objects[i])) objects[j] = "---";
                    }

                    check.add(id);
                    objectList.add(new Object(id, idCustomer, idStaff, go, objects));
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
        return objectList;
    }

    public static void add(Object object)
    {
        try {
            stmt = c.createStatement();

            sql = "INSERT INTO OBJECTS (id, idCustomer, idStaff, date, go, adres, custNumb, type, cost, warranty, path, annotation)" +
                    " VALUES ('" + addId + "', '" + object.getIdCustomer() + "', '" + object.getIdStaff() + "', '" + object.getDate() + "', '" + object.isGo() +
                    "', '" + object.getAdres() + "', '" + object.getCustNumb() + "', '" + object.getType() +
                    "', '" + object.getCost() + "', '" + object.getWarranty() + "', '" + object.getPath() + "', '" + object.getAnnotation() + "');";
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

    public static void update(Object object)
    {
        objects = object.getArray();
        int id = object.getId();
        int idCustomer = object.getIdCustomer();
        int idStaff = object.getIdStaff();
        boolean go = object.isGo();

        try
        {
            stmt = c.createStatement();
            sql = "UPDATE OBJECTS set IDCUSTOMER = '" + idCustomer +"' where ID= '" + id + "';";
            stmt.executeUpdate(sql);
            sql = "UPDATE OBJECTS set IDSTAFF = '" + idStaff +"' where ID= '" + id + "';";
            stmt.executeUpdate(sql);
            sql = "UPDATE OBJECTS set GO = '" + go +"' where ID= '" + id + "';";
            stmt.executeUpdate(sql);
            for (int i = 0; i < objects.length; i++)
            {
                sql = "UPDATE OBJECTS set " + names[i] + " = '" + objects[i] +"' where ID= '" + id + "';";
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
            sql = "DELETE from OBJECTS where ID=" + id + ";";
            stmt.executeUpdate(sql);
            c.commit();
            stmt.close();
            System.out.println("-- Operation DELETE123 done successfully");
        } catch (Exception e)
        {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
    }
}
