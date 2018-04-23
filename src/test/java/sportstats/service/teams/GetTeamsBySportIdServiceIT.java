package sportstats.service.teams;

import sportstats.service.teams.GetTeamsBySportIdService;
import org.junit.Test;

public class GetTeamsBySportIdServiceIT {
    
    public GetTeamsBySportIdServiceIT() {
    }

    @Test
    public void testExecuteOutput() {
        System.out.println(new GetTeamsBySportIdService(3L).execute());
    }
}
