/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportstats.domain;

import com.owlike.genson.annotation.JsonIgnore;
import sportstats.domain.dao.SeasonTeamDao;

/**
 *
 * @author Davik
 */
public class SeasonTeam implements Base<SeasonTeamDao> {
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
    
    @Override
    @JsonIgnore
    public SeasonTeamDao getDao() {
        return dao;
    }
    
    public Long getSeasonId() {
        return dao.getLong("season_id");
    }
    
    public Long getTeamId() {
        return dao.getLong("team_id");
    }
}
