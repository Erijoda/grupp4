/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportstats.domain.broker;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;
import sportstats.domain.Game;
import sportstats.domain.dao.GameDao;

/**
 *
 * @author Davik
 */
public class GameBroker {

    public Game create() {
        return new Game();
    }

    public Game findById(Long gameId) {
        return Game.of(GameDao.findById(gameId));
    }

    public List<Game> findByRoundId(Long roundId) {
        return GameDao.find("round_id = ?", roundId).stream()
                .map(gameDao -> Game.of((GameDao) gameDao))
                .collect(Collectors.toList());
    }

    public List<Game> findByTeamId(Long teamId) {
        return GameDao.find("home_team_id=? or away_team_id=?", teamId, teamId)
                .stream()
                .map(gameDao -> Game.of((GameDao) gameDao))
                .collect(Collectors.toList());
    }

    public List<Game> findByTeamIds(Long firstTeamId, Long secondTeamId) {
        return GameDao
                .find("(home_team_id=? and away_team_id=?) "
                        + "or (home_team_id=? and away_team_id=?)",
                         firstTeamId, secondTeamId,
                         secondTeamId, firstTeamId)
                .stream()
                .map(gameDao -> Game.of((GameDao) gameDao))
                .collect(Collectors.toList());
    }

    public List<Game> findWinsByTeamId(Long teamId) {
        String query
                = "SELECT games.* FROM games, results "
                + "WHERE games.id=results.game_id "
                + "AND ((games.home_team_id=? AND results.score_home_team > results.score_away_team) "
                + "OR (games.away_team_id=? AND results.score_home_team < results.score_away_team))";
        return GameDao.findBySQL(query, teamId, teamId).stream()
                .map(gameDao -> Game.of((GameDao) gameDao))
                .collect(Collectors.toList());
    }

    public List<Game> findBiggestWinByTeamIdAndDateInterval(Long teamId, Date fromDate, Date toDate) {
        String query
                = "SELECT\n"
                + "games.*,\n"
                + "(case when games.home_team_id=? then results.score_home_team - results.score_away_team else results.score_away_team - results.score_home_team end) as win_value,\n"
                + "(SELECT MAX(case when games.home_team_id=? then results.score_home_team - results.score_away_team else results.score_away_team - results.score_home_team end) as bw "
                + "FROM games, results WHERE results.game_id=games.id AND ((games.home_team_id=? AND results.score_home_team > results.score_away_team) OR (games.away_team_id=? "
                + "AND results.score_home_team < results.score_away_team))) as biggest_win\n"
                + "FROM\n"
                + "games\n"
                + "LEFT JOIN\n"
                + "results ON games.id=results.game_id\n"
                + "WHERE\n"
                + "((games.home_team_id=? AND results.score_home_team > results.score_away_team) OR (games.away_team_id=? AND results.score_home_team < results.score_away_team))\n"
                + "AND (games.date BETWEEN ? AND ?)"
                + "GROUP BY games.id, results.score_home_team, results.score_away_team\n"
                + "HAVING win_value = biggest_win";
                return GameDao.findBySQL(query, teamId, teamId, teamId, teamId, teamId, teamId, fromDate, toDate).stream()
                        .map(gameDao -> Game.of((GameDao) gameDao))
                        .collect(Collectors.toList());
    }
    
    public List<Game> findBiggestLossByTeamIdAndDateInterval(Long teamId, Date fromDate, Date toDate) {
        String query
                = "SELECT\n"
                + "games.*,\n"
                + "(case when games.home_team_id=? then results.score_home_team - results.score_away_team else results.score_away_team - results.score_home_team end) as win_value,\n"
                + "(SELECT MIN(case when games.home_team_id=? then results.score_home_team - results.score_away_team else results.score_away_team - results.score_home_team end) as bw "
                + "FROM games, results WHERE results.game_id=games.id AND ((games.home_team_id=? AND results.score_home_team > results.score_away_team) OR (games.away_team_id=? "
                + "AND results.score_home_team < results.score_away_team))) as biggest_win\n"
                + "FROM\n"
                + "games\n"
                + "LEFT JOIN\n"
                + "results ON games.id=results.game_id\n"
                + "WHERE\n"
                + "((games.home_team_id=? AND results.score_home_team > results.score_away_team) OR (games.away_team_id=? AND results.score_home_team < results.score_away_team))\n"
                + "AND (games.date BETWEEN ? AND ?)"
                + "GROUP BY games.id, results.score_home_team, results.score_away_team\n"
                + "HAVING win_value = biggest_win";
                return GameDao.findBySQL(query, teamId, teamId, teamId, teamId, teamId, teamId, fromDate, toDate).stream()
                        .map(gameDao -> Game.of((GameDao) gameDao))
                        .collect(Collectors.toList());
    }
    
