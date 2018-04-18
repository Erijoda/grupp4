package sportstats.service;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;
import org.junit.Test;
import static org.mockito.Mockito.*;
import sportstats.domain.Sport;
import sportstats.domain.Team;
import sportstats.domain.broker.BrokerFactory;
import sportstats.domain.broker.SportBroker;
import sportstats.domain.broker.TeamBroker;

public class AddTeamServiceTest {
    
    @Test
    public void nameIsNullThrowsException() {
        try {
            new AddTeamService(null, 0L);
            fail("Should throw a Exception");
        } catch (SportstatsServiceException ex) {}
    }
    
    @Test
    public void sportIdIsNullThrowsException() {
        try {
            new AddTeamService("Name", null);
            fail("Should throw a Exception");
        } catch (SportstatsServiceException ex) {}
    }
    
    @Test
    public void nonExistantSportThrowsException() {
        BrokerFactory brokerFactory = getMockedBrokerFactoryWithSettings();
        
        try {
            new AddTeamService("Name", 1L)
                    .init(brokerFactory)
                    .execute();
            fail("Should throw a Exception");
        } catch (SportstatsServiceException ex) {}
    }
    
    @Test
    public void existingSportReturnsTeam() {
        BrokerFactory brokerFactory = getMockedBrokerFactoryWithSettings();
        
        Team team = null;
        try {
            team = new AddTeamService("Name", 0L)
                    .init(brokerFactory)
                    .execute();
        } catch (SportstatsServiceException ex) {
            fail("Should not throw Exception");
        }
        
        assertThat(team, instanceOf(Team.class));
    }
    
    private BrokerFactory getMockedBrokerFactory() {
        BrokerFactory brokerFactory = mock(BrokerFactory.class);
        SportBroker sportBroker = mock(SportBroker.class);
        TeamBroker teamBroker = mock(TeamBroker.class);
        
        when(brokerFactory.getSportBroker()).thenReturn(sportBroker);
        when(brokerFactory.getTeamBroker()).thenReturn(teamBroker);
        
        return brokerFactory;
    }
    
    private BrokerFactory getMockedBrokerFactoryWithSettings() {
        BrokerFactory brokerFactory = getMockedBrokerFactory();
        Sport sport = mock(Sport.class);
        Team team = mock(Team.class);
        
        SportBroker sportBroker = brokerFactory.getSportBroker();
        TeamBroker teamBroker = brokerFactory.getTeamBroker();
        
        when(sportBroker.findById(0L)).thenReturn(sport);
        when(sportBroker.findById(1L)).thenReturn(null);
        when(teamBroker.create()).thenReturn(team);
        
        return brokerFactory;
    }
}
