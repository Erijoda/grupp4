/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportstats.service;

import sportstats.domain.Arena;
import sportstats.domain.Game;


public class AddArenaService extends BaseService<Arena> {
    
    private final String name;
    private final Long gameId;
    
    public AddArenaService(String name, Long gameId) {
        if (name == null) {
            throw new SportstatsServiceException("Name should not be null");
        }
        if (gameId == null) {
            throw new SportstatsServiceException("Id for game should not be null");
        }
        this.name = name;
        this.gameId = gameId;
    }
    
    @Override
    public Arena execute() {
        
        Arena arena = getBrokerFactory().getArenaBroker().findByName(name);
        if (arena == null)
        {
            arena = getBrokerFactory().getArenaBroker().create();
            arena.setName(name);
        }        
        
        Game game = getBrokerFactory().getGameBroker().findById(gameId);
        if (game == null) {
            throw new SportstatsServiceException("No game with given ID");
        }
        arena.save();
        
        game.setArena(arena);
        game.save();
        
        return arena;
    }
}
    

