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
        this.firstTeamId = firstTeamId;
        this.secondTeamId = secondTeamId;
    }

    @Override
    public List<Game> execute() {        
        return getBrokerFactory().getGameBroker().findByTeamIds(firstTeamId, secondTeamId);
    }

}
