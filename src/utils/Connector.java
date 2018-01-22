package utils;

import org.postgresql.util.PSQLException;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by alien on 27.07.2017.
 */
public class Connector
{
    private static Connection connection;

    public static void Connect(String user, String password)
    {
        try
        {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/regionRem",user, password);
            connection.setAutoCommit(false);
            System.out.println("-- Opened database successfully");
            setConnection(connection);
        }
        catch (PSQLException e)
        {
            e.printStackTrace();
            System.out.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
    }

    public static Connection getConnection()
    {
        if (connection==null)
            Connect("postgres", "postgres");
        return connection;
    }

    public static void setConnection(Connection connection)
    {
        Connector.connection = connection;
    }
}
