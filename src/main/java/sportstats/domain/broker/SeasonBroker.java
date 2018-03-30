/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportstats.domain.broker;

import java.util.List;
import java.util.stream.Collectors;
import sportstats.domain.Season;
import sportstats.domain.dao.SeasonDao;

/**
 *
 * @author Rebecca
 */
public class SeasonBroker {

    public Season create() {
        return new Season();
    }

    public Season findById(Long seasonId) {
        return Season.of(SeasonDao.findById(seasonId));
    }

    public List<Season> findByLeagueId(Long leagueId) {
        return SeasonDao.find("league_id = ?", leagueId).stream()
                .map(seasonDao -> Season.of((SeasonDao)seasonDao))
                .collect(Collectors.toList());
    }
    
}
