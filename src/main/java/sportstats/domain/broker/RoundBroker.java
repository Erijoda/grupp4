
package sportstats.domain.broker;

import java.util.List;
import java.util.stream.Collectors;
import sportstats.domain.Round;
import sportstats.domain.dao.RoundDao;

/**
 *
 * @author mattkranc
 */
public class RoundBroker {
    
    public Round create() {
        return new Round();
    }
    
    public Round findById(Long roundId) {
        return Round.of(RoundDao.findById(roundId));
    }
    
    public List<Round> findBySeasonId(Long seasonId) {
        return RoundDao.find("season_id = ?", seasonId).stream()
                .map(roundDao -> Round.of((RoundDao)roundDao))
                .collect(Collectors.toList());
    }
}
