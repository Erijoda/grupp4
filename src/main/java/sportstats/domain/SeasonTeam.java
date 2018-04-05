/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportstats.domain;

import sportstats.domain.dao.SeasonTeamDao;

/**
 *
 * @author Davik
 */
public class SeasonTeam {
    private final SeasonTeamDao dao;
    
    public SeasonTeam() {
        dao = new SeasonTeamDao();
    }
    
    private SeasonTeam(SeasonTeamDao dao) {
        this.dao = dao;
    }
    
    public static SeasonTeam of(SeasonTeamDao dao) {
        return dao == null ? null : new SeasonTeam(dao);
    }
    
    public Long getSeasonId() {
        return dao.getLong("season_id");
    }
    
    public Long getTeamId() {
        return dao.getLong("team_id");
    }
}
