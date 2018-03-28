/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportstats.domain;

import static java.lang.Long.getLong;
import org.javalite.activejdbc.Model;
import sportstats.domain.dao.ArenaDao;
import sportstats.domain.dao.GameDao;
import sportstats.domain.dao.RoundDao;
import sportstats.domain.dao.TableDao;
import sportstats.domain.dao.TeamDao;

/**
 *
 * @author Rebecca
 */
public class Game {
    private final GameDao dao;
    
    public Game() {
        this(new GameDao());
    }
    
    public Game(GameDao dao) {
        this.dao = dao;
    }
    
    public String getName() {
        return dao.getString("name");
    }
    
    public void setName(String name) {
        dao.setString("name", name);
    }
    
    public Team getHomeTeam() {
        return new Team(TeamDao.findById(dao.getLong("home_team_id")));
    }

    public Team getAwayTeam() {
        return new Team(TeamDao.findById(dao.getLong("away_team_id")));
    }

    public void setHomeTeam(Team homeTeam) {
        if (homeTeam.getId() == null) {
            homeTeam.save();
        }
        dao.setLong("home_team_id", homeTeam.getId());
    }
    
    public RoundDao getRound() {
        return dao.parent(RoundDao.class);
    }
    
    public void setRound(RoundDao round) {
        dao.setParent(round);
    }
    
    public ArenaDao getArena() {
        return dao.parent(ArenaDao.class);
    }
    
    public void setArena(ArenaDao arena) {
        dao.setParent(arena);
    }
    
    public TableDao getTable() {
        return dao.parent(TableDao.class);
    }
    
    public void setTable(TableDao table) {
        dao.setParent(table);
    }
}   
    
