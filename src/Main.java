import DAO.ConnectionHandler;
import DAO.DAOProject;
import DAO.DAOTicket;
import Model.Project;
import Model.Ticket;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        DAOTicket dt = new DAOTicket();
        DAOProject dp = new DAOProject();
        dt.dropStructure();
        dp.dropStructure();
        dp.createStructure();
        dt.createStructure();

        Project p1 = new Project(1l, "Backend Ticket Application");
        dp.addProject(p1);
        Project p2 = new Project(2l, "Frontend Ticket Application");
        dp.addProject(p2);
        Project p3 = new Project(3l, "Landing Page");
        dp.addProject(p3);
        Project p4 = new Project(4l, "Windows Server Active Directory");
        dp.addProject(p4);
        Project p5 = new Project(5l, "Hardware");
        //dp.addProject(p5);
        System.out.println();
        Ticket t1 = new Ticket(1l,"Backend API failing",p1,"The backend API is not working when i update a ticket",7,false,0f);
        dt.addTicket(t1);
        dt.addTicket(t1);
        Ticket t2 = new Ticket(2l,"Backend Server Stopped",p1,"The backend server stopped due an error.",8,true,0.2f);
        dt.addTicket(t2);
        Ticket t3 = new Ticket(3l,"Incorrect color schema in index.html",p2,"The main page of the ticketing web-app, have incorrect color schema",1,false,0f);
        dt.addTicket(t3);
        Ticket t4 = new Ticket(4l,"DDoS Attacks",p3,"The landing page is suffering DDoS Attacks!",10,false,0f);
        dt.addTicket(t4);
        Ticket t5 = new Ticket(5l,"Shared Desktop is not working.",p4,"I can't access to my shared desktop service since last update on the DC AC.",9,true,0.25f);
        dt.addTicket(t5);
        System.out.println();

        t1.setDescription("No se de que era asi que no se que poner de descricion.");
        dt.updateTicket(t1);
        System.out.println();

        t1.setProject(p5);
        dt.updateTicket(t1);
        System.out.println();

        for (Ticket t : dt.getTickets()){
            System.out.println(t);
        }
        System.out.println();
        for (Ticket t : dt.getTicketsByProjectId(1l)){
            System.out.println(t);
        }
        System.out.println();
        for (Ticket t : dt.getTicketsByProject(p1)){
            System.out.println(t);
        }
        System.out.println();
        for (Ticket t : dt.getTicketsOrderedBy("description",false)){
            System.out.println(t);
        }
        System.out.println();
        System.out.println(dt.getTicketById(11l));
        System.out.println();
        System.out.println(dt.getTicketById(3l));
        System.out.println();
        dt.printTimeUsed();
    }
}