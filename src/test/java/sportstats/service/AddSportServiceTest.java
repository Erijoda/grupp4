/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportstats.service;

import static org.hamcrest.CoreMatchers.instanceOf;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import sportstats.domain.Sport;
import sportstats.domain.broker.BrokerFactory;
import sportstats.domain.broker.SportBroker;

/**
 *
 * @author Rebecca
 */
public class AddSportServiceTest {
    
    @Test
    public void nameIsNullThrowsException() {
        try {
            new AddSportService(null);
            fail("Should throw Exception");
        } catch (SportstatsServiceException ex) {}
    }
    
    @Test
    public void nameIsNotNullReturnsSport() {
        BrokerFactory brokerFactory = mock(BrokerFactory.class);
        SportBroker sportBroker = mock(SportBroker.class);
        when(brokerFactory.getSportBroker()).thenReturn(sportBroker);
        
        Sport sport = mock(Sport.class);
        when(sportBroker.create()).thenReturn(sport);
        Sport result = null;
        try {
            result = new AddSportService("Test").init(brokerFactory)
                    .execute();
        } catch(SportstatsServiceException ex) {
            fail("Should not throw Exception");
        }
        assertThat(result, instanceOf(Sport.class));
    }
}
