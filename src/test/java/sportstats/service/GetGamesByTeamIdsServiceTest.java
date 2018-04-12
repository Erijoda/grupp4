/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportstats.service;

import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.CoreMatchers.instanceOf;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import sportstats.domain.Game;
import sportstats.domain.Team;
import sportstats.domain.broker.BrokerFactory;
import sportstats.domain.broker.GameBroker;
import sportstats.domain.broker.TeamBroker;

/**
 *
 * @author Davik
 */
public class GetGamesByTeamIdsServiceTest {
    
    @Test
    public void firstTeamIdIsNullThrowsException() {
        try {
            new GetGamesByTeamIdsService(null, 1L);
            fail("Should throw exception");
        } catch (SportstatsServiceException ex) {}
    }
    
    @Test
    public void secondTeamIdIsNullThrowsException() {
        try {
            new GetGamesByTeamIdsService(1L, null);
            fail("Should throw exception");
        } catch (SportstatsServiceException ex) {}
    }
    
    @Test
    public void nonExistantFirstTeamThrowsException() {
        BrokerFactory brokerFactory = getMockedBrokerFactoryWithSettings();
        try {
            new GetGamesByTeamIdsService(0L, 1L)
                    .init(brokerFactory)
                    .execute();
            fail("Should throw exception");
        } catch (SportstatsServiceException ex) {}
    }
    
    @Test
    public void nonExistantSecondTeamThrowsException() {
        BrokerFactory brokerFactory = getMockedBrokerFactoryWithSettings();
        try {
            new GetGamesByTeamIdsService(1L, 0L)
                    .init(brokerFactory)
                    .execute();
            fail("Should throw exception");
        } catch (SportstatsServiceException ex) {}
    }
    
    @Test
    public void existingTeamsReturnsListOfGame() {
        BrokerFactory brokerFactory = getMockedBrokerFactoryWithSettings();
        
        List<Game> games = null;
        try {
            games = new GetGamesByTeamIdsService(1L, 1L)
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
        
        when(gameBroker.findByTeamIds(1L, 1L)).thenReturn(mockedGames);
        when(teamBroker.findById(0L)).thenReturn(null);
        when(teamBroker.findById(1L)).thenReturn(mockedTeam);
        
        return brokerFactory;
    }
}
