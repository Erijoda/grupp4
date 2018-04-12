/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportstats.domain;

import com.owlike.genson.annotation.JsonIgnore;
import sportstats.domain.dao.ArenaDao;
import sportstats.domain.dao.GameDao;

/**
 *
 * @author Rebecca
 */
public class Arena implements Base<ArenaDao> {
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

    @Override
    @JsonIgnore
    public ArenaDao getDao() {
        return dao;
    }

    public void setAsChild(GameDao gameDao) {
        if (dao == null) System.out.println("NULLAH!");
        gameDao.setParent(dao);
    }

    public Long getId() {
        return dao.getLongId();
    }
    
     public String getName() {
        return dao.getString("name");
    }

    public void setName(String name) {
        dao.setString("name", name);
    }
    
    public void setGame(Game game) {
        game.setAsChild(dao);
    }
    
    @Override
    public String toString() {
        return getName() + " (id: " + getId() + ")";
    }
    
     public void save() {
        dao.save();
    }
    
}
