/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportstats.domain;

import com.owlike.genson.annotation.JsonIgnore;
import sportstats.domain.dao.GameDao;
import sportstats.domain.dao.ResultDao;

/**
 *
 * @author Rebecca
 */
public class Result implements Base<ResultDao> {
    private final ResultDao dao;
    
    public Result() {
        this(new ResultDao());
    }
    
    private Result(ResultDao dao) {
        this.dao = dao;
    }
    
    public static Result of(ResultDao dao) {
        return dao == null ? null : new Result(dao);
    }
    
    @Override
    @JsonIgnore
    public ResultDao getDao() {
        return dao;
    }
    
    @JsonIgnore
    public Game getGame() {
        return Game.of(dao.parent(GameDao.class));
    }
    
    public Long getGameId() {
        return dao.getLong("game_id");
    }
    
    public void setGame(Game game) {
        game.setAsChild(dao);
    }
    
    public Long getScoreAwayTeam() {
        return dao.getLong("score_away_team");
    }
    
    public void setScoreAwayTeam(Long scoreAwayTeam) {
        dao.setLong("score_away_team", scoreAwayTeam);
    }
    
    public Long getScoreHomeTeam() {
        return dao.getLong("score_home_team");
    }
    
    public void setScoreHomeTeam(Long scoreHomeTeam) {
        dao.setLong("score_home_team", scoreHomeTeam);
    }
    
    public WinType getWinType() {
        return dao.getWinType();
    }
    
    public void setWinType(WinType winType) {
        dao.setWinType(winType);
    }
    
    public String getResult() {
        String normalResult =
                String.valueOf(getScoreHomeTeam())
                + " - "
                + String.valueOf(getScoreAwayTeam());
        
        switch (getWinType()) {
            case OVERTIME:
                return normalResult + " OT";
            case SHOOTOUT:
                return normalResult + " SO";
        }
        
        return normalResult;
    }

    public void save() {
        dao.save();
    }
}
