/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportstats.domain;

import com.owlike.genson.annotation.JsonIgnore;
import sportstats.domain.dao.TeamDao;

/**
 *
 * @author Rebecca
 */
public class Team implements Base<TeamDao> {
    
    private final TeamDao dao;
    
    public Team() {
        this(new TeamDao());
    }
    
    private Team(TeamDao dao) {
        this.dao = dao;
    }
    
    public static Team of(TeamDao dao) {
        return dao == null ? null : new Team(dao);
    }
    
    @Override
    @JsonIgnore
    public TeamDao getDao() {
        return dao;
    }
    
    public Long getId() {
        return dao.getLongId();
    }

    public String getName() {
        return dao.getString("name");
    }

    public void setName(String name) {
        dao.setString("name", name);
    }

    public void setSport(Sport sport) {
        sport.setAsChild(dao);
    }

    public void save() {
        dao.save();
    }
    
    public void addToSeason(Season season) {
        season.addTeam(dao);
    }
}
