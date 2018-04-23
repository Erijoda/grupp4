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
 * @author davik
 */
public class GetGamesByTeamIdService extends BaseService<List<Game>> {

    private final Long teamId;

    public GetGamesByTeamIdService(Long teamId) {
        this.teamId = teamId;
    }

    @Override
    public List<Game> execute() {
        return getBrokerFactory().getGameBroker().findByTeamId(teamId);
    }
}
