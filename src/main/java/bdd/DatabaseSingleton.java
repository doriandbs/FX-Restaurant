package bdd;


import constantes.Constants;

import java.io.*;
import java.nio.file.Path;
import java.sql.*;
import java.util.Properties;


public class DatabaseSingleton {


    //RECUPERATION DES DONNEES DE LA BDD


    private static final DatabaseSingleton instance;

    static {
        try {
            instance = new DatabaseSingleton();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private Connection connection;

    private DatabaseSingleton() throws FileNotFoundException {
        System.out.println("Instanciation du Singleton");
        System.out.println("Path executing => "+System.getProperty("user.dir"));
    }

    public static DatabaseSingleton getInstance() {
        return instance;
    }

    public void connect() throws SQLException, IOException {
        try (InputStream input = new FileInputStream(System.getProperty("user.dir")+"/configbdd.properties")){
            Properties prop = new Properties();
            prop.load(input);
            String url = prop.getProperty("db.url");
            String user = prop.getProperty("db.user");
            String password = prop.getProperty("db.password");
            System.out.println("url : " + url +"user : " + user + "psw : " + password );
            connection = DriverManager.getConnection(url,user, password);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public ResultSet query(String sql) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeQuery(sql);
    }

    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return connection.prepareStatement(sql);
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
