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
public enum GameFilter implements TableFilter {
    ALL, AWAY, HOME;

    @Override
    public String getSqlStub() {
        switch(this) {
            case ALL:
                return "(games.home_team_id=teams.id OR games.away_team_id=teams.id)";
            case AWAY:
                return "games.away_team_id=teams.id";
            case HOME:
                return "games.home_team_id=teams.id";
            default: throw new IllegalStateException();
        }
    }
}
