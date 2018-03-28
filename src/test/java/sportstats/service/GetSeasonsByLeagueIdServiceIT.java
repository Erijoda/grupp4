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
public class GetSeasonsByLeagueIdServiceIT {
    
    public GetSeasonsByLeagueIdServiceIT() {
    }

    @Test
    public void testExecuteOutput() {
        System.out.println(new GetSeasonsByLeagueIdService(3L).execute());
    }
    
}
