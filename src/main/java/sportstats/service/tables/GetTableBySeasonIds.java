/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportstats.service.tables;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.javalite.activejdbc.Base;
import sportstats.domain.TableRow;
import sportstats.service.BaseService;
import sportstats.service.tables.filters.GameFilter;
import sportstats.service.tables.filters.SeasonFilter;
import sportstats.service.tables.filters.TableSqlGenerator;

/**
 *
 * @author Davik
 */
public class GetTableBySeasonIds extends BaseService<List<TableRow>> {

    private final Long[] seasonIds;

    public GetTableBySeasonIds(Long... seasonIds) {
        this.seasonIds = seasonIds;
    }

    public List<TableRow> execute() {
        List<Map> result = Base.findAll(
                new TableSqlGenerator(
                        GameFilter.ALL,
                        new SeasonFilter(seasonIds)
                ).generateSql());

        List<TableRow> table = new ArrayList<>();
        for (Map row : result) {
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

            //Calculate points according to SHL standards
            long points = 0;

            //3 points for every normal win
            for (int i = 0; i < tr.getGamesWon().longValue(); i++) {
                points += 3;
            }

            //2 points for every overtime/shootout win
            for (int i = 0; i < tr.getGamesWonOT().longValue(); i++) {
                points += 2;
            }

            //1 points for every overtime/shootout lost, and tied.
            for (int i = 0; i < (tr.getGamesLostOT().longValue() + tr.getGamesTied().longValue()); i++) {
                points += 1;
            }
            //0 points for every normal loss

            tr.setPoints(points);

            table.add(tr);
        }

        return table;
    }
}
