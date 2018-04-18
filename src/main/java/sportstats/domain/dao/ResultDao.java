/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportstats.domain.dao;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;
import sportstats.constants.WinType;


/**
 *
 * @author Rebecca
 */
@Table ("results")
public class ResultDao extends Model {
    public WinType getWinType() {
        return WinType.valueOf(getInteger("win_type"));
    }
    
    public void setWinType(WinType winType) {
        setInteger("win_type", winType.getValue());
    }
}
