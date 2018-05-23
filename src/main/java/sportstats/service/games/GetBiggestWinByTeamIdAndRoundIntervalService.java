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
 * @author mattkranc
 */
public class GetBiggestWinByTeamIdAndRoundIntervalService extends BaseService<List<Game>> {
    
    private final Long teamId;
    private final Long fromRound;
    private final Long toRound;
    
    public GetBiggestWinByTeamIdAndRoundIntervalService(Long teamId, Long fromRound, Long toRound) {
        this.teamId = teamId;
        this.fromRound = fromRound;
        this.toRound = toRound;
    }
    
    @Override
    public List<Game> execute() {
        List<Game> result = getBrokerFactory().getGameBroker()
                .findBiggestWinByTeamIdAndRoundInterval(teamId, fromRound, toRound);
        return result;
    }
    
}
