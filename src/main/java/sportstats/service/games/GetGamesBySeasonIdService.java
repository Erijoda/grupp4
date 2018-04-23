package sportstats.service.games;

import java.util.ArrayList;
import java.util.List;
import sportstats.domain.Game;
import sportstats.domain.Round;
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
        List<Round> rounds = getBrokerFactory()
                .getRoundBroker().findBySeasonId(seasonId);

        List<Long> roundIds = new ArrayList();
        rounds.forEach((round) -> {
            roundIds.add(round.getId());
        });
        
        List games = new ArrayList();
        roundIds.forEach((id) -> {
            games.add(getBrokerFactory().getGameBroker().findByRoundId(id));
        });
        
        return games;
    }
}
