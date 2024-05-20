package Model;

import java.sql.Timestamp;

public class Ticket {
    private Long id;
    private String title;
    private Project project;
    private String description;
    private int hurry;
    private Timestamp creationDate;
    private boolean solved;
    private float timeUsed;

    public Ticket() {

    }

    public Ticket(Long id, String title, Project project, String description, int hurry, boolean solved, float timeUsed) {
        this.id = id;
        this.title = title;
        this.project = project;
        this.description = description;
        this.hurry = hurry;
        this.creationDate = new Timestamp(System.currentTimeMillis());
        this.solved = solved;
        this.timeUsed = timeUsed;
    }

    public Ticket(Long id, String title, Project project, String description, int hurry, Timestamp creationDate,boolean solved, float timeUsed) {
        this.id = id;
        this.title = title;
        this.project = project;
        this.description = description;
        this.hurry = hurry;
        this.creationDate = creationDate;
        this.solved = solved;
        this.timeUsed = timeUsed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getHurry() {
        return hurry;
    }

    public void setHurry(int hurry) {
        this.hurry = hurry;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public boolean isSolved() {
        return solved;
    }

    public void setSolved(boolean solved) {
        this.solved = solved;
    }

    public float getTimeUsed() {
        return timeUsed;
    }

    public void setTimeUsed(float timeUsed) {
        this.timeUsed = timeUsed;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", hurry=" + hurry +
                ", creationDate=" + creationDate +
                ", solved=" + solved +
                ", timeUsed=" + timeUsed +
                '}';
    }
}
