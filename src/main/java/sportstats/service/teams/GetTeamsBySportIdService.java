package sportstats.service.teams;

import java.util.List;
import sportstats.domain.Team;
import sportstats.service.BaseService;

public class GetTeamsBySportIdService extends BaseService<List<Team>> {
    private final Long sportId;
    
    public GetTeamsBySportIdService(Long sportId) {
        this.sportId = sportId;
    }
        
    @Override
    public List<Team> execute() {
        List<Team> result = getBrokerFactory()
                .getTeamBroker().findBySportId(sportId);
        return result;
    }
}
