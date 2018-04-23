/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportstats.service.seasons;

import sportstats.domain.Season;
import sportstats.domain.SeasonTeam;
import sportstats.domain.Team;
import sportstats.service.BaseService;
import sportstats.service.SportstatsServiceException;

/**
 *
 * @author Davik
 */
public class AddTeamToSeasonService extends BaseService<SeasonTeam> {
    private final Long teamId;
    private final Long seasonId;
    
    public AddTeamToSeasonService(Long teamId, Long seasonId) {
        if (teamId == null) {
            throw new SportstatsServiceException("TeamId should not be null");
        }
        if (seasonId == null) {
            throw new SportstatsServiceException("SeasonId should not be null");
        }
        this.teamId = teamId;
        this.seasonId = seasonId;
    }

    @Override
    public SeasonTeam execute() {
        Team team = getBrokerFactory().getTeamBroker().findById(teamId);
        if (team == null) {
            throw new SportstatsServiceException("A team with the given id does not exist");
        }
        Season season = getBrokerFactory().getSeasonBroker().findById(seasonId);
        if (season == null) {
            throw new SportstatsServiceException("A season with the given id does not exist");
        }
        
        team.addToSeason(season);
        
        return getBrokerFactory()
                .getSeasonTeamBroker().findByIds(teamId, seasonId);
    }
    
}
