/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportstats.domain;

import sportstats.domain.dao.ArenaDao;
import sportstats.domain.dao.GameDao;

/**
 *
 * @author Rebecca
 */
public class Arena {
    private final ArenaDao dao;
    
    public Arena() {
        this(new ArenaDao());
    }
    
    private Arena(ArenaDao dao) {
        this.dao = dao;
    }
    
    public static Arena of(ArenaDao dao) {
        return dao == null ? null : new Arena(dao);
    }

    public void setAsChild(GameDao gameDao) {
        gameDao.setParent(dao);
    }
    
}
