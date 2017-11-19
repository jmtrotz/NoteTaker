package notetaker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * Class to store database methods that can be called by other classes
 * @author Jeffrey Trotz
 * @version 1.0.2
 */
public class DatabaseMethods 
{    
    // Database connection is global so the .close() method can be called in 
    // other methods to conserve resources
    private static Connection connection = null;
    
    /**
     * Method to connect to the database
     */
    private void connectToDatabase()
    {
        try 
        {
            DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/Database", "app", "app");
        }
        
        catch (SQLException sqlException)
        {
            System.out.println(sqlException.getMessage());
        }
    }
    
    /**
     * Method to change the names of current classes or the location of the 
     * notes for the class
     * @param className - Name of the class to be added to the database
     * @param classPath - File path to the location of the notes for the class
     */
    public void setSettings(String className, String classPath)
    {
        PreparedStatement statement = null;
        try 
        {            
            this.connectToDatabase();
            statement = connection.prepareStatement("INSERT INTO SETTINGS "
                    + "(CLASSNAME, CLASSPATH) VALUES (?, ?)");            
            statement.setString(1, className);
            statement.setString(2, classPath);
            statement.executeUpdate();
        } 
        
        catch (SQLException sqlException) 
        {
            System.out.println(sqlException.getMessage());
        }
        
        finally
        {
            try
            {
                statement.close();
                connection.close();
            } 
            
            catch (SQLException sqlException) 
            {
                System.out.println(sqlException.getMessage());
            }
        }
    }
    
    /**
     * Method to get the name and location of notes for saved classes
     * @return - Returns a HashMap containing the class name (key) and the path
     * to saved notes for that class (value)
     */
    public HashMap getSettings()
    {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        HashMap<String, String> settings = new HashMap<>();
        
        try 
        {            
            this.connectToDatabase();
            statement = connection.prepareStatement("SELECT * FROM SETTINGS");
            resultSet = statement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            
            while (resultSet.next())
            {
                for (int index = 1; index <= resultSetMetaData.getColumnCount(); index ++)
                {
                    settings.put(resultSet.getString("CLASSNAME"), 
                            resultSet.getString("CLASSPATH"));
                }
            }
        } 
        
        catch (SQLException sqlException) 
        {
            System.out.println(sqlException.getMessage());
        }
        
        finally
        {
            try
            {
                resultSet.close();
                statement.close();
                connection.close();
            } 
            
            catch (SQLException sqlException) 
            {
                System.out.println(sqlException.getMessage());
            }
        }
        
        return settings;
    }
}