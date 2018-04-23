/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportstats.service.games;

import sportstats.domain.Game;
import sportstats.service.BaseService;
import sportstats.service.SportstatsServiceException;

/**
 *
 * @author apon
 */
public class AddSpectatorsService extends BaseService<Game> {
    
    private final Long spectators;
    private final Long gameId;
    
    public AddSpectatorsService(Long spectators, Long gameId) {
        if (spectators == null) {
            throw new SportstatsServiceException("spectators should not be null");
        }
        if (gameId == null) {
            throw new SportstatsServiceException("Id for game should not be null");
        }
        this.spectators = spectators;
        this.gameId = gameId;
    }
    
    @Override
    public Game execute() {
        
        Game game = getBrokerFactory().getGameBroker().findById(gameId);
        if (game == null) {
            throw new SportstatsServiceException("No game with given ID");
        }
        
        game.setSpectators(spectators);
        game.save();
        
        return game;
    }
    
}
