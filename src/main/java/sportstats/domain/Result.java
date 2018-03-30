/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportstats.domain;

import sportstats.domain.dao.GameDao;
import sportstats.domain.dao.ResultDao;

/**
 *
 * @author Rebecca
 */
public class Result {
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
    
    public Game getGame() {
        return Game.of(dao.parent(GameDao.class));
    }
    
    public void setGame(Game game) {
        game.setAsChild(dao);
    }
    
    public Long getScoreAwayTeam() {
        return dao.getLong("score_away_team");
    }
    
    public void setScoreAwayTeam(Long score_away_team) {
        dao.setInteger("score_away_team", score_away_team);
    }
    
    public Long getScoreHomeTeam() {
        return dao.getLong("score_home_team");
    }
    
    public void setScoreHomeTeam(Long score_away_team) {
        dao.setLong("score_away_team", score_away_team);
    }
}
