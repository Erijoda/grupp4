/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportstats.domain;

import com.owlike.genson.annotation.JsonIgnore;
import java.sql.Date;
import sportstats.domain.broker.BrokerFactory;
import sportstats.domain.dao.ArenaDao;
import sportstats.domain.dao.GameDao;
import sportstats.domain.dao.ResultDao;
import sportstats.domain.dao.RoundDao;
import sportstats.domain.dao.TeamDao;

/**
 *
 * @author Rebecca
 */
public class Game implements Base<GameDao> {

    private final GameDao dao;

    public Game() {
        this(new GameDao());
    }

    private Game(GameDao dao) {
        this.dao = dao;
    }

    public static Game of(GameDao dao) {
        return dao == null ? null : new Game(dao);
    }

    @Override
    @JsonIgnore
    public GameDao getDao() {
        return dao;
    }

    public Long getId() {
        return dao.getLongId();
    }

    public String getName() {
        return getHomeTeam().getName() + " - " + getAwayTeam().getName();
    }

    @JsonIgnore //In favor of id
    public Team getHomeTeam() {
        return Team.of(TeamDao.findById(dao.getLong("home_team_id")));
    }

    public Long getHomeTeamId() {
        return dao.getLong("home_team_id");
    }

    public void setHomeTeam(Team team) {
        if (team.getId() == null) {
            team.save();
        }
        dao.setLong("home_team_id", team.getId());
    }

    @JsonIgnore //In favor of id
    public Team getAwayTeam() {
        return Team.of(TeamDao.findById(dao.getLong("away_team_id")));
    }

    public Long getAwayTeamId() {
        return dao.getLong("away_team_id");
    }

    public void setAwayTeam(Team team) {
        if (team.getId() == null) {
            team.save();
        }
        dao.setLong("away_team_id", team.getId());
    }
  
    @JsonIgnore //Genson can't serialize java.sql.Date
    public Date getDate() {
        return dao.getDate("date");
    }
    
    public String getGameDate() {
        return dao.getString("date");
    }
    
    public void setDate(Date date) {
        dao.setDate("date", date);
    }
  
    @JsonIgnore //To avoid loop while serializing
    public Round getRound() {
        return Round.of(dao.parent(RoundDao.class));
    }

    public Long getRoundId() {
        return dao.getLong("round_id");
    }

    public void setRound(Round round) {
        round.setAsChild(dao);
    }

    public Long getSeasonId() {
        return getRound().getSeasonId();
    }

    public Long getLeagueId() {
        return new BrokerFactory()
                .getSeasonBroker()
                .findById(getSeasonId())
                .getLeagueId();
    }

    public Result getResult() {
        return new BrokerFactory()
                .getResultBroker()
                .findByGameId(getId());
    }

    @JsonIgnore //In favor of getting the id instead
    public Arena getArena() {
        return Arena.of(dao.parent(ArenaDao.class));
    }

    public Long getArenaId() {
        return dao.getLong("arena_id");
    }

    public void setArena(Arena arena) {
        arena.setAsChild(dao);
    }
    
    public Long getSpectators() {
        return dao.getLong("spectators");
    }
    
    public void setSpectators(Long spectators) {
        dao.setLong("spectators", spectators);
    }

    public void setAsChild(ResultDao resultDao) {
        resultDao.setParent(dao);
    }

    public void setAsChild(ArenaDao arenaDao) {
        arenaDao.setParent(dao);
    }

    public void save() {
        dao.save();
    }
}
