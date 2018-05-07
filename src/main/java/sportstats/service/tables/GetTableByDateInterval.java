
package sportstats.service.tables;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.javalite.activejdbc.Base;
import sportstats.domain.TableRow;
import sportstats.service.BaseService;

/**
 *
 * @author mattkranc
 */
public class GetTableByDateInterval extends BaseService<List<TableRow>> {
    
    private final Date fromDate;
    private final Date toDate;
    
    public GetTableByDateInterval(Date fromDate, Date toDate) {
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    @Override
    public List<TableRow> execute() {
        List<Map> result = Base.findAll(
"       SELECT\n" +
"	seasons_teams.season_id as season_id,\n" +
"	teams.id AS team_id,\n" +
"	teams.name AS team_name,\n" +
"       games.date AS date,\n" +  
"    (SELECT COUNT(games.id) FROM games, rounds WHERE (games.away_team_id=teams.id OR games.home_team_id=teams.id) AND games.round_id=rounds.id AND rounds.season_id=seasons_teams.season_id) as games_played,\n" +
"    (SELECT COUNT(games.id) FROM games, results, rounds WHERE ((games.away_team_id=teams.id AND (results.game_id=games.id AND results.score_away_team > results.score_home_team)) OR (games.home_team_id=teams.id AND (results.game_id=games.id AND results.score_away_team < results.score_home_team))) AND games.round_id=rounds.id AND rounds.season_id=seasons_teams.season_id) as games_won,\n" +
"    (SELECT COUNT(games.id) FROM games, results, rounds WHERE ((games.away_team_id=teams.id AND (results.game_id=games.id AND results.score_away_team = results.score_home_team)) OR (games.home_team_id=teams.id AND (results.game_id=games.id AND results.score_away_team = results.score_home_team))) AND games.round_id=rounds.id AND rounds.season_id=seasons_teams.season_id) as games_tied,\n" +
"    (SELECT COUNT(games.id) FROM games, results, rounds WHERE ((games.away_team_id=teams.id AND (results.game_id=games.id AND results.score_away_team < results.score_home_team)) OR (games.home_team_id=teams.id AND (results.game_id=games.id AND results.score_away_team > results.score_home_team))) AND games.round_id=rounds.id AND rounds.season_id=seasons_teams.season_id) as games_lost,\n" +
"    (SELECT COUNT(games.id) FROM games, results, rounds WHERE ((games.away_team_id=teams.id AND (results.game_id=games.id AND results.score_away_team > results.score_home_team)) OR (games.home_team_id=teams.id AND (results.game_id=games.id AND results.score_away_team < results.score_home_team))) AND results.win_type > 0 AND games.round_id=rounds.id AND rounds.season_id=seasons_teams.season_id) as games_won_ot,\n" +
"    (SELECT COUNT(games.id) FROM games, results, rounds WHERE ((games.away_team_id=teams.id AND (results.game_id=games.id AND results.score_away_team < results.score_home_team)) OR (games.home_team_id=teams.id AND (results.game_id=games.id AND results.score_away_team > results.score_home_team))) AND results.win_type > 0 AND games.round_id=rounds.id AND rounds.season_id=seasons_teams.season_id) as games_lost_ot,\n" +
"    (SELECT SUM(case when games.away_team_id=teams.id then results.score_away_team else results.score_home_team end)  FROM games, rounds, results WHERE (games.away_team_id=teams.id OR games.home_team_id=teams.id) AND results.game_id=games.id AND games.round_id=rounds.id AND rounds.season_id=seasons_teams.season_id) as goals,\n" +
"	(SELECT SUM(case when games.away_team_id=teams.id then results.score_home_team else results.score_away_team end)  FROM games, rounds, results WHERE (games.away_team_id=teams.id OR games.home_team_id=teams.id) AND results.game_id=games.id AND games.round_id=rounds.id AND rounds.season_id=seasons_teams.season_id) as goals_against\n" +
"FROM\n" +
"	teams, games, seasons_teams\n" +
"WHERE\n" +
"	(games.away_team_id=teams.id OR games.home_team_id=teams.id)\n" +
"    AND team_id=seasons_teams.team_id\n" +
"    AND (games.date BETWEEN ? AND ?)\n" +
"GROUP BY seasons_teams.season_id, teams.id, games.round_id, games.date\n" +
"HAVING games_played > 0\n" +
"ORDER BY games.date, team_name ASC", fromDate, toDate);
        
        List<TableRow> table = new ArrayList<>();
        result.stream().map((row) -> {
            TableRow tr = new TableRow();
            tr.addSeasonId((Integer)row.get("season_id"));
            tr.setDate((String)row.get("date").toString());
            tr.setTeamId((Integer)row.get("team_id"));
            tr.setTeamName((String)row.get("team_name"));
            tr.setGamesPlayed((Long)row.get("games_played"));
            tr.setGamesWon((Long)row.get("games_won"));
            tr.setGamesTied((Long)row.get("games_tied"));
            tr.setGamesLost((Long)row.get("games_lost"));
            tr.setGamesWonOT((Long)row.get("games_won_ot"));
            tr.setGamesLostOT((Long)row.get("games_lost_ot"));
            tr.setGoals((BigDecimal)row.get("goals"));
            tr.setGoalsAgainst((BigDecimal)row.get("goals_against"));
            return tr;
        }).map((tr) -> {
            long points = 0;
            for (int i=0; i < tr.getGamesWon(); i++) {
                points += 3;
            }
            for (int i=0; i < tr.getGamesWonOT(); i++) {
                points += 2;
            }
            for (int i=0; i < (tr.getGamesLostOT()+tr.getGamesTied());i++) {
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
