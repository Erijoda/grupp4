
package sportstats.service.games;

import java.util.List;
import sportstats.domain.Game;
import sportstats.service.BaseService;

/**
 *
 * @author mattkranc
 */
public class GetGamesByRoundIdService extends BaseService<List<Game>> {

    private final Long roundId;
    
    public GetGamesByRoundIdService(Long roundId) {
        this.roundId = roundId;
    }
    
    @Override
    public List<Game> execute() {
        List<Game> games = getBrokerFactory().getGameBroker()
                .findByRoundId(roundId);
        return games;                        
    }
    
}
