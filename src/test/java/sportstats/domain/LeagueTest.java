/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportstats.domain;

import static org.junit.Assert.*;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import sportstats.domain.dao.LeagueDao;

/**
 *
 * @author Rebecca
 */
public class LeagueTest {
    
    public LeagueTest() {
    }

    @Test
    public void testSomeMethod() {
        LeagueDao dao = mock(LeagueDao.class);
        when(dao.getString("name")).thenReturn("Hej");
        
        //when(dao.getClass()).thenReturn(new Sport);
        
        League instance = League.of(dao);
        assertEquals("Hej", instance.getName());
        assertNotEquals("Poff", instance.getName());
    }
    
}
