package sportstats.service;

import sportstats.domain.Sport;
import sportstats.domain.Team;

public class AddTeamService extends BaseService<Team> {

    private final String name;
    private final Long sportId;
    
    public AddTeamService(String name, Long sportId) {
        if (name == null) {
            throw new SportstatsServiceException("Name should not be null");
        }
        if (sportId == null) {
            throw new SportstatsServiceException("Id for sport should not be null");
        }
        this.name = name;
        this.sportId = sportId;
    }
    
    @Override
    public Team execute() {
        Team team = getBrokerFactory().getTeamBroker().create();
        team.setName(name);
        Sport sport = getBrokerFactory().getSportBroker().findById(sportId);
        if (sport == null) {
            throw new SportstatsServiceException("No sport with given ID");
        }
        team.setSport(sport);
        team.save();
        return team;
    }
}
