/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportstats.domain;

import sportstats.domain.dao.RoundDao;
import sportstats.domain.dao.SeasonDao;
import sportstats.domain.dao.TeamDao;

/**
 *
 * @author Rebecca
 */
public class Season {
    
    private final SeasonDao dao; 
    
    public Season() {
        this(new SeasonDao());
    }
    
    private Season(SeasonDao dao) {
        this.dao = dao;
    }
    
    public static Season of(SeasonDao dao) {
        return dao == null ? null : new Season(dao);
    }
    
    public Long getId() {
        return dao.getLongId();
    }
    
    public Long getYear() {
        return dao.getLong("year");
    }
    
    public void setYear(Long year) {
        dao.setLong("year", year);
    }
    
    public Boolean isSummer() {
        return dao.getBoolean("summer");
    }
    
    public  void setSummer(Boolean summer) {
        dao.setBoolean("summer", summer);
    }
    
    public void setLeague(League league) {
        league.setAsChild(dao);
    }
    
    public void save() {
        dao.save();
    }
    
    public String getName() {
        return String.valueOf(getYear()) +
                (isSummer() ? "" : " - " + (getYear() + 1));
    }

    public void setAsChild(RoundDao roundDao) {
        roundDao.setParent(dao);
    }
    
    public void addTeam(TeamDao teamDao) {
        dao.add(teamDao);
    }
}
