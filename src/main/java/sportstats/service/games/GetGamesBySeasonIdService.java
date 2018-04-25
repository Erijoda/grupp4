package sportstats.service.games;

import java.util.List;
import sportstats.domain.Game;
import sportstats.service.BaseService;

/**
 *
 * @author mattkranc
 */
public class GetGamesBySeasonIdService extends BaseService<List<Game>> {

    private final Long seasonId;

    public GetGamesBySeasonIdService(Long seasonId) {
        this.seasonId = seasonId;
    }

    @Override
    public List<Game> execute() {
        return getBrokerFactory().getGameBroker().findBySeasonId(seasonId);
    }
}
