/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportstats.service.games;

import java.util.List;
import sportstats.domain.Game;
import sportstats.service.BaseService;

/**
 *
 * @author Davik
 */
public class GetHomeGamesByTeamIdService extends BaseService<List<Game>> {
    
    private final Long teamId;

    public GetHomeGamesByTeamIdService(Long teamId) {
        this.teamId = teamId;
    }

    @Override
    public List<Game> execute() {
        return getBrokerFactory().getGameBroker().findHomeGamesByTeamId(teamId);
    }
    
}
