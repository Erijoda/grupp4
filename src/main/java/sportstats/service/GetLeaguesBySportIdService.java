/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportstats.service;

import java.util.List;
import java.util.stream.Collectors;
import sportstats.db.DbConn;
import sportstats.domain.League;
import sportstats.domain.dao.LeagueDao;


/**
 *
 * @author Rebecca
 */
public class GetLeaguesBySportIdService {
    private final Long sportId;
    
    public GetLeaguesBySportIdService(Long sportId) {
        this.sportId = sportId;
    }
    
    public List<League> execute() {
        DbConn._open();
        List<League> result = LeagueDao.find("sport_id = ?", sportId).stream()
                .map(leagueDao -> new League((LeagueDao)leagueDao))
                .collect(Collectors.toList());
        DbConn._close();
        return result;
    }
}
