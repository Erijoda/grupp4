/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportstats.service;

import sportstats.domain.League;
import sportstats.domain.Season;

/**
 *
 * @author Rebecca
 */
public class AddSeasonService extends BaseService<Season> {
    private final Long year;
    private final Long leagueId;
    private final Boolean summer;
    
    public AddSeasonService(Long year, Boolean summer, Long leagueId) {
        if (year == null) {
            throw new SportstatsServiceException("Year should not be null");
        }
        if (leagueId == null) {
            throw new SportstatsServiceException("Id for league should not be null");
        }
        this.year = year;
        this.leagueId = leagueId;
        this.summer = summer;
    }
    
    @Override
    public Season execute() {
        Season season = getBrokerFactory().getSeasonBroker().create();
        season.setYear(year);
        season.setSummer(summer);
        
        League league = getBrokerFactory().getLeagueBroker().findById(leagueId);
        if (league == null) {
            throw new SportstatsServiceException("No league with given ID");
        }
        season.setLeague(league);
        
        season.save();
        
        return season;
    }
}
