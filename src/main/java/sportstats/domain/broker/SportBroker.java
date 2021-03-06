/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportstats.domain.broker;

import java.util.List;
import java.util.stream.Collectors;
import sportstats.domain.Sport;
import sportstats.domain.dao.SportDao;

/**
 *
 * @author Rebecca
 */
public class SportBroker {
    public List<Sport> findAll() {
        return SportDao.findAll().stream()
                .map(dao -> Sport.of((SportDao) dao))
                .collect(Collectors.toList());
    }
    
    public Sport findById(Long sportId) {
        return Sport.of(SportDao.findById(sportId));
    }
    
    public Sport create() {
        return new Sport();
    }
}


