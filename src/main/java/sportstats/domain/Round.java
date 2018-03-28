/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportstats.domain;

import sportstats.domain.dao.RoundDao;
import sportstats.domain.dao.SeasonDao;

/**
 *
 * @author Rebecca
 */
public class Round {
    private final RoundDao dao;
    
    public Round() {
        this(new RoundDao());
    }
    
    public Round(RoundDao dao) {
        this.dao = dao;
    }
    
    public SeasonDao getSeason() {
        return dao.parent(SeasonDao.class);
    }
    
    public void setSeason(SeasonDao season) {
        dao.setParent(season);
    }
    
}
