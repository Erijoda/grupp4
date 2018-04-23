package sportstats.service.seasons;

import sportstats.service.seasons.AddTeamToSeasonService;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;
import org.junit.Test;
import static org.mockito.Mockito.*;
import sportstats.domain.Season;
import sportstats.domain.SeasonTeam;
import sportstats.domain.Team;
import sportstats.domain.broker.BrokerFactory;
import sportstats.domain.broker.SeasonBroker;
import sportstats.domain.broker.SeasonTeamBroker;
import sportstats.domain.broker.TeamBroker;
import sportstats.service.SportstatsServiceException;

public class AddTeamToSeasonServiceTest {
    
    @Test
    public void teamIdIsNullThrowsException() {
        try {
            new AddTeamToSeasonService(null, 0L);
            fail("Should throw a Exception");
        } catch (SportstatsServiceException ex) {}
    }
    
    @Test
    public void sportIdIsNullThrowsException() {
        try {
            new AddTeamToSeasonService(0L, null);
            fail("Should throw a Exception");
        } catch (SportstatsServiceException ex) {}
    }
    
    @Test
    public void nonExistantSeasonThrowsException() {
        BrokerFactory brokerFactory = getMockedBrokerFactoryWithSettings();
        
        try {
            new AddTeamToSeasonService(0L, 1L)
                    .init(brokerFactory)
                    .execute();
            fail("Should throw a Exception");
        } catch (SportstatsServiceException ex) {}
    }
    
    @Test
    public void nonExistantTeamThrowsException() {
        BrokerFactory brokerFactory = getMockedBrokerFactoryWithSettings();
        
        try {
            new AddTeamToSeasonService(1L, 0L)
                    .init(brokerFactory)
                    .execute();
            fail("Should throw a Exception");
        } catch (SportstatsServiceException ex) {}
    }
    
    @Test
    public void existingTeamAndSeasonReturnsSeasonTeam() {
        BrokerFactory brokerFactory = getMockedBrokerFactoryWithSettings();
        
        SeasonTeam seasonTeam = null;
        try {
            seasonTeam = new AddTeamToSeasonService(0L, 0L)
                    .init(brokerFactory)
                    .execute();
        } catch (SportstatsServiceException ex) {
            fail("Should not throw Exception");
        }
        
        assertThat(seasonTeam, instanceOf(SeasonTeam.class));
    }
    
    private BrokerFactory getMockedBrokerFactory() {
        BrokerFactory brokerFactory = mock(BrokerFactory.class);
        SeasonBroker seasonBroker = mock(SeasonBroker.class);
        TeamBroker teamBroker = mock(TeamBroker.class);
        SeasonTeamBroker seasonTeamBroker = mock(SeasonTeamBroker.class);
        
        when(brokerFactory.getSeasonBroker()).thenReturn(seasonBroker);
        when(brokerFactory.getTeamBroker()).thenReturn(teamBroker);
        when(brokerFactory.getSeasonTeamBroker()).thenReturn(seasonTeamBroker);
        
        return brokerFactory;
    }
    
    private BrokerFactory getMockedBrokerFactoryWithSettings() {
        BrokerFactory brokerFactory = getMockedBrokerFactory();
        Team team = mock(Team.class);
        Season season = mock(Season.class);
        SeasonTeam seasonTeam = mock(SeasonTeam.class);
        
        SeasonBroker seasonBroker = brokerFactory.getSeasonBroker();
        TeamBroker teamBroker = brokerFactory.getTeamBroker();
        SeasonTeamBroker seasonTeamBroker = brokerFactory.getSeasonTeamBroker();
        
        when(teamBroker.findById(0L)).thenReturn(team);
        when(teamBroker.findById(1L)).thenReturn(null);
        when(seasonBroker.findById(0L)).thenReturn(season);
        when(seasonBroker.findById(1L)).thenReturn(null);
        when(seasonTeamBroker.findByIds(0L, 0L)).thenReturn(seasonTeam);
        
        return brokerFactory;
    }
}
