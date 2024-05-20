package Model;

import java.util.ArrayList;

public class Project {
    private Long id;
    private String name;
    private ArrayList<Ticket> tickets;

    public Project(Long id, String name) {
        this.id = id;
        this.name = name;
        this.tickets = tickets;
    }

    public Project() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(ArrayList<Ticket> tickets) {
        this.tickets = tickets;
    }
}
