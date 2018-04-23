/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportstats.service;

import org.junit.Test;

/**
 *
 * @author Rebecca
 */
public class GetLeaguesBySportIdServiceIT {
    
    public GetLeaguesBySportIdServiceIT() {
    }

    @Test
    public void testExecuteOutput() {
        System.out.println(new GetLeaguesBySportIdService(3L).execute());
    }
    
}
