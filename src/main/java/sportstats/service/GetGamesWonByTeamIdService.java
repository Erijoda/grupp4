/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportstats.service;

import java.util.List;
import sportstats.domain.Game;
import sportstats.domain.Team;

/**
 *
 * @author davik
 */
public class GetGamesWonByTeamIdService extends BaseService<List<Game>> {

    private final Long teamId;

    public GetGamesWonByTeamIdService(Long teamId) {
        if (teamId == null) {
            throw new SportstatsServiceException("Team id should not be null");
        }
        this.teamId = teamId;
    }

    @Override
    public List<Game> execute() {
        Team team = getBrokerFactory().getTeamBroker().findById(teamId);
        if (team == null) {
            throw new SportstatsServiceException("Team with the given id does not exist");
        }
        return getBrokerFactory().getGameBroker().findWinsByTeamId(teamId);
    }
}
