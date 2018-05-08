/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportstats.service.tables.filters;

import java.sql.Date;

/**
 *
 * @author davik
 */
public class DateIntervalFilter implements TableFilter {
    private final Date fromDate;
    private final Date toDate;
    
    public DateIntervalFilter(Date fromDate, Date toDate) {
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    @Override
    public String getSqlStub() {
        return "(games.date BETWEEN '" + fromDate + "' AND '" + toDate + "')";
    }
    
}
