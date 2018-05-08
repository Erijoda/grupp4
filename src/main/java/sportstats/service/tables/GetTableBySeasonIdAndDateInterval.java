
package sportstats.service.tables;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.javalite.activejdbc.Base;
import sportstats.domain.TableRow;
import sportstats.service.BaseService;
import sportstats.service.tables.filters.DateIntervalFilter;
import sportstats.service.tables.filters.GameFilter;
import sportstats.service.tables.filters.SeasonFilter;
import sportstats.service.tables.filters.TableSqlGenerator;

/**
 *
 * @author mattkranc
 */
public class GetTableBySeasonIdAndDateInterval extends BaseService<List<TableRow>> {
    private final Long seasonId;
    private final Date fromDate;
    private final Date toDate;
    
    public GetTableBySeasonIdAndDateInterval(Long seasonId, Date fromDate, Date toDate) {
        this.seasonId = seasonId;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    @Override
    public List<TableRow> execute() {
        List<Map> result = Base.findAll(
                new TableSqlGenerator(
                        GameFilter.ALL,
                        new SeasonFilter(seasonId),
                        new DateIntervalFilter(fromDate, toDate)
                ).generateSql());
        
        List<TableRow> table = new ArrayList<>();
        result.stream().map((row) -> {
            TableRow tr = new TableRow();
            tr.setTeamId((Integer) row.get("team_id"));
            tr.setTeamName((String) row.get("team_name"));
            tr.setGamesPlayed((Long) row.get("games_played"));
            tr.setGamesWon((BigDecimal) row.get("games_won"));
            tr.setGamesTied((BigDecimal) row.get("games_tied"));
            tr.setGamesLost((BigDecimal) row.get("games_lost"));
            tr.setGamesWonOT((BigDecimal) row.get("games_won_ot"));
            tr.setGamesLostOT((BigDecimal) row.get("games_lost_ot"));
            tr.setGoals((BigDecimal) row.get("goals"));
            tr.setGoalsAgainst((BigDecimal) row.get("goals_against"));
            
            return tr;
        }).map((tr) -> {
            long points = 0;
            for (int i=0; i < tr.getGamesWon().longValue(); i++) {
                points += 3;
            }
            for (int i=0; i < tr.getGamesWonOT().longValue(); i++) {
                points += 2;
            }
            for (int i=0; i < (tr.getGamesLostOT().longValue()+tr.getGamesTied().longValue());i++) {
                points += 1;
            }
            tr.setPoints(points);
            return tr;            
        }).forEachOrdered((tr) -> {
            table.add(tr);
        });
        return table;
    }  
}
