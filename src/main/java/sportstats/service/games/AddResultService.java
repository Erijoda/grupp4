/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportstats.service.games;

import sportstats.domain.WinType;
import sportstats.domain.Game;
import sportstats.domain.Result;
import sportstats.service.BaseService;
import sportstats.service.SportstatsServiceException;

/**
 *
 * @author Davik
 */
public class AddResultService extends BaseService<Result> {
    private final Long gameId;
    private final Long scoreHomeTeam;
    private final Long scoreAwayTeam;
    private final WinType winType;
    
    public AddResultService(Long gameId, Long scoreHomeTeam, Long scoreAwayTeam) {
        this(gameId, scoreHomeTeam, scoreAwayTeam, null);
    }
    
    public AddResultService(Long gameId, Long scoreHomeTeam, Long scoreAwayTeam, WinType winType) {
        if (gameId == null) {
            throw new SportstatsServiceException("GameId should not be null");
        }
        if(scoreHomeTeam == null) {
            throw new SportstatsServiceException("ScoreHomeTeam should not be null");
        }
        if(scoreAwayTeam == null) {
            throw new SportstatsServiceException("ScoreAwayTeam should not be null");
        }
        this.gameId = gameId;
        this.scoreHomeTeam = scoreHomeTeam;
        this.scoreAwayTeam = scoreAwayTeam;
        this.winType = winType == null ? WinType.NORMAL : winType; //NORMAL is default
    }

    @Override
    public Result execute() {
        Result newResult = getBrokerFactory().getResultBroker().create();
        
        newResult.setScoreAwayTeam(scoreAwayTeam);
        newResult.setScoreHomeTeam(scoreHomeTeam);
        newResult.setWinType(winType);
        
        Game game = getBrokerFactory().getGameBroker().findById(gameId);
        if (game == null) {
            throw new SportstatsServiceException("A game with the given id does not exist");
        }
        
        newResult.setGame(game);
        newResult.save();
        
        return newResult;
    }
}
