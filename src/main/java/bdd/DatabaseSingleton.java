package main.java.bdd;


import main.java.constantes.Constants;

import java.sql.*;


public class DatabaseSingleton {

    private static final DatabaseSingleton instance;

    static {
        instance = new DatabaseSingleton();
    }

    private Connection connection;

    private DatabaseSingleton() {
        System.out.println("Instanciation du Singleton");
    }

    public static DatabaseSingleton getInstance() {
        return instance;
    }

    public void connect() throws SQLException {
        connection = DriverManager.getConnection(Constants.url, Constants.user, Constants.psw);
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
