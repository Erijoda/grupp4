/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportstats.domain;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Davik
 */
public class TableRow {
    private Integer teamId;
    private String teamName;
    private Long gamesPlayed;
    private BigDecimal gamesWon;
    private BigDecimal gamesTied;
    private BigDecimal gamesLost;
    private BigDecimal gamesWonOT;
    private BigDecimal gamesLostOT;
    private BigDecimal goals;
    private BigDecimal goalsAgainst;
    private Long points;

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

    public BigDecimal getGamesWon() {
        return gamesWon;
    }

    public void setGamesWon(BigDecimal gamesWon) {
        this.gamesWon = gamesWon;
    }

    public BigDecimal getGamesTied() {
        return gamesTied;
    }

    public void setGamesTied(BigDecimal gamesTied) {
        this.gamesTied = gamesTied;
    }

    public BigDecimal getGamesLost() {
        return gamesLost;
    }

    public void setGamesLost(BigDecimal gamesLost) {
        this.gamesLost = gamesLost;
    }

    public BigDecimal getGamesWonOT() {
        return gamesWonOT;
    }

    public void setGamesWonOT(BigDecimal gamesWonOT) {
        this.gamesWonOT = gamesWonOT;
    }

    public BigDecimal getGamesLostOT() {
        return gamesLostOT;
    }

    public void setGamesLostOT(BigDecimal gamesLostOT) {
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
