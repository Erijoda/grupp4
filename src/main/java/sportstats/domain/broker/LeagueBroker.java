/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportstats.domain.broker;

import sportstats.domain.League;
import sportstats.domain.dao.LeagueDao;

/**
 *
 * @author Rebecca
 */
public class LeagueBroker {
    public League create() {
        return new League(new LeagueDao());
    }
    
    public League findById(Long leagueId) {
        return new League(LeagueDao.findById(leagueId));
    }
    
}
