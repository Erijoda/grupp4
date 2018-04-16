/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportstats.service;

import java.sql.Date;
import java.util.List;
import sportstats.domain.Game;

/**
 *
 * @author Rebecca
 */
public class GetGamesByDateService extends BaseService<List<Game>> {
    private final Date date;
    
    public GetGamesByDateService(Date date) {
        this.date = date;
    }
    
    @Override
    public List<Game> execute() {
        List<Game> games = getBrokerFactory().getGameBroker()
                .findByDate(date);
        return games;                     
    }
    
}
