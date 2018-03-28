/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportstats.service;

import sportstats.db.DbConn;
import sportstats.domain.League;
import sportstats.domain.Season;
import sportstats.domain.broker.BrokerFactory;

/**
 *
 * @author Rebecca
 */
public class AddSeasonService {
    private final Long year;
    private final Long leagueId;
    private final boolean summer;
    private DbConn conn;
    private BrokerFactory brokerFactory;
    
    public AddSeasonService(Long year, boolean summer, Long leagueId) {
        //if (year == null) {
        //    throw new SportstatsServiceException("Year should not be null");
        //}
        //if (leagueId == null) {
        //    throw new SportstatsServiceException("Id for sport should not be null");
        //}
        this.year = year;
        this.leagueId = leagueId;
        this.summer = summer;
    }
    
    public void init(DbConn conn, BrokerFactory brokerFactory) {
        this.conn = conn;
        this.brokerFactory = brokerFactory;
    }
    
    public Season execute() {
        conn.open();
        Season season = brokerFactory.getSeasonBroker().create();
        season.setYear(year);
        season.setSummer(summer);
        League league = brokerFactory.getLeagueBroker().findById(leagueId);
        if (league == null) {
            throw new SportstatsServiceException("No sport with given ID");
        }
        season.setLeague(league);
        season.save();
        conn.close();
        return season;
    }
}
