package sportstats.service.sports;

import sportstats.service.sports.GetAllSportsService;
import java.util.List;
import org.junit.Test;
import sportstats.domain.Sport;

public class GetAllSportsServiceIT {

    @Test
    public void testListingOfSports() {
        System.out.println(new GetAllSportsService().execute());
    }
    
    @Test
    public void testListingOfSportsName() {
        List<Sport> sportList = new GetAllSportsService().execute();
        for(int i = 0; i < sportList.size(); i++) {
            System.out.println(sportList.get(i).getName());
        }
    }
}
