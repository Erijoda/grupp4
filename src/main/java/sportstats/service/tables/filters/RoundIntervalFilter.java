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
public class RoundIntervalFilter implements TableFilter {
    private final Long round1;
    private final Long round2;
    
    public RoundIntervalFilter(Long round1, Long round2) {
        this.round1 = round1;
        this.round2 = round2;
    }

    @Override
    public String getSqlStub() {
        return "(rounds.round BETWEEN '" + round1 + "' AND '" + round2 + "')";
    }
    
}
