package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DAOProject {

    public void dropStructure(){
        String sql = "DROP TABLE IF EXISTS Project;";
        Connection conn = ConnectionHandler.getConnection();
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();
            stmt.close();
        }catch (SQLException e){
            System.out.println("Peta al borrar Project;");
        }
    }

    public void createStructure(){
        String sql = "CREATE TABLE Project(" +
                "   id int PRIMARY KEY," +
                "   name varchar(100) NOT NULL" +
                ");";
        Connection conn = ConnectionHandler.getConnection();
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.executeUpdate(sql);
            stmt.close();
        }catch(SQLException e){
            System.out.println("Peta al crear Project;");
        }
    }

}
