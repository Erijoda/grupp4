
package sportstats.service;

import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.CoreMatchers.instanceOf;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import sportstats.domain.Game;
import sportstats.domain.Round;
import sportstats.domain.Season;
import sportstats.domain.broker.BrokerFactory;
import sportstats.domain.broker.GameBroker;
import sportstats.domain.broker.RoundBroker;
import sportstats.domain.broker.SeasonBroker;
import sportstats.service.games.GetGamesBySeasonIdService;

/**
 *
 * @author mattkranc
 */
public class GetGamesBySeasonIdServiceTest {
/*
    @Test
    public void seasonIdIsNullThrowException() {
        try {
            new GetGamesBySeasonIdService(null);
            fail("Should throw exception");
        } catch (SportstatsServiceException ex) {}
    }
    
    @Test
    public void nonExistantSeasonThrowsException() {
        BrokerFactory brokerFactory = getMockedBrokerFactoryWithSettings();
        try {
            new GetGamesBySeasonIdService(0L)
                    .init(brokerFactory)
                    .execute();
            fail("Should throw exception");
        } catch (SportstatsServiceException ex) {}
    }
    
    @Test
    public void existingSeasonReturnsListOfGame() {
        BrokerFactory brokerFactory = getMockedBrokerFactoryWithSettings();
        
        List<Game> games = null;
        try {
            games = new GetGamesBySeasonIdService(1L)
                    .init(brokerFactory).execute();
        } catch (SportstatsServiceException ex) {
            fail("Should not throw exception");
        }
        //assertThat(games.get(0), instanceOf(Game.class));
    }
    
*/    
    
    private BrokerFactory getMockedBrokerFactory() {
        BrokerFactory brokerFactory = mock(BrokerFactory.class);
        GameBroker gameBroker = mock(GameBroker.class);
        RoundBroker roundBroker = mock(RoundBroker.class);
        SeasonBroker seasonBroker = mock(SeasonBroker.class);
        
        when(brokerFactory.getGameBroker()).thenReturn(gameBroker);
        when(brokerFactory.getRoundBroker()).thenReturn(roundBroker);
        when(brokerFactory.getSeasonBroker()).thenReturn(seasonBroker);
        
        return brokerFactory;
    }
    
    private BrokerFactory getMockedBrokerFactoryWithSettings() {
        BrokerFactory brokerFactory = getMockedBrokerFactory();
        GameBroker gameBroker = brokerFactory.getGameBroker();
        RoundBroker roundBroker = brokerFactory.getRoundBroker();
        SeasonBroker seasonBroker = brokerFactory.getSeasonBroker();
        
        Season mockedSeason = mock(Season.class);
        
        List<Round> mockedRounds = new ArrayList<>();
        mockedRounds.add(mock(Round.class));
        
        List<Long> mockedRoundIds = new ArrayList<>();
        mockedRoundIds.add(mockedRounds.get(0).getId());
        
        List mockedGames = new ArrayList<>();
        mockedGames.add(getMockedBrokerFactory()
                .getGameBroker()
                .findByRoundId(mockedRoundIds.get(0)));
        
        
        //when(seasonBroker.findById(1L)).thenReturn(mockedSeason);
        when(roundBroker.findBySeasonId(1L)).thenReturn(mockedRounds);
        
        when(gameBroker.findByRoundId(mockedRoundIds.get(0)))
                .thenReturn(mockedGames);
        return brokerFactory;
    }
    
}
