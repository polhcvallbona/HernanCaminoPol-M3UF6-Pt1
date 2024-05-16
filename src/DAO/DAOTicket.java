package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DAOTicket {

    public void dropStructure(){
        String sql = "DROP TABLE IF EXISTS Ticket;";
        Connection conn = ConnectionHandler.getConnection();
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();
            stmt.close();
        }catch(SQLException e){
            System.out.println("Peta al borrar Ticket.");
        }
    }

    public void createStructure(){
        String sql = "CREATE TABLE Ticket(" +
                "   id int PRIMARY KEY," +
                "   title varchar(100) NOT NULL," +
                "   project int NOT NULL," +
                "   description varchar(16000) NOT NULL," +
                "   hurry int NOT NULL," +
                "   creationDate DateTime NOT NULL," +
                "   solved boolean NOT NULL," +
                "   timeUsed decimal(5,2)," +
                "   FOREIGN KEY (project) REFERENCES Project(id)" +
                ");";
        Connection conn = ConnectionHandler.getConnection();
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.executeUpdate(sql);
            stmt.close();
        }catch(SQLException e){
            System.out.println("Peta al crear Ticket.");
        }
    }

}
