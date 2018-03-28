package sportstats.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.javalite.activejdbc.Model;
import sportstats.domain.dao.LeagueDao;
import sportstats.domain.dao.SeasonDao;
import sportstats.domain.dao.SportDao;

/**
 *
 * @author thomas
 */
public class League {
    
    private final LeagueDao dao;
    
    public League() {
        this(new LeagueDao());
    }
    
    public League(LeagueDao dao) {
        this.dao = dao;
    }
    
    public Long getId() {
        return dao.getLong("id");
    }

    public String getName() {
        return dao.getString("name");
    }
    
    public void setName(String name) {
        dao.setString("name", name);
    }
    
    public void setSport(Sport sport) {
        dao.setLong("sports_id", sport.getId());
    }
    
    public void save() {
        dao.save();
    }
    
    @Override
    public String toString() {
        return getName() + " (id: " + getId() + ")";
    }
    
    public List<Season> getSeasons() {
        return dao.getAll(SeasonDao.class).stream()
                .map(dao -> new Season(dao))
                .collect(Collectors.toList());
        /*List<SeasonDao> daos = getAll(SeasonDao.class);
        List<Season> result = new ArrayList<>();
        for (SeasonDao dao : daos) {
            result.add(new Season(dao));
        }
        return result;*/
    }
}
