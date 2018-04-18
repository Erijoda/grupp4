/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportstats.service;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;
import org.junit.Test;
import static org.mockito.Mockito.*;
import sportstats.domain.League;
import sportstats.domain.Sport;
import sportstats.domain.broker.BrokerFactory;
import sportstats.domain.broker.LeagueBroker;
import sportstats.domain.broker.SportBroker;

/**
 *
 * @author davik
 */
public class AddLeagueServiceTest {
    @Test
    public void nameIsNullThrowsException() {
        try {
            new AddLeagueService(null, 0L);
            fail("Should throw Exception");
        } catch (SportstatsServiceException ex) {};
    }
    
    @Test
    public void sportIdIsNullThrowsException() {
        try {
            new AddLeagueService("Name", null);
            fail("Should throw Exception");
        } catch (SportstatsServiceException ex) {}
    }
    
    @Test
    public void nonExistantSportThrowsException() {
        BrokerFactory brokerFactory = getMockedBrokerFactoryWithSettings();
        
        try {
            new AddLeagueService("Name", 1L)
                    .init(brokerFactory)
                    .execute();
            fail("Should throw Exception");
        } catch (SportstatsServiceException ex) {}
    }
    
    @Test
    public void existingSportReturnsLeague() {
        BrokerFactory brokerFactory = getMockedBrokerFactoryWithSettings();
        
        League league = null;
        try {
            league = new AddLeagueService("Name", 0L)
                    .init(brokerFactory)
                    .execute();
        } catch (SportstatsServiceException ex) {
            fail("Should not throw Exception");
        }
        
        assertThat(league, instanceOf(League.class));
    }
    
    private BrokerFactory getMockedBrokerFactory() {
        BrokerFactory brokerFactory = mock(BrokerFactory.class);
        SportBroker sportBroker = mock(SportBroker.class);
        LeagueBroker leagueBroker = mock(LeagueBroker.class);
        
        when(brokerFactory.getSportBroker()).thenReturn(sportBroker);
        when(brokerFactory.getLeagueBroker()).thenReturn(leagueBroker);
        
        return brokerFactory;
    }
    
    private BrokerFactory getMockedBrokerFactoryWithSettings() {
        BrokerFactory brokerFactory = getMockedBrokerFactory();
        Sport sport = mock(Sport.class);
        League league = mock(League.class);
        
        SportBroker sportBroker = brokerFactory.getSportBroker();
        LeagueBroker leagueBroker = brokerFactory.getLeagueBroker();
        
        when(sportBroker.findById(0L)).thenReturn(sport);
        when(sportBroker.findById(1L)).thenReturn(null);
        when(leagueBroker.create()).thenReturn(league);
        
        return brokerFactory;
    }
}
