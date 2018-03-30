/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportstats.service;

import java.util.List;
import sportstats.domain.League;


/**
 *
 * @author Rebecca
 */
public class GetLeaguesBySportIdService extends BaseService<List<League>> {
    private final Long sportId;
    
    public GetLeaguesBySportIdService(Long sportId) {
        this.sportId = sportId;
    }
    
    @Override
    public List<League> execute() {
        List<League> result = getBrokerFactory()
                .getLeagueBroker().findBySportId(sportId);
        return result;
    }
}
