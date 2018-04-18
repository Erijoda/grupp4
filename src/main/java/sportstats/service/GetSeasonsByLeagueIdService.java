/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportstats.service;

import java.util.List;
import sportstats.domain.Season;


/**
 *
 * @author Rebecca
 */
public class GetSeasonsByLeagueIdService extends BaseService<List<Season>> {
    private final Long leagueId;
    
    public GetSeasonsByLeagueIdService(Long leagueId) {
        this.leagueId = leagueId;
    }
    
    @Override
    public List<Season> execute() {
        List<Season> result = getBrokerFactory()
                .getSeasonBroker().findByLeagueId(leagueId);
        return result;
    }
}
