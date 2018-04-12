/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportstats.service;

import java.util.List;
import sportstats.domain.Game;
import sportstats.domain.Team;
import sportstats.domain.broker.GameBroker;
import sportstats.service.constants.Outcome;

/**
 *
 * @author davik
 */
public class GetGamesByTeamIdService extends BaseService<List<Game>> {

    private final Long teamId;
    private final Outcome outcome;

    public GetGamesByTeamIdService(Long teamId, Outcome outcome) {
        if (teamId == null) {
            throw new SportstatsServiceException("Team id should not be null");
        }
        if (outcome == null) {
            throw new SportstatsServiceException("Outcome should not be null");
        }
        this.teamId = teamId;
        this.outcome = outcome;
    }

    @Override
    public List<Game> execute() {
        Team team = getBrokerFactory().getTeamBroker().findById(teamId);
        if (team == null) {
            throw new SportstatsServiceException("Team with the given id does not exist");
        }

        GameBroker gameBroker = getBrokerFactory().getGameBroker();
        List<Game> games = null;
        switch (outcome) {
            case ANY:
                games = gameBroker.findByTeamId(teamId);
                break;
            case WIN:
                games = gameBroker.findWinsByTeamId(teamId);
                break;
            case TIE:
                games = gameBroker.findTiesByTeamId(teamId);
                break;
            case LOSS:
                games = gameBroker.findLossesByTeamId(teamId);
                break;
        }

        return games;
    }
}
