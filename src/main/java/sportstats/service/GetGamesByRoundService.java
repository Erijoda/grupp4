
package sportstats.service;

import java.util.List;
import sportstats.domain.Game;

/**
 *
 * @author mattkranc
 */
public class GetGamesByRoundService extends BaseService<List<Game>> {

    private final Long roundId;
    
    public GetGamesByRoundService(Long roundId) {
        this.roundId = roundId;
    }
    
    @Override
    public List<Game> execute() {
        List<Game> games = getBrokerFactory().getGameBroker()
                .findByRoundId(roundId);
        return games;                        
    }
    
}
