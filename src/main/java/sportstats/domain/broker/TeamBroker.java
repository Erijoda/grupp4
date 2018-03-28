package sportstats.domain.broker;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import sportstats.domain.Team;
import sportstats.domain.dao.TeamDao;

/**
 *
 * @author thomas
 */
public class TeamBroker {

    public Team create() {
        return new Team(new TeamDao());
    }
    
    public Team findById(Long teamId) {
        return new Team(TeamDao.findById(teamId));
    }
    
}
