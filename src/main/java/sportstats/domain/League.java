package sportstats.domain;

import java.util.List;
import java.util.stream.Collectors;
import sportstats.domain.dao.LeagueDao;
import sportstats.domain.dao.SeasonDao;

/**
 *
 * @author thomas
 */
public class League implements Base<LeagueDao> {
    private final LeagueDao dao;
    
    public League() {
        this(new LeagueDao());
    }
    
    private League(LeagueDao dao) {
        this.dao = dao;
    }
    
    public static League of(LeagueDao dao) {
        return dao == null ? null : new League(dao);
    }
    
    @Override
    public LeagueDao getDao() {
        return dao;
    }
    
    public Long getId() {
        return dao.getLongId();
    }

    public String getName() {
        return dao.getString("name");
    }
    
    public void setName(String name) {
        dao.setString("name", name);
    }
    
    public void setSport(Sport sport) {
        dao.setLong("sport_id", sport.getId());
    }
    
    public void setAsChild(SeasonDao seasonDao) {
        seasonDao.setParent(dao);
    }
    
    public void save() {
        dao.save();
    }
    
    @Override
    public String toString() {
        return getName() + " (id: " + getId() + ")";
    }
}
