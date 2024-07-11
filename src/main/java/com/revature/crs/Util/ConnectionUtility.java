package com.revature.crs.Util;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * The Connection Utility Class is used to connect to our database when we need to connect to the database from our DAO Classes.
 */
public class ConnectionUtility {
    private static ConnectionUtility connectionUtility = new ConnectionUtility();
    private Properties properties = new Properties();

    /**
     * SingletonDesign via privatizing the Constructor to only be executed in the class itself
     */
    private ConnectionUtility() {
        try {
            properties.load(new FileReader("src/main/resources/db.properties"));
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Static block to CHECK that you have your intended driver available
     */
    static {
        try {
            Class.forName("org.postgresql.Driver");
        }

        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @return connectionUtility
     */
    public static ConnectionUtility getConnectionUtility() {
        return connectionUtility;
    }

    /**
     *
     * @return DriverManager object or null
     */
    public Connection getConnection() {
        try {
            return DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("user"), properties.getProperty("password"));
        }

        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}