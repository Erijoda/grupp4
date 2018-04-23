package sportstats.service.sports;

import java.util.List;
import sportstats.domain.Sport;
import sportstats.service.BaseService;

public class GetAllSportsService extends BaseService<List<Sport>> {

    @Override
    public List<Sport> execute() {
        List<Sport> result = getBrokerFactory().getSportBroker().findAll();
        return result;
    }
}
