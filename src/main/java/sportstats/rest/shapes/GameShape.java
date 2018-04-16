/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportstats.rest.shapes;

import java.sql.Date;

/**
 *
 * @author Davik
 */
public class GameShape {
    public Long homeTeamId;
    public Long awayTeamId;
    public Date date;
    
    public GameShape() {}
}
