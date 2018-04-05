/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportstats.domain.broker;

import java.util.List;
import java.util.stream.Collectors;
import sportstats.domain.Game;
import sportstats.domain.dao.GameDao;

/**
 *
 * @author Davik
 */
public class GameBroker {
    
    public Game create() {
        return new Game();
    }
    
    public List<Game> findByRoundId(Long roundId) {
        return GameDao.find("round_id = ?", roundId).stream()
                .map(gameDao -> Game.of((GameDao) gameDao))
                .collect(Collectors.toList());
    }
}
