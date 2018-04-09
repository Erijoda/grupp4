/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportstats.domain;

import com.owlike.genson.annotation.JsonIgnore;
import java.util.List;
import java.util.stream.Collectors;
import sportstats.domain.dao.GameDao;
import sportstats.domain.dao.RoundDao;
import sportstats.domain.dao.SeasonDao;

/**
 *
 * @author Rebecca
 */
public class Round implements Base<RoundDao> {
    private final RoundDao dao;
    
    public Round() {
        this(new RoundDao());
    }
    
    private Round(RoundDao dao) {
        this.dao = dao;
    }
    
    public static Round of(RoundDao dao) {
        return dao == null ? null : new Round(dao);
    }
    
    @Override
    @JsonIgnore
    public RoundDao getDao() {
        return dao;
    }
    
    public Long getSeasonId() {
        return dao.getLong("season_id");
    }
    
    @JsonIgnore
    public Season getSeason() {
        return Season.of(dao.parent(SeasonDao.class));
    }
    
    public void setSeason(Season season) {
        season.setAsChild(dao);
    }
    
    public void setAsChild(GameDao gameDao) {
        gameDao.setParent(dao);
    }
    
    public Long getId() {
        return dao.getLongId();
    }
    
    @Override
    public String toString() {
        return "Id: " + getId();
    }
    
    public void save() {
        dao.save();
    }
    
    public List<Game> getGames() {
        return dao.getAll(GameDao.class).stream()
                .map((gameDao) -> Game.of(gameDao))
                .collect(Collectors.toList());
    }
    
}
