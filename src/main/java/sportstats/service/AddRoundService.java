
package sportstats.service;

import sportstats.domain.Round;
import sportstats.domain.Season;

/**
 *
 * @author mattkranc
 */
public class AddRoundService extends BaseService<Round>{
    
        private final Long seasonId;
        
    public AddRoundService(Long seasonId) {
        if (seasonId == null) {
            throw new SportstatsServiceException("Season should not be null");
        }
        this.seasonId = seasonId;
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
        
        return round;
    }
}
