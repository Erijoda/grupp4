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
    
    public Result(ResultDao dao) {
        this.dao = dao;
    }
    
    public GameDao getGame() {
        return dao.parent(GameDao.class);
    }
    
    public void setGame(GameDao game) {
        dao.setParent(game);
    }
    
    public Integer getScoreAwayTeam() {
        return dao.getInteger("score_away_team");
    }
    
    public void setScoreAwayTeam(Integer score_away_team) {
        dao.setInteger("score_away_team", score_away_team);
    }
}
