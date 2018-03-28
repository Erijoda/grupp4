package sportstats.spikes;

import java.util.List;
import sportstats.db.DbConn;
import static sportstats.db.DbConn.*;
//import org.javalite.activejdbc.Base;
import sportstats.domain.Sport;
import sportstats.domain.Team;
import sportstats.domain.broker.BrokerFactory;
import sportstats.domain.dao.SportDao;
import sportstats.service.AddSportService;
import sportstats.service.GetAllSportsService;
import sportstats.service.ServiceRunner;

/**
 *
 * @author thomas
 */
public class FirstShot {

    public static void main(String[] args) {
        
        getSports();
        
        /*
        List<Sport> sportList = new GetAllSportsService().execute();
        for(int i = 0; i < sportList.size(); i++) {
            System.out.println(sportList.get(i).getName());
        }
        */
        
        //addSport("Skidskytte");
        //new AddSportService("Skidor").execute();
        //open();
        //List<SportDao> sports = SportDao.findAll();
        //System.out.println("Antal tillgängliga sporter: " + sports.size());
        //Sport newSport = new Sport();
        //newSport.setName("Quidditch");
        //newSport.save();
        //sports = Sport.findAll();
        //System.out.println("Antal tillgängliga sporter: " + sports.size());
        //System.out.println("Den första heter: " + sports.get(0).getName());
        //System.out.println("Den sista heter: " + sports.get(sports.size() - 1).getName());
        //newSport.delete();
        //sports = Sport.findAll();
        //System.out.println("Antal tillgängliga sporter: " + sports.size());
        
        //List<Team> teams = Team.findAll();
        //System.out.println("Antal tillgängliga lag: " + teams.size());
        
        //Team newTeam = new Team();
        //newTeam.setName("Voldemort");
        //newTeam.save();
        
        //Team newTeam2 = new Team();
        //newTeam2.setName("Slytherin");
        //newTeam2.save();
        
        //teams = Team.findAll();
        /**System.out.println("Antal tillgängliga lag: " + teams.size());
        System.out.println("Den första heter: " + teams.get(0).getName());
        System.out.println("Den sista heter: " + teams.get(teams.size() - 1).getName());
        newTeam.delete();
        newTeam2.delete();
        teams = Team.findAll();
        System.out.println("Antal tillgängliga lag: " + teams.size());
        */
        //close();
        
        //GetAllSportsService x = new GetAllSportsService();
        //List<Sport> sports = x.execute();
        //for(Sport s : sports) {
        //    System.out.println(s);
        //}
    }
    /*
    public static void addSport(String name) {
            AddSportService addSport = new AddSportService(name);
            //addSport.init(new DbConn(), new BrokerFactory());
            addSport.execute();
    }*/
    
    public static void getSports() {
            ServiceRunner getSports = new ServiceRunner<>(new GetAllSportsService());
            getSports.execute();
        }

    /*public static void open() {
        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/sportstats", "root", "admin");
    }
    
    public static void close() {
        Base.close();
    }*/
}
