/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportstats.service;

import sportstats.db.DbConn;
import sportstats.domain.Sport;
import sportstats.domain.League;
import sportstats.domain.broker.BrokerFactory;

/**
 *
 * @author Rebecca
 */
public class AddLeagueService {
    private final String name;
    private final Long sportId;
    private DbConn conn;
    private BrokerFactory brokerFactory;
    
    
    
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
    
    public void init(DbConn conn, BrokerFactory brokerFactory) {
        this.conn = conn;
        this.brokerFactory = brokerFactory;
    }
    
    public League execute() {
        conn.open();
        League league = brokerFactory.getLeagueBroker().create();
        league.setName(name);
        Sport sport = brokerFactory.getSportBroker().findById(sportId);
        if (sport == null) {
            throw new SportstatsServiceException("No sport with given ID");
        }
        league.setSport(sport);
        league.save();
        conn.close();
        return league;
    }
    
}
