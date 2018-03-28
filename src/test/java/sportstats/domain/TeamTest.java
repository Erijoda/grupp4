/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportstats.domain;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import sportstats.domain.dao.SportDao;
import sportstats.domain.dao.TeamDao;

/**
 *
 * @author Rebecca
 */
public class TeamTest {
    
    public TeamTest() {
    }

    @Test
    public void testSomeMethod() {
        TeamDao dao = mock(TeamDao.class);
        when(dao.getString("name")).thenReturn("Foo");
        
        Team instance = new Team(dao);
        assertEquals("Foo", instance.getName());
        assertNotEquals("Poff", instance.getName());
    }
    
}
