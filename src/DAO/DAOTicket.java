package DAO;

import Exceptions.FKException;
import Exceptions.PKException;
import Model.Project;
import Model.Ticket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

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
                "   projectId int NOT NULL," +
                "   description varchar(16000) NOT NULL," +
                "   hurry int NOT NULL," +
                "   creationDate DateTime NOT NULL," +
                "   solved boolean NOT NULL," +
                "   timeUsed decimal(5,2)," +
                "   FOREIGN KEY (projectId) REFERENCES Project(id)" +
                ");";
        Connection conn = ConnectionHandler.getConnection();
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.executeUpdate(sql);
            stmt.close();
        }catch(SQLException e){
            System.out.println("Peta al crear Ticket.");
            e.printStackTrace();
        }
    }

    public void addTicket(Ticket t){
        String sql = "INSERT INTO Ticket VALUES(?,?,?,?,?,?,?,?)";
        Connection conn = ConnectionHandler.getConnection();
        PreparedStatement stmt = null;
        DAOProject dp = new DAOProject();
        try{
            if(!dp.projectExists(t.getProject().getId())) throw new FKException("This project doesn't exists in the database. Project id: "+t.getProject().getId());
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1, t.getId());
            stmt.setString(2, t.getTitle());
            stmt.setLong(3,t.getProject().getId());
            stmt.setString(4,t.getDescription());
            stmt.setInt(5,t.getHurry());
            stmt.setTimestamp(6,t.getCreationDate());
            stmt.setBoolean(7,t.isSolved());
            stmt.setFloat(8,t.getTimeUsed());
            stmt.executeUpdate();
            stmt.close();
        }catch (SQLException e){
            switch(e.getSQLState()){
                case "23000":
                    System.out.println("Primary Key Repeated.");
                    break;
                case "22001":
                    System.out.println("Some value is too long.");
                    break;
                default:
                    System.out.println("Some other error." + e.getSQLState());
                    e.printStackTrace();
            }
        }catch(FKException e){
            System.out.println(e.getMessage());
        }


    }

    public void updateTicket(Ticket t){
        String sql = "UPDATE Ticket SET title = ?, projectId = ?, description = ?, hurry = ?, solved = ?, timeUsed = ? WHERE id = ?";
        Connection conn = ConnectionHandler.getConnection();
        PreparedStatement stmt = null;
        DAOProject dp = new DAOProject();
        try{
            if(!dp.projectExists(t.getProject().getId())) throw new FKException("This project doesn't exists in the database. Project id: "+t.getProject().getId());
            if(!ticketExists(t.getId())) throw new PKException("The key shouldn't be updated. Ticket id: "+t.getId());
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
        }catch(FKException | PKException e){
            System.out.println(e.getMessage());
        }
    }

    public void deleteTicket(Long id){
        String sql = "DELETE FROM Ticket WHERE id = ?";
        Connection conn = ConnectionHandler.getConnection();
        PreparedStatement stmt = null;
        try{
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1,id);
            if(stmt.executeUpdate() < 1){
                throw new PKException("This primary key isn't associated in any ticket.");
            }
            stmt.close();
        }catch (SQLException e){
            e.printStackTrace();
        }catch (PKException e){
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Ticket> getTickets(){
        String sql = "SELECT * FROM Ticket ORDER BY id;";
        System.out.println(sql);
        Connection conn = ConnectionHandler.getConnection();
        ArrayList<Ticket> tickets = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while(rs.next()){
                Ticket t = new Ticket(rs.getLong("id"),
                        rs.getString("title"),
                        null,
                        rs.getString("description"),
                        rs.getInt("hurry"),
                        rs.getTimestamp("creationDate"),
                        rs.getBoolean("solved"),
                        rs.getFloat("timeUsed"));
                tickets.add(t);
            }
            return tickets;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Ticket> getTicketsByProjectId(Long cat){
        String sql = "SELECT * FROM Ticket WHERE projectId = ? ORDER BY id;";
        System.out.println(sql);
        Connection conn = ConnectionHandler.getConnection();
        ArrayList<Ticket> tickets = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1,cat);
            rs = stmt.executeQuery();
            while(rs.next()){
                Ticket t = new Ticket(rs.getLong("id"),
                        rs.getString("title"),
                        null,
                        rs.getString("description"),
                        rs.getInt("hurry"),
                        rs.getTimestamp("creationDate"),
                        rs.getBoolean("solved"),
                        rs.getFloat("timeUsed"));
                tickets.add(t);
            }
            return tickets;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Ticket> getTicketsByProject(Project p){
        return getTicketsByProjectId(p.getId());
    }

    public Ticket getTicketById(Long id){
        String sql = "SELECT * FROM Ticket WHERE id = ? ORDER BY id;";
        System.out.println(sql);
        Connection conn = ConnectionHandler.getConnection();
        ArrayList<Ticket> tickets = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1,id);
            rs = stmt.executeQuery();
            while(rs.next()){
                Ticket t = new Ticket(rs.getLong("id"),
                        rs.getString("title"),
                        null,
                        rs.getString("description"),
                        rs.getInt("hurry"),
                        rs.getTimestamp("creationDate"),
                        rs.getBoolean("solved"),
                        rs.getFloat("timeUsed"));
                tickets.add(t);
            }
            if(!tickets.isEmpty()){
                return tickets.get(0);
            }
            return null;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Ticket> getTicketsOrderedBy(String orderBy, boolean desc){
        HashMap<String,Integer> mapper = new HashMap<>();
        mapper.put("id",1);
        mapper.put("title",2);
        mapper.put("projectId",3);
        mapper.put("description",4);
        mapper.put("hurry",5);
        mapper.put("creationDate",6);
        mapper.put("solved",7);
        mapper.put("timeUsed",8);

        String sql;
        if(desc){
            sql = "SELECT * FROM Ticket ORDER BY ? desc;";
        }else{
            sql = "SELECT * FROM Ticket ORDER BY ? asc;";
        }
        System.out.println(sql);
        Connection conn = ConnectionHandler.getConnection();
        ArrayList<Ticket> tickets = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1,mapper.get(orderBy));
            rs = stmt.executeQuery();
            while(rs.next()){
                Ticket t = new Ticket(rs.getLong("id"),
                        rs.getString("title"),
                        null,
                        rs.getString("description"),
                        rs.getInt("hurry"),
                        rs.getTimestamp("creationDate"),
                        rs.getBoolean("solved"),
                        rs.getFloat("timeUsed"));
                tickets.add(t);
            }
            return tickets;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public boolean ticketExists(Long id){
        String sql = "SELECT count(*) FROM Ticket WHERE id = ?;";
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
