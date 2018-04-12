/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportstats.domain.broker;

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
}
