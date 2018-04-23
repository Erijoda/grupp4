/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportstats.service.seasons;

import sportstats.service.seasons.AddSeasonService;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;
import org.junit.Test;
import static org.mockito.Mockito.*;
import sportstats.domain.League;
import sportstats.domain.Season;
import sportstats.domain.broker.BrokerFactory;
import sportstats.domain.broker.LeagueBroker;
import sportstats.domain.broker.SeasonBroker;
import sportstats.service.SportstatsServiceException;

/**
 *
 * @author davik
 */
public class AddSeasonServiceTest {
    
    @Test
    public void yearIsNullThrowsException() {
        try {
            new AddSeasonService(null, Boolean.FALSE, 1L);
            fail("Should throw Exception");
        } catch (SportstatsServiceException ex) {}
    }
    
    @Test
    public void summerSeasonIsNullThrowsException() {
        try {
            new AddSeasonService(2015L, null, 1L);
            fail("Should throw Exception");
        } catch (SportstatsServiceException ex) {}
    }
    
    @Test
    public void leagueIdIsNullThrowsException() {
        try {
            new AddSeasonService(2015L, Boolean.FALSE, null);
            fail("Should throw Exception");
        } catch (SportstatsServiceException ex) {}
    }
    
    @Test
    public void nonExistantLeagueThrowsException() {
        BrokerFactory brokerFactory = getMockedBrokerFactoryWithSettings();
        
        try {
            new AddSeasonService(2015L, Boolean.FALSE, 1L)
                    .init(brokerFactory)
                    .execute();
            fail("Should throw Exception");
        } catch (SportstatsServiceException ex) {}
    }
    
    @Test
    public void existingLeagueReturnsSeason() {
        BrokerFactory brokerFactory = getMockedBrokerFactoryWithSettings();
        
        Season season = null;
        try {
            season = new AddSeasonService(2015L, Boolean.FALSE, 0L)
                    .init(brokerFactory)
                    .execute();
        } catch (SportstatsServiceException ex) {
            fail("Should not throw Exception");
        }
        
        assertThat(season, instanceOf(Season.class));
    }
    
    private BrokerFactory getMockedBrokerFactory() {
        BrokerFactory brokerFactory = mock(BrokerFactory.class);
        LeagueBroker leagueBroker = mock(LeagueBroker.class);
        SeasonBroker seasonBroker = mock(SeasonBroker.class);
        
        when(brokerFactory.getLeagueBroker()).thenReturn(leagueBroker);
        when(brokerFactory.getSeasonBroker()).thenReturn(seasonBroker);
        
        return brokerFactory;
    }
    
    private BrokerFactory getMockedBrokerFactoryWithSettings() {
        BrokerFactory brokerFactory = getMockedBrokerFactory();
        League league = mock(League.class);
        Season season = mock(Season.class);
        
        LeagueBroker leagueBroker = brokerFactory.getLeagueBroker();
        SeasonBroker seasonBroker = brokerFactory.getSeasonBroker();
        
        when(leagueBroker.findById(0L)).thenReturn(league);
        when(leagueBroker.findById(1L)).thenReturn(null);
        when(seasonBroker.create()).thenReturn(season);
        
        return brokerFactory;
    }
}
