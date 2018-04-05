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
        
        try {
            dbConn.open();
            
            return new JsonOutputFormatter().createOutput(executeWithoutJson());
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
            throw ex;
        } finally {
            dbConn.close();
        }
    }
    
    protected T executeWithoutJson() {
        DbConn dbConn = null;
        
        service.init(new BrokerFactory());
        try {
            if (!DbConn.hasConnection()) {
                dbConn = new DbConn();
                dbConn.open();
            }
            
            return service.execute();
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
            throw ex;
        } finally {
            if(dbConn != null) dbConn.close();
        }
    }
}
