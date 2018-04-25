/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportstats.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Davik
 */
public class TableRow {
    private List<Integer> seasonIds = new ArrayList<>();
    private Integer teamId;
    private String teamName;
    private Long gamesPlayed;
    private Long gamesWon;
    private Long gamesTied;
    private Long gamesLost;
    private Long gamesWonOT;
    private Long gamesLostOT;
    private BigDecimal goals;
    private BigDecimal goalsAgainst;
    private Long points;

    public List<Integer> getSeasonIds() {
        return seasonIds;
    }

    public void addSeasonId(Integer seasonId) {
        this.seasonIds.add(seasonId);
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Long getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(Long gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public Long getGamesWon() {
        return gamesWon;
    }

    public void setGamesWon(Long gamesWon) {
        this.gamesWon = gamesWon;
    }

    public Long getGamesTied() {
        return gamesTied;
    }

    public void setGamesTied(Long gamesTied) {
        this.gamesTied = gamesTied;
    }

    public Long getGamesLost() {
        return gamesLost;
    }

    public void setGamesLost(Long gamesLost) {
        this.gamesLost = gamesLost;
    }

    public Long getGamesWonOT() {
        return gamesWonOT;
    }

    public void setGamesWonOT(Long gamesWonOT) {
        this.gamesWonOT = gamesWonOT;
    }

    public Long getGamesLostOT() {
        return gamesLostOT;
    }

    public void setGamesLostOT(Long gamesLostOT) {
        this.gamesLostOT = gamesLostOT;
    }

    public BigDecimal getGoals() {
        return goals;
    }

    public void setGoals(BigDecimal goals) {
        this.goals = goals;
    }

    public BigDecimal getGoalsAgainst() {
        return goalsAgainst;
    }

    public void setGoalsAgainst(BigDecimal goalsAgainst) {
        this.goalsAgainst = goalsAgainst;
    }

    public Long getPoints() {
        return points;
    }

    public void setPoints(Long points) {
        this.points = points;
    }
    
    
}
