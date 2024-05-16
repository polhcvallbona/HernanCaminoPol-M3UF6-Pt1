package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import static Config.DatabaseConfiguration.*;

public class ConnectionHandler {

    private final static int AMOUNT_CONNECTIONS = 3;
    private static ArrayList<Connection> connections = new ArrayList<>();

    private static void createConnections() {
        for (int i = 0; i < AMOUNT_CONNECTIONS; i++){
            try{
                connections.add(DriverManager.getConnection(DB_CONNECTION_STRING+DB_DATABASENAME,DB_USER,DB_PASSWORD));
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public static Connection getConnection(){
        if(connections.size() < 1){
            createConnections();
        }
        Connection returnConn = connections.remove(0);
        connections.add(returnConn);
        return returnConn;
    }
}
