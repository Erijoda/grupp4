package sportstats.service;

import org.junit.Test;
import static org.junit.Assert.*;

public class GetTeamsBySportIdServiceIT {
    
    public GetTeamsBySportIdServiceIT() {
    }

    @Test
    public void testExecuteOutput() {
        System.out.println(new GetTeamsBySportIdService(3L).execute());
    }
}
