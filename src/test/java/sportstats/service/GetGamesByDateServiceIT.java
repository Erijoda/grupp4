/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportstats.service;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Rebecca
 */
public class GetGamesByDateServiceIT {
    
    public GetGamesByDateServiceIT() {
        java.util.Date myDate = new java.util.Date("2018-04-12");
        java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
        //java.sql.Date date = new java.sql.Date(2018, 04, 12);
        System.out.println(new GetGamesByDateService(sqlDate).execute());
    }

 
    
}
