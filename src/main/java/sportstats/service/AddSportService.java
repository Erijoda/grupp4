/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportstats.service;

import sportstats.domain.Sport;

/**
 *
 * @author Rebecca
 */
public class AddSportService extends BaseService<Sport> {
    private final String sportName;
    
    public AddSportService(String sportName) {
        if(sportName == null) {
            throw new SportstatsServiceException("Name should not be null");
        }
        this.sportName = sportName;
    }
    
    
    @Override
    public Sport execute() {
        Sport sport = getBrokerFactory().getSportBroker().create();
        sport.setName(sportName);
        
        sport.save();
        return sport;
    }
}
