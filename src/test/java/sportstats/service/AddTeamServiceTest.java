package sportstats.service;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import sportstats.db.DbConn;
import sportstats.domain.Sport;
import sportstats.domain.Team;
import sportstats.domain.broker.BrokerFactory;
import sportstats.domain.broker.SportBroker;
import sportstats.domain.broker.TeamBroker;

/**
 *
 * @author thomas
 */
public class AddTeamServiceTest {
/*
    public AddTeamServiceTest() {
    }

    @Test
    public void nameShouldNotBeNull() {
        try {
            new AddTeamService(null, 21L);
            fail("Should throw exception");
        } catch (SportstatsServiceException ex) {
        }
    }

    @Test
    public void sportIdShouldNotBeNull() {
        try {
            new AddTeamService("New Team", null);
            fail("Should throw exception");
        } catch (SportstatsServiceException ex) {
        }
    }

    @Test
    public void shouldNotAcceptNonExistingSport() {
        AddTeamService service = new AddTeamService("New Team", 10L);
        BrokerFactory brokerFactory = getMockedBrokerFactoryWithBrokersSetup();
        DbConn conn = mock(DbConn.class);
        service.init(conn, brokerFactory);
        try {
            service.execute();
            fail("Should throw");
        } catch (SportstatsServiceException ex) {
        }
    }

    @Test
    public void shouldAcceptExistingSport() {
        AddTeamService service = new AddTeamService("Nytt Lag", 1L);
        BrokerFactory brokerFactory = getMockedBrokerFactoryWithBrokersSetup();
        DbConn conn = mock(DbConn.class);
        service.init(conn, brokerFactory);
        try {
            service.execute();
        } catch (SportstatsServiceException ex) {
            fail("Should not throw");
        }
    }

    private BrokerFactory getMockedBrokerFactory() {
        SportBroker sportBroker = mock(SportBroker.class);
        TeamBroker teamBroker = mock(TeamBroker.class);
        BrokerFactory brokerFactory = mock(BrokerFactory.class);
        when(brokerFactory.getSportBroker()).thenReturn(sportBroker);
        when(brokerFactory.getTeamBroker()).thenReturn(teamBroker);
        return brokerFactory;
    }
    
    private BrokerFactory getMockedBrokerFactoryWithBrokersSetup() {
        BrokerFactory brokerFactory = getMockedBrokerFactory();
        Team team = mock(Team.class);
        Sport sport = mock(Sport.class);
        SportBroker sportBroker = brokerFactory.getSportBroker();
        TeamBroker teamBroker = brokerFactory.getTeamBroker();
        when(sportBroker.findById(1L)).thenReturn(sport);
        when(sportBroker.findById(10L)).thenReturn(null);
        when(teamBroker.create()).thenReturn(team);
        return brokerFactory;
    }
*/
}
