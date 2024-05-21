package DAO;

import Exceptions.FKException;
import Exceptions.PKException;
import Model.Project;
import Model.Ticket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOProject {

    public void dropStructure() {
        String sql = "DROP TABLE IF EXISTS Project;";
        Connection conn = ConnectionHandler.getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Couldn't delete project structure!");
        }
    }

    public void createStructure() {
        String sql = "CREATE TABLE Project(" +
                "   id int PRIMARY KEY," +
                "   name varchar(100) NOT NULL" +
                ");";
        Connection conn = ConnectionHandler.getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Couldn't create structure for project!");
        }
    }

    public void addProject(Project p){
        String sql = "INSERT INTO Project VALUES(?,?)";
        Connection conn = ConnectionHandler.getConnection();
        PreparedStatement stmt = null;
        try{
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1, p.getId());
            stmt.setString(2, p.getName());
            stmt.executeUpdate();
            stmt.close();
        }catch (SQLException e){
            switch(e.getSQLState()){
                case "23000":
                    System.out.println("Primary Key Repeated.");
                    break;
                case "22001":
                    System.out.println("Project Name too long.");
                    break;
            }
        }finally{
            System.out.println("Added successfully project with id "+p.getId());
        }


    }

    public void updateProject(Project p){
        String sql = "UPDATE Project SET name = ? WHERE id = ?;";
        Connection conn = ConnectionHandler.getConnection();
        PreparedStatement stmt = null;
        DAOProject dp = new DAOProject();
        try{
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,p.getName());
            stmt.setLong(2,p.getId());
            stmt.executeUpdate();
            stmt.close();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            System.out.println("Succesfully updated project with id "+p.getId());
        }
    }

    public boolean projectExists(Long id){
        String sql = "SELECT count(*) FROM Project WHERE id = ?;";
        Connection conn = ConnectionHandler.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int count = 0;
        try{
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1,id);
            rs = stmt.executeQuery();
            rs.next();
            count = rs.getInt(1);
            rs.close();
            stmt.close();
            return count == 1;
        }catch (SQLException e){
            System.out.println("Ha petau");
            e.printStackTrace();
        }
        return false;
    }

}
