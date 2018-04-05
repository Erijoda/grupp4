
package sportstats.service;

import sportstats.domain.Round;
import sportstats.domain.Season;

/**
 *
 * @author mattkranc
 */
public class AddRoundBySeasonService extends BaseService<Round>{
    
        private final Long seasonId;
        
    public AddRoundBySeasonService(Long seasonId) {
        if (seasonId == null) {
            throw new SportstatsServiceException("Season doesn't exist");
        }
        this.seasonId = seasonId;
    }
    

    @Override
    public Round execute() {
        
        Round round = getBrokerFactory().getRoundBroker().create();
        
        Season season = getBrokerFactory().getSeasonBroker().findById(seasonId);
        if (season == null) {
            throw new SportstatsServiceException("Season doesn't exist");
        }
        
        round.setSeason(season);
        round.save();
        
        return round;
    }
}
