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
public class SeasonFilter implements TableFilter {
    private final Long[] seasonIds;

    public SeasonFilter(Long... seasonIds) {
        this.seasonIds = seasonIds;
    }
    @Override
    public String getSqlStub() {
        String sqlStub = "(";
        for (int i=0; i < seasonIds.length; i++) {
            if (i != 0) {
                sqlStub += " OR ";
            }
            sqlStub += "seasons_teams.season_id=" + seasonIds[i];
        }
        sqlStub += ")";
        
        return sqlStub;
    }
    
}
