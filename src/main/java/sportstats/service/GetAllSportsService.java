package sportstats.service;

import java.util.List;
import sportstats.domain.Sport;

public class GetAllSportsService extends BaseService<List<Sport>> {

    @Override
    public List<Sport> execute() {
        List<Sport> result = getBrokerFactory().getSportBroker().findAll();
        return result;
    }
}