    public List<Game> findBiggestWinByTeamIdAndRoundInterval(Long teamId, Long fromRound, Long toRound) {
        String query
                = "SELECT\n"
                + "games.*,\n"
                + "(case when games.home_team_id=? then results.score_home_team - results.score_away_team else results.score_away_team - results.score_home_team end) as win_value,\n"
                + "(SELECT MAX(case when games.home_team_id=? then results.score_home_team - results.score_away_team else results.score_away_team - results.score_home_team end) as bw "
                + "FROM games, results WHERE results.game_id=games.id AND ((games.home_team_id=? AND results.score_home_team > results.score_away_team) OR (games.away_team_id=? "
                + "AND results.score_home_team < results.score_away_team))) as biggest_win\n"
                + "FROM\n"
                + "rounds, games\n"
                + "LEFT JOIN\n"
                + "results ON games.id=results.game_id\n"
                + "WHERE\n"
                + "((games.home_team_id=? AND results.score_home_team > results.score_away_team) OR (games.away_team_id=? AND results.score_home_team < results.score_away_team))\n"
                + "AND (rounds.round BETWEEN ? AND ?)"
                + "GROUP BY games.id, results.score_home_team, results.score_away_team\n"
                + "HAVING win_value = biggest_win";
                return GameDao.findBySQL(query, teamId, teamId, teamId, teamId, teamId, teamId, fromRound, toRound).stream()
                        .map(gameDao -> Game.of((GameDao) gameDao))
                        .collect(Collectors.toList());
    }
    
    public List<Game> findBiggestLossByTeamIdAndRoundInterval(Long teamId, Long fromRound, Long toRound) {
        String query
                = "SELECT\n"
                + "games.*,\n"
                + "(case when games.home_team_id=? then results.score_home_team - results.score_away_team else results.score_away_team - results.score_home_team end) as win_value,\n"
                + "(SELECT MIN(case when games.home_team_id=? then results.score_home_team - results.score_away_team else results.score_away_team - results.score_home_team end) as bw "
                + "FROM games, results WHERE results.game_id=games.id AND ((games.home_team_id=? AND results.score_home_team > results.score_away_team) OR (games.away_team_id=? "
                + "AND results.score_home_team < results.score_away_team))) as biggest_win\n"
                + "FROM\n"
                + "rounds, games\n"
                + "LEFT JOIN\n"
                + "results ON games.id=results.game_id\n"
                + "WHERE\n"
                + "((games.home_team_id=? AND results.score_home_team > results.score_away_team) OR (games.away_team_id=? AND results.score_home_team < results.score_away_team))\n"
                + "AND (rounds.round BETWEEN ? AND ?)"
                + "GROUP BY games.id, results.score_home_team, results.score_away_team\n"
                + "HAVING win_value = biggest_win";
                return GameDao.findBySQL(query, teamId, teamId, teamId, teamId, teamId, teamId, fromRound, toRound).stream()
                        .map(gameDao -> Game.of((GameDao) gameDao))
                        .collect(Collectors.toList());
    }

    public List<Game> findTiesByTeamId(Long teamId) {
        String query
                = "SELECT games.* FROM games, results "
                + "WHERE games.id=results.game_id "
                + "AND ((games.home_team_id=? AND results.score_home_team = results.score_away_team) "
                + "OR (games.away_team_id=? AND results.score_home_team = results.score_away_team))";
        return GameDao.findBySQL(query, teamId, teamId).stream()
                .map(gameDao -> Game.of((GameDao) gameDao))
                .collect(Collectors.toList());
    }

    public List<Game> findLossesByTeamId(Long teamId) {
        String query
                = "SELECT games.* FROM games, results "
                + "WHERE games.id=results.game_id "
                + "AND ((games.home_team_id=? AND results.score_home_team < results.score_away_team) "
                + "OR (games.away_team_id=? AND results.score_home_team > results.score_away_team))";
        return GameDao.findBySQL(query, teamId, teamId).stream()
                .map(gameDao -> Game.of((GameDao) gameDao))
                .collect(Collectors.toList());
    }

    public List<Game> findHomeGamesByTeamId(Long teamId) {
        return GameDao.find("home_team_id=?", teamId)
                .stream()
                .map(gameDao -> Game.of((GameDao) gameDao))
                .collect(Collectors.toList());
    }

    public List<Game> findAwayGamesByTeamId(Long teamId) {
        return GameDao.find("away_team_id=?", teamId)
                .stream()
                .map(gameDao -> Game.of((GameDao) gameDao))
                .collect(Collectors.toList());
    }

    public List<Game> findByDate(Date date) {
        return GameDao.find("date=?", date)
                .stream()
                .map(gameDao -> Game.of((GameDao) gameDao))
                .collect(Collectors.toList());
    }

    public List<Game> findBySeasonId(Long seasonId) {
        String query = "SELECT games.* FROM games, rounds "
                + "WHERE games.round_id=rounds.id AND rounds.season_id=?";
        return GameDao.findBySQL(query, seasonId).stream()
                .map(gameDao -> Game.of((GameDao) gameDao))
                .collect(Collectors.toList());
    }
}
