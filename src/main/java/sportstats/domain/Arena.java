/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportstats.domain;

import sportstats.domain.dao.ArenaDao;

/**
 *
 * @author Rebecca
 */
public class Arena {
    private final ArenaDao dao;
    
    public Arena() {
        this(new ArenaDao());
    }
    
    public Arena(ArenaDao dao) {
        this.dao = dao;
    }
    
}
