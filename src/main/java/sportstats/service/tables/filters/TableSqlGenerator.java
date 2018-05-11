/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportstats.service.tables.filters;

/**
 *
 * @author davik
 */
public class TableSqlGenerator {

    private final GameFilter gameFilter;
    private final TableFilter[] tableFilters;

    public TableSqlGenerator(GameFilter gameFilter, TableFilter... tableFilters) {
        this.gameFilter = gameFilter;
        this.tableFilters = tableFilters;
    }

    public String generateSql() {
        String sql = "SELECT\n"
                + "	teams.id AS team_id,\n"
                + "	teams.name AS team_name,\n"
                + "    COUNT(games.id) as games_played,\n"
                + "    SUM(case when games.home_team_id=teams.id then results.score_home_team > results.score_away_team else results.score_home_team < results.score_away_team end) as games_won,\n"
                + "    SUM(case when results.score_home_team=results.score_away_team then 1 else 0 end) as games_tied,\n"
                + "    SUM(case when games.home_team_id=teams.id then results.score_home_team < results.score_away_team else results.score_home_team > results.score_away_team end) as games_lost,\n"
                + "    SUM(case when games.home_team_id=teams.id then results.win_type > 0 and results.score_home_team > results.score_away_team else results.win_type > 0 and results.score_home_team < results.score_away_team end) as games_won_ot,\n"
                + "    SUM(case when games.home_team_id=teams.id then results.win_type > 0 and results.score_home_team < results.score_away_team else results.win_type > 0 and  results.score_home_team > results.score_away_team end) as games_lost_ot,\n"
                + "    SUM(case when games.home_team_id=teams.id then results.score_home_team else results.score_away_team end) as goals,\n"
                + "    SUM(case when games.home_team_id=teams.id then results.score_away_team else results.score_home_team end) as goals_against\n"
                + "FROM\n"
                + "	seasons_teams\n"
                + "INNER JOIN\n"
                + "	teams ON teams.id=seasons_teams.team_id\n"
                + "INNER JOIN\n"
                + "	rounds ON rounds.season_id=seasons_teams.season_id\n"
                + "INNER JOIN\n"
                + "	games ON games.round_id=rounds.id\n"
                + "INNER JOIN\n"
                + "	results ON results.game_id=games.id\n"
                + "WHERE \n";
                sql += gameFilter.getSqlStub();
                
                for (TableFilter tf : tableFilters) {
                    sql += " AND " + tf.getSqlStub();
                }
        
                sql += " GROUP BY teams.id\n"
                + "ORDER BY team_name ASC";
                
                return sql;
    }
}
