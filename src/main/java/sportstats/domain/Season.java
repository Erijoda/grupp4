/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportstats.domain;

import sportstats.domain.dao.SeasonDao;

/**
 *
 * @author Rebecca
 */
public class Season {
    
    private final SeasonDao dao; 
    
    public Season() {
        this(new SeasonDao());
    }
    
    public Season(SeasonDao dao) {
        this.dao = dao;
    }
    
    public Integer getYear() {
        return dao.getInteger("year");
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
        dao.setLong("id_leagues", league.getId());
    }
    
    public void save() {
        dao.save();
    }
    
    public String getName() {
        return String.valueOf(getYear()) +
                (isSummer() ? "" : " - " + (getYear() + 1));
    }
}
