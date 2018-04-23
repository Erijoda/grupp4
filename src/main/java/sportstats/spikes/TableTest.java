/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportstats.spikes;

import java.util.List;
import java.util.Map;
import sportstats.service.ServiceRunner;
import sportstats.service.tables.GetTableBySeasonId;

/**
 *
 * @author Davik
 */
public class TableTest {
    public static void main(String[] args) {
        /*List<Map> result = new ServiceRunner<>(new GetTableBySeasonId(1L))
                .executeWithoutJson();
        
        for(Map tableResult : result) {
            if (tableResult.get("season_id") instanceof Long) {
                System.out.println("season_id is Long");
            }
            if (tableResult.get("season_id") instanceof String) {
                System.out.println("season_id is String");
            }
            System.out.println(tableResult.get("season_id").getClass());
            System.out.println(tableResult.get("team_name"));
        }*/
    }
}
