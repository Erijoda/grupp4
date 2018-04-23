/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportstats.service.games;

import sportstats.service.games.AddResultService;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;
import org.junit.Test;
import static org.mockito.Mockito.*;
import sportstats.domain.Game;
import sportstats.domain.Result;
import sportstats.domain.broker.BrokerFactory;
import sportstats.domain.broker.GameBroker;
import sportstats.domain.broker.ResultBroker;
import sportstats.service.SportstatsServiceException;

/**
 *
 * @author Davik
 */
public class AddResultServiceTest {
    @Test
    public void gameIdIsNullThrowsException() {
        try {
            new AddResultService(null, 1L, 1L);
            fail("Should throw exception");
        } catch (SportstatsServiceException ex) {}
    }
    
    @Test
    public void scoreHomeTeamIsNullThrowsException() {
        try {
            new AddResultService(1L, null, 1L);
            fail("Should throw exception");
        } catch (SportstatsServiceException ex) {}
    }
    
    @Test
    public void scoreAwayTeamIsNullThrowsException() {
        try {
            new AddResultService(1L, 1L, null);
            fail("Should throw exception");
        } catch (SportstatsServiceException ex) {}
    }
    
    @Test
    public void nonExistantGameThrowsException() {
        BrokerFactory brokerFactory = getMockedBrokerFactoryWithSettings();
        try {
            new AddResultService(0L, 1L, 1L)
                    .init(brokerFactory)
                    .execute();
            fail("Should throw exception");
        } catch (SportstatsServiceException ex) {}
    }
    
    @Test
    public void existingGameReturnsResult() {
        BrokerFactory brokerFactory = getMockedBrokerFactoryWithSettings();
        Result result = null;
        try {
            result = new AddResultService(1L, 1L, 1L)
                    .init(brokerFactory)
                    .execute();
        } catch (SportstatsServiceException ex) {
            fail("Should not throw exception");
        }
        assertThat(result, instanceOf(Result.class));
    }
    
    private BrokerFactory getMockedBrokerFactory() {
        BrokerFactory brokerFactory = mock(BrokerFactory.class);
        GameBroker gameBroker = mock(GameBroker.class);
        ResultBroker resultBroker = mock(ResultBroker.class);
        
        when(brokerFactory.getGameBroker()).thenReturn(gameBroker);
        when(brokerFactory.getResultBroker()).thenReturn(resultBroker);
        
        return brokerFactory;
    }
    
    private BrokerFactory getMockedBrokerFactoryWithSettings() {
        BrokerFactory brokerFactory = getMockedBrokerFactory();
        GameBroker gameBroker = brokerFactory.getGameBroker();
        ResultBroker resultBroker = brokerFactory.getResultBroker();
        
        Game mockedGame = mock(Game.class);
        Result mockedResult = mock(Result.class);
        
        when(resultBroker.create()).thenReturn(mockedResult);
        when(gameBroker.findById(0L)).thenReturn(null);
        when(gameBroker.findById(1L)).thenReturn(mockedGame);
        
        return brokerFactory;
    }
}
