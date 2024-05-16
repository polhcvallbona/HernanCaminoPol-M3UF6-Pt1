import DAO.ConnectionHandler;
import DAO.DAOProject;
import DAO.DAOTicket;

public class Main {
    public static void main(String[] args) {
        DAOTicket dt = new DAOTicket();
        DAOProject dp = new DAOProject();
        dt.dropStructure();
        dp.dropStructure();
        dp.createStructure();
        dt.createStructure();
    }
}