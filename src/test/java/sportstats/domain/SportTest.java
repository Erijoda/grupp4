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
import sportstats.domain.dao.SportDao;

/**
 *
 * @author Rebecca
 */
public class SportTest {
    
    public SportTest() {
    }

    @Test
    public void testSomeMethod() {
        SportDao dao = mock(SportDao.class);
        when(dao.getString("name")).thenReturn("Foo");
        
        Sport instance = Sport.of(dao);
        assertEquals("Foo", instance.getName());
        assertNotEquals("Poff", instance.getName());
    }
    
}
