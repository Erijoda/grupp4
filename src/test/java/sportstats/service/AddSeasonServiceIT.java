/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportstats.service;

/**
 *
 * @author Rebecca
 */
public class AddSeasonServiceIT {
    
    public AddSeasonServiceIT() {
    }

    /*@Test
    public void runService() {
        List<League> leagues = new GetLeaguesBySportIdService().execute();
        String randomSeason = UUID.randomUUID().toString();
        AddSeasonService service = new AddSeasonService(randomSeason, leagues.get(0).getId());
        service.init(new DbConn(), new BrokerFactory());
        service.execute();
    }*/
}
