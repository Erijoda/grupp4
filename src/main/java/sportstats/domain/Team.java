/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportstats.domain;

import org.javalite.activejdbc.Model;
import sportstats.domain.dao.TeamDao;

/**
 *
 * @author Rebecca
 */
public class Team {
    
    private final TeamDao dao;
    
    public Team() {
        this(new TeamDao());
    }
    
    public Team(TeamDao dao) {
        this.dao = dao;
    }
    
    public Long getId() {
        return dao.getLong("id");
    }

    public String getName() {
        return dao.getString("name");
    }

    public void setName(String name) {
        dao.setString("name", name);
    }

    public void setSport(Sport sport) {
        dao.setLong("sports_id", sport.getId());
    }

    public void save() {
        dao.save();
    }
    
    @Override
    public String toString() {
        return getName() + " (id: " + getId() + ")";
    }
    
}
