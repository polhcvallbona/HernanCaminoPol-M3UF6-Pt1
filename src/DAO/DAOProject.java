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
            System.out.println("Peta al borrar Project;");
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
            System.out.println("Peta al crear Project;");
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
        }


    }

    public void updateProject(Ticket t){
        String sql = "UPDATE Ticket SET title = ?, projectId = ?, description = ?, hurry = ?, solved = ?, timeUsed = ? WHERE id = ?";
        Connection conn = ConnectionHandler.getConnection();
        PreparedStatement stmt = null;
        DAOProject dp = new DAOProject();
        try{
            if(!dp.projectExists(t.getProject().getId())) throw new FKException("This project doesn't exists in the database. Project id: "+t.getProject().getId());
            stmt = conn.prepareStatement(sql);
            stmt.setLong(7, t.getId());
            stmt.setString(1, t.getTitle());
            stmt.setLong(2,t.getProject().getId());
            stmt.setString(3,t.getDescription());
            stmt.setInt(4,t.getHurry());
            stmt.setBoolean(5,t.isSolved());
            stmt.setFloat(6,t.getTimeUsed());
            stmt.executeUpdate();
            stmt.close();
        }catch (SQLException e){
            e.printStackTrace();
        }catch(FKException e){
            System.out.println(e.getMessage());
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
