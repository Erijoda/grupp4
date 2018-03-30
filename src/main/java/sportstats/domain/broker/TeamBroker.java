package sportstats.domain.broker;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.List;
import java.util.stream.Collectors;
import sportstats.domain.Team;
import sportstats.domain.dao.TeamDao;

/**
 *
 * @author thomas
 */
public class TeamBroker {

    public Team create() {
        return new Team();
    }
    
    public Team findById(Long teamId) {
        return Team.of(TeamDao.findById(teamId));
    }
    
    public List<Team> findBySportId(Long sportId) {
        return TeamDao.find("sport_id = ?", sportId).stream()
                .map(teamDao -> Team.of((TeamDao)teamDao))
                .collect(Collectors.toList());
    }
    
}
