
package sportstats.service.games;

import java.sql.Date;
import java.util.List;
import sportstats.domain.Game;
import sportstats.service.BaseService;

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
