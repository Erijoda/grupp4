package sportstats.domain.broker;

/**
 *
 * @author thomas
 */
public class BrokerFactory {
    
    public SportBroker getSportBroker() {
        return new SportBroker();
    }
    
    public TeamBroker getTeamBroker() {
        return new TeamBroker();
    }
    
    public LeagueBroker getLeagueBroker() {
        return new LeagueBroker();
    }
    
    public SeasonBroker getSeasonBroker() {
        return new SeasonBroker();
    }
    
    public SeasonTeamBroker getSeasonTeamBroker() {
        return new SeasonTeamBroker();
    }
}
