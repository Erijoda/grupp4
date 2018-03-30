/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportstats.domain.broker;

import java.util.List;
import java.util.stream.Collectors;
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

    public List<League> findBySportId(Long sportId) {
        return LeagueDao.find("sport_id = ?", sportId).stream()
                .map(leagueDao -> new League((LeagueDao) leagueDao))
                .collect(Collectors.toList());
    }

}
