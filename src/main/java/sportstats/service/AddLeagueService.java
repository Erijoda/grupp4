/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportstats.service;

import sportstats.domain.League;
import sportstats.domain.Sport;

/**
 *
 * @author Rebecca
 */
public class AddLeagueService extends BaseService<League> {
    private final String name;
    private final Long sportId;
    
    public AddLeagueService(String name, Long sportId) {
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
    public League execute() {
        League league = getBrokerFactory().getLeagueBroker().create();
        league.setName(name);
        
        Sport sport = getBrokerFactory().getSportBroker().findById(sportId);
        if (sport == null) {
            throw new SportstatsServiceException("No sport with given ID");
        }
        league.setSport(sport);
        
        league.save();
        
        return league;
    }
    
}
