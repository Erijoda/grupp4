/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportstats.service;

import java.util.List;
import sportstats.domain.Game;

/**
 *
 * @author davik
 */
public class GetGamesTiedByTeamIdService extends BaseService<List<Game>> {

    private final Long teamId;

    public GetGamesTiedByTeamIdService(Long teamId) {
        this.teamId = teamId;
    }

    @Override
    public List<Game> execute() {
        return getBrokerFactory().getGameBroker().findTiesByTeamId(teamId);
    }
}
