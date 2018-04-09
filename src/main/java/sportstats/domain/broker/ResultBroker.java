/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportstats.domain.broker;

import sportstats.domain.Result;
import sportstats.domain.dao.ResultDao;

/**
 *
 * @author Davik
 */
public class ResultBroker {

    public Result create() {
        return new Result();
    }
    
    public Result findByGameId(Long gameId) {
        return Result.of(ResultDao.findFirst("game_id = ?", gameId));
    }
    
}
