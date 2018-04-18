/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportstats.spikes;

import com.owlike.genson.Genson;
import sportstats.rest.shapes.ResultShape;


/**
 *
 * @author davik
 */
public class TestElastx {
    public static void main(String[] args) {
        //String random = UUID.randomUUID().toString();
        //System.out.println(new ServiceRunner<>(new AddSportService(random)).execute());
        //System.err.println(new ServiceRunner<>(new GetAllSportsService()).execute());
    
    /*
        System.out.println(new ServiceRunner<>(new GetGamesBySeasonIdService(1L))
                .execute());
        
        System.out.println(new ServiceRunner<>(new GetGamesByRoundIdService(4L))

                .execute());
*/
        //AddArenaService addArena = new AddArenaService("Friends",2L);
        //System.out.println(new ServiceRunner<>(addArena).execute());
        /*AddSpectatorsService addSpectators = new AddSpectatorsService(500L, 2L);
        System.out.println(new ServiceRunner<>(addSpectators).execute());*/
        
        String one = "{ \"gameId\": 1, \"scoreHomeTeam\": 4, \"scoreAwayTeam\": 3 }";
        String two = "{ \"gameId\": 1, \"scoreHomeTeam\": 4, \"scoreAwayTeam\": 3, \"winType\": \"OVERTIME\" }";
        
        
        ResultShape withoutWinType = new Genson().deserialize(one, ResultShape.class);
        ResultShape withWinType = new Genson().deserialize(two, ResultShape.class);
        System.out.println("Without - WinType is: " + withoutWinType.winType);
        System.out.println("With - WinType is: " + withWinType.winType);
        }
    
}
