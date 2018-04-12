/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportstats.spikes;

import java.util.UUID;
import sportstats.service.AddSportService;
import sportstats.service.GetAllSportsService;
import sportstats.service.AddArenaService;
import sportstats.service.ServiceRunner;

/**
 *
 * @author davik
 */
public class TestElastx {
    public static void main(String[] args) {
        String random = UUID.randomUUID().toString();
        System.out.println(new ServiceRunner<>(new AddSportService(random)).execute());
        System.err.println(new ServiceRunner<>(new GetAllSportsService()).execute());
        //System.out.println(new ServiceRunner<>(new AddArenaService("Strömvallen",1L)).execute());
        AddArenaService addArena = new AddArenaService("Friends",1L);
        System.out.println(new ServiceRunner<>(addArena).execute());
       //System.out.println(new ServiceRunner<>(new AddArenaService("Strömvallen",1L)).execute());
        
    
    }
}
