package sportstats.service;

import java.util.List;
import java.util.stream.Collectors;
import sportstats.db.DbConn;
import sportstats.domain.Team;
import sportstats.domain.dao.TeamDao;

public class GetTeamsBySportIdService extends BaseService<List<Team>> {

    private final Long sportId;
    
    public GetTeamsBySportIdService(Long sportId) {
        this.sportId = sportId;
    }
        
    @Override
    public List<Team> execute() {
        List<Team> result = TeamDao.find("sports_id = ?", sportId).stream()
                .map(teamDao -> new Team((TeamDao)teamDao))
                .collect(Collectors.toList());
        return result;
    }
}
