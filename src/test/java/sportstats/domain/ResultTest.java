/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportstats.domain;

import static org.junit.Assert.*;
import org.junit.Test;
import static org.mockito.Mockito.*;
import sportstats.constants.WinType;
import sportstats.domain.dao.ResultDao;

/**
 *
 * @author davik
 */
public class ResultTest {
    
    @Test
    public void getResultWinTypeIsNormalReturnsNormalResult() {
        ResultDao dao = mock(ResultDao.class);
        when(dao.getWinType()).thenReturn(WinType.NORMAL);
        when(dao.getLong("score_home_team")).thenReturn(4L);
        when(dao.getLong("score_away_team")).thenReturn(3L);
        
        Result result = Result.of(dao);
        
        String expected = "4 - 3";
        String actual = result.getResult();
        
        assertEquals(expected, actual);
    }
    
    @Test
    public void getResultWinTypeIsOvertimeReturnsOvertimeResult() {
        ResultDao dao = mock(ResultDao.class);
        when(dao.getWinType()).thenReturn(WinType.OVERTIME);
        when(dao.getLong("score_home_team")).thenReturn(4L);
        when(dao.getLong("score_away_team")).thenReturn(3L);
        
        Result result = Result.of(dao);
        
        String expected = "4 - 3 OT";
        String actual = result.getResult();
        
        assertEquals(expected, actual);
    }
    
    @Test
    public void getResultWinTypeIsShootoutReturnsShootoutResult() {
        ResultDao dao = mock(ResultDao.class);
        when(dao.getWinType()).thenReturn(WinType.SHOOTOUT);
        when(dao.getLong("score_home_team")).thenReturn(4L);
        when(dao.getLong("score_away_team")).thenReturn(3L);
        
        Result result = Result.of(dao);
        
        String expected = "4 - 3 SO";
        String actual = result.getResult();
        
        assertEquals(expected, actual);
    }
}
