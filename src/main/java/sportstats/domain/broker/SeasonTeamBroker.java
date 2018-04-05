package sportstats.domain.broker;

import sportstats.domain.SeasonTeam;
import sportstats.domain.dao.SeasonTeamDao;

public class SeasonTeamBroker {

    public SeasonTeam findByIds(Long teamId, Long seasonId) {
        return SeasonTeam.of(
                SeasonTeamDao
                        .findFirst("team_id=? and season_id=?",
                                teamId, seasonId));
    }

}
