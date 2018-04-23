
package sportstats.service.games;

import java.util.List;
import sportstats.domain.Game;
import sportstats.domain.Round;
import sportstats.domain.Season;
import sportstats.domain.Team;
import sportstats.domain.broker.TeamBroker;
import sportstats.rest.shapes.GameShape;
import sportstats.service.BaseService;
import sportstats.service.SportstatsServiceException;

/**
 *
 * @author mattkranc
 */
public class AddRoundService extends BaseService<Round>{
        private final Long seasonId;
        private final List<GameShape> games;
        
    public AddRoundService(Long seasonId, List<GameShape> games) {
        if (seasonId == null) {
            throw new SportstatsServiceException("Season should not be null");
        }
        if (games == null || games.isEmpty()) {
            throw new SportstatsServiceException("You cannot create a round without the games");
        }
        this.seasonId = seasonId;
        this.games = games;
    }
    

    @Override
    public Round execute() {
        Round round = getBrokerFactory().getRoundBroker().create();
        
        Season season = getBrokerFactory().getSeasonBroker().findById(seasonId);
        if (season == null) {
            throw new SportstatsServiceException("Season with the given id does not exist");
        }
        round.setSeason(season);
        round.save();
        
        games.forEach((gameShape) -> {
            Game g = getBrokerFactory().getGameBroker().create();
            
            TeamBroker tb = getBrokerFactory().getTeamBroker(); 
            Team at = tb.findById(gameShape.awayTeamId);
            Team ht = tb.findById(gameShape.homeTeamId);
            if (at == null) {
                throw new SportstatsServiceException("Away team with the id: " + gameShape.awayTeamId + ", does not exist");
            }
            if (ht == null) {
                throw new SportstatsServiceException("Home team with the id: " + gameShape.homeTeamId + ", does not exist");
            }
            
            g.setAwayTeam(at);
            g.setHomeTeam(ht);
            g.setRound(round);
            g.save();
        });
        
        return round;
    }
}
