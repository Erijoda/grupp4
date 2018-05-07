/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportstats.service;

import sportstats.db.DbConn;
import sportstats.domain.broker.BrokerFactory;
import sportstats.rest.json.JsonOutputFormatter;

/**
 *
 * @author Rebecca
 */
public class ServiceRunner<T> {

    private final SportstatsService<T> service;

    public ServiceRunner(SportstatsService<T> service) {
        this.service = service;
    }

    public String execute() {
        DbConn dbConn = new DbConn();
        
        service.init(new BrokerFactory());
        try {
            dbConn.open();
            return new JsonOutputFormatter().createOutput(service.execute());
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
            throw ex;
        } finally {
            dbConn.close();
        }
    }
    
    public T executeWithoutJson() {
        DbConn dbConn = new DbConn();
        
        service.init(new BrokerFactory());
        try {
            dbConn.open();
            
            return service.execute();
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
            throw ex;
        } finally {
            dbConn.close();
        }
    }
}
