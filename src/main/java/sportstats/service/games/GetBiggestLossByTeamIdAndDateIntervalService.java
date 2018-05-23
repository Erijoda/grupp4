/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportstats.service.games;

import java.sql.Date;
import java.util.List;
import sportstats.domain.Game;
import sportstats.service.BaseService;

/**
 *
 * @author mattkranc
 */
public class GetBiggestLossByTeamIdAndDateIntervalService extends BaseService<List<Game>> {
    
    private final Long teamId;
    private final Date fromDate;
    private final Date toDate;
    
    public GetBiggestLossByTeamIdAndDateIntervalService(Long teamId, Date fromDate, Date toDate) {
        this.teamId = teamId;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    @Override
    public List<Game> execute() {
        List<Game> result = getBrokerFactory().getGameBroker()
                .findBiggestLossByTeamIdAndDateInterval(teamId, fromDate, toDate);
        return result;
    }
    
    
}
