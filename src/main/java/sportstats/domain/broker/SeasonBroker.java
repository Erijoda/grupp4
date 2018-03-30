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
        return new Season(new SeasonDao());
    }

    public Season findById(Long seasonId) {
        return new Season(SeasonDao.findById(seasonId));
    }

    public List<Season> findByLeagueId(Long leagueId) {
        return SeasonDao.find("league_id = ?", leagueId).stream()
                .map(seasonDao -> new Season((SeasonDao)seasonDao))
                .collect(Collectors.toList());
    }
    
}
