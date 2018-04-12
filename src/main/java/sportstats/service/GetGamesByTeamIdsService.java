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
 * @author Davik
 */
public class GetGamesByTeamIdsService extends BaseService<List<Game>> {

    private final Long firstTeamId;
    private final Long secondTeamId;

    public GetGamesByTeamIdsService(Long firstTeamId, Long secondTeamId) {
        if (firstTeamId == null) {
            throw new SportstatsServiceException("FirstTeamId should not be null");
        }
        if (secondTeamId == null) {
            throw new SportstatsServiceException("SecondTeamId should not be null");
        }

        this.firstTeamId = firstTeamId;
        this.secondTeamId = secondTeamId;
    }

    @Override
    public List<Game> execute() {
        Team firstTeam = getBrokerFactory().getTeamBroker().findById(firstTeamId);
        Team secondTeam = getBrokerFactory().getTeamBroker().findById(secondTeamId);
        if (firstTeam == null) {
            throw new SportstatsServiceException("A team with the first id does not exist");
        }
        if (secondTeam == null) {
            throw new SportstatsServiceException("A team with the second id does not exist");
        }
        
        return getBrokerFactory().getGameBroker().findByTeamIds(firstTeamId, secondTeamId);
    }

}
