/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportstats.service;

import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;
import org.junit.Test;
import static org.mockito.Mockito.*;
import sportstats.domain.Game;
import sportstats.domain.Round;
import sportstats.domain.Season;
import sportstats.domain.Team;
import sportstats.domain.broker.BrokerFactory;
import sportstats.domain.broker.GameBroker;
import sportstats.domain.broker.RoundBroker;
import sportstats.domain.broker.SeasonBroker;
import sportstats.domain.broker.TeamBroker;
import sportstats.rest.shapes.GameShape;

/**
 *
 * @author Davik
 */
public class AddRoundServiceTest {
    @Test
    public void seasonIdIsNullThrowsException() {
        List<GameShape> games = new ArrayList<>();
        GameShape firstGame = new GameShape();
        GameShape secondGame = new GameShape();
        firstGame.awayTeamId = 1L;
        firstGame.homeTeamId = 1L;
        secondGame.awayTeamId = 1L;
        secondGame.homeTeamId = 1L;
        games.add(firstGame);
        games.add(secondGame);
        
        try {
            new AddRoundService(null, games);
            fail("Should throw exception");
        } catch (SportstatsServiceException ex) {}
    }
    
    @Test
    public void gamesIsNullThrowsException() {
        try {
            new AddRoundService(1L, null);
            fail("Should throw exception");
        } catch (SportstatsServiceException ex) {}
    }
    
    @Test
    public void gamesIsEmptyThrowsException() {
        List<GameShape> games = new ArrayList<GameShape>();
        
        try {
            new AddRoundService(1L, games);
            fail("Should throw exception");
        } catch (SportstatsServiceException ex) {}
    }
    
    @Test
    public void nonExistantSeasonThrowsException() {
        List<GameShape> games = new ArrayList<>();
        GameShape firstGame = new GameShape();
        GameShape secondGame = new GameShape();
        firstGame.awayTeamId = 1L;
        firstGame.homeTeamId = 1L;
        secondGame.awayTeamId = 1L;
        secondGame.homeTeamId = 1L;
        games.add(firstGame);
        games.add(secondGame);
        BrokerFactory brokerFactory = getMockedBrokerFactoryWithSettings();
        
        try {
            new AddRoundService(0L, games)
                    .init(brokerFactory)
                    .execute();
            fail("Should throw exception");
        } catch (SportstatsServiceException ex) {}
    }
    
    @Test
    public void nonExistantAwayTeamThrowsException() {
        List<GameShape> games = new ArrayList<>();
        GameShape firstGame = new GameShape();
        GameShape secondGame = new GameShape();
        firstGame.awayTeamId = 1L;
        firstGame.homeTeamId = 1L;
        secondGame.awayTeamId = 0L;
        secondGame.homeTeamId = 1L;
        games.add(firstGame);
        games.add(secondGame);
        BrokerFactory brokerFactory = getMockedBrokerFactoryWithSettings();
        
        try {
            new AddRoundService(1L, games)
                    .init(brokerFactory)
                    .execute();
            fail("Should throw exception");
        } catch (SportstatsServiceException ex) {}
    }
    
    @Test
    public void nonExistantHomeTeamThrowsException() {
        List<GameShape> games = new ArrayList<>();
        GameShape firstGame = new GameShape();
        GameShape secondGame = new GameShape();
        firstGame.awayTeamId = 1L;
        firstGame.homeTeamId = 1L;
        secondGame.awayTeamId = 1L;
        secondGame.homeTeamId = 0L;
        games.add(firstGame);
        games.add(secondGame);
        BrokerFactory brokerFactory = getMockedBrokerFactoryWithSettings();
        
        try {
            new AddRoundService(1L, games)
                    .init(brokerFactory)
                    .execute();
            fail("Should throw exception");
        } catch (SportstatsServiceException ex) {}
    }
    
    @Test
    public void existingSeasonHomeAndAwayTeamReturnsRound() {
        List<GameShape> games = new ArrayList<>();
        GameShape firstGame = new GameShape();
        GameShape secondGame = new GameShape();
        firstGame.awayTeamId = 1L;
        firstGame.homeTeamId = 1L;
        secondGame.awayTeamId = 1L;
        secondGame.homeTeamId = 1L;
        games.add(firstGame);
        games.add(secondGame);
        BrokerFactory brokerFactory = getMockedBrokerFactoryWithSettings();
        
        Round result = null;
        try {
            result = new AddRoundService(1L, games)
                    .init(brokerFactory)
                    .execute();
        } catch (SportstatsServiceException ex) {
            fail("Should not throw exception");
        }
        
        assertThat(result, instanceOf(Round.class));
    }
    
    private BrokerFactory getMockedBrokerFactory() {
        BrokerFactory brokerFactory = mock(BrokerFactory.class);
        GameBroker gameBroker = mock(GameBroker.class);
        RoundBroker roundBroker = mock(RoundBroker.class);
        SeasonBroker seasonBroker = mock(SeasonBroker.class);
        TeamBroker teamBroker = mock(TeamBroker.class);
        
        when(brokerFactory.getGameBroker()).thenReturn(gameBroker);
        when(brokerFactory.getRoundBroker()).thenReturn(roundBroker);
        when(brokerFactory.getSeasonBroker()).thenReturn(seasonBroker);
        when(brokerFactory.getTeamBroker()).thenReturn(teamBroker);
        
        return brokerFactory;
    }
            
    private BrokerFactory getMockedBrokerFactoryWithSettings() {
        BrokerFactory brokerFactory = getMockedBrokerFactory();
        
        GameBroker gameBroker = brokerFactory.getGameBroker();
        RoundBroker roundBroker = brokerFactory.getRoundBroker();
        SeasonBroker seasonBroker = brokerFactory.getSeasonBroker();
        TeamBroker teamBroker = brokerFactory.getTeamBroker();
        
        Game mockedGame = mock(Game.class);
        Round mockedRound = mock(Round.class);
        Season mockedSeason = mock(Season.class);
        Team mockedTeam = mock(Team.class);
        
        when(gameBroker.create()).thenReturn(mockedGame);
        when(roundBroker.create()).thenReturn(mockedRound);
        when(seasonBroker.findById(0L)).thenReturn(null);
        when(seasonBroker.findById(1L)).thenReturn(mockedSeason);
        when(teamBroker.findById(0L)).thenReturn(null);
        when(teamBroker.findById(1L)).thenReturn(mockedTeam);
        
        return brokerFactory;
    }
}
