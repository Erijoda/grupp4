/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportstats.service;

import java.util.List;
import java.util.stream.Collectors;
import sportstats.db.DbConn;
import sportstats.domain.Season;
import sportstats.domain.dao.SeasonDao;


/**
 *
 * @author Rebecca
 */
public class GetSeasonsByLeagueIdService {
    private final Long leagueId;
    
    public GetSeasonsByLeagueIdService(Long leagueId) {
        this.leagueId = leagueId;
    }
    
    
    public List<Season> execute() {
        DbConn._open();
        List<Season> result = SeasonDao.find("id_leagues = ?", leagueId).stream()
                .map(seasonDao -> new Season((SeasonDao)seasonDao))
                .collect(Collectors.toList());
        DbConn._close();
        return result;
    }
}
