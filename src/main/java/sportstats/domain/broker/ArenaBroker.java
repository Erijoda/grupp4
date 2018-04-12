/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportstats.domain.broker;

import sportstats.domain.Arena;
import sportstats.domain.dao.ArenaDao;



public class ArenaBroker {
    
    public Arena create() {
        return new Arena();
    }
    /*
    public Arena findById(Long arenaId) {
        return Arena.of(ArenaDao.findById(arenaId));
    }
*/
    
     public Arena findByGameId(Long gameId) {
        return Arena.of(ArenaDao.findFirst("game_id = ?", gameId));
    }
     
     public Arena findByName(String name) {
         return Arena.of(ArenaDao.findFirst("name=?", name));
     }
    
}

   
