/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportstats.domain;

import org.junit.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import sportstats.domain.dao.SeasonDao;

/**
 *
 * @author Rebecca
 */
public class SeasonTest {
    
    
    @Test
    public void testNameIsCompliant() {
        SeasonDao dao = mock(SeasonDao.class);
        when(dao.getInteger("year")).thenReturn(1989);
        when(dao.getBoolean("summer"))
                .thenReturn(Boolean.FALSE)
                .thenReturn(Boolean.TRUE);
        
        Season instance = Season.of(dao);
        assertEquals("1989 - 1990", instance.getName());
        assertEquals("1989", instance.getName());
    }
    
}
