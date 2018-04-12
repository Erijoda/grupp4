/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportstats.service;

import static org.hamcrest.CoreMatchers.instanceOf;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import sportstats.domain.Game;
import sportstats.domain.Team;
import sportstats.domain.broker.BrokerFactory;
import sportstats.domain.broker.GameBroker;
import sportstats.domain.broker.TeamBroker;
import sportstats.service.constants.Outcome;

/**
 *
 * @author davik
 */
public class GetGamesByTeamIdServiceTest {
    
    @Test
    public void teamIdIsNullThrowsException() {
        try {
            new GetGamesByTeamIdService(null, Outcome.ANY);
            fail("Should throw exception");
        } catch (SportstatsServiceException ex) {}
    }
    
    @Test
    public void outcomeIsNullThrowsException() {
        try {
            new GetGamesByTeamIdService(1L, null);
            fail("Should throw exception");
        } catch (SportstatsServiceException ex) {}
    }
    
    @Test
    public void nonExistantTeamThrowsException() {
        BrokerFactory brokerFactory = getMockedBrokerFactoryWithSettings();
        try {
            new GetGamesByTeamIdService(0L, Outcome.ANY)
                    .init(brokerFactory)
                    .execute();
            fail("Should throw exception");
        } catch (SportstatsServiceException ex) {}
    }
    
    @Test
    public void existingTeamOutcomeAnyReturnsListOfGame() {
        BrokerFactory brokerFactory = getMockedBrokerFactoryWithSettings();
        
        List<Game> games = null;
        try {
            games = new GetGamesByTeamIdService(1L, Outcome.ANY)
                    .init(brokerFactory)
                    .execute();
        } catch (SportstatsServiceException ex) {
            fail("Should not throw exception");
        }
        assertThat(games.get(0), instanceOf(Game.class));
    }
    
    @Test
    public void existingTeamOutcomeWinReturnsListOfGame() {
        BrokerFactory brokerFactory = getMockedBrokerFactoryWithSettings();
        
        List<Game> games = null;
        try {
            games = new GetGamesByTeamIdService(1L, Outcome.WIN)
                    .init(brokerFactory)
                    .execute();
        } catch (SportstatsServiceException ex) {
            fail("Should not throw exception");
        }
        assertThat(games.get(0), instanceOf(Game.class));
    }
    
    @Test
    public void existingTeamOutcomeTieReturnsListOfGame() {
        BrokerFactory brokerFactory = getMockedBrokerFactoryWithSettings();
        
        List<Game> games = null;
        try {
            games = new GetGamesByTeamIdService(1L, Outcome.TIE)
                    .init(brokerFactory)
                    .execute();
        } catch (SportstatsServiceException ex) {
            fail("Should not throw exception");
        }
        assertThat(games.get(0), instanceOf(Game.class));
    }
    
    @Test
    public void existingTeamOutcomeLossReturnsListOfGame() {
        BrokerFactory brokerFactory = getMockedBrokerFactoryWithSettings();
        
        List<Game> games = null;
        try {
            games = new GetGamesByTeamIdService(1L, Outcome.LOSS)
                    .init(brokerFactory)
                    .execute();
        } catch (SportstatsServiceException ex) {
            fail("Should not throw exception");
        }
        assertThat(games.get(0), instanceOf(Game.class));
    }
    
    private BrokerFactory getMockedBrokerFactory() {
        BrokerFactory brokerFactory = mock(BrokerFactory.class);
        GameBroker gameBroker = mock(GameBroker.class);
        TeamBroker teamBroker = mock(TeamBroker.class);
        
        when(brokerFactory.getGameBroker()).thenReturn(gameBroker);
        when(brokerFactory.getTeamBroker()).thenReturn(teamBroker);
        
        return brokerFactory;
    }
    
    private BrokerFactory getMockedBrokerFactoryWithSettings() {
        BrokerFactory brokerFactory = getMockedBrokerFactory();
        GameBroker gameBroker = brokerFactory.getGameBroker();
        TeamBroker teamBroker = brokerFactory.getTeamBroker();
        
        Team mockedTeam = mock(Team.class);
        List<Game> mockedGames = new ArrayList<>();
        mockedGames.add(mock(Game.class));
        
        when(gameBroker.findByTeamId(1L)).thenReturn(mockedGames);
        when(gameBroker.findWinsByTeamId(1L)).thenReturn(mockedGames);
        when(gameBroker.findTiesByTeamId(1L)).thenReturn(mockedGames);
        when(gameBroker.findLossesByTeamId(1L)).thenReturn(mockedGames);
        when(teamBroker.findById(0L)).thenReturn(null);
        when(teamBroker.findById(1L)).thenReturn(mockedTeam);
        
        return brokerFactory;
    }
}
