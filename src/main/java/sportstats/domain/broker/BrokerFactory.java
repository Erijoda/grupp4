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
    
    public RoundBroker getRoundBroker() {
        return new RoundBroker();
    }
    
    public SeasonTeamBroker getSeasonTeamBroker() {
        return new SeasonTeamBroker();
    }
    
    public GameBroker getGameBroker() {
        return new GameBroker();
    }
}
