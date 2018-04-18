/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportstats.constants;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author davik
 */
public enum WinType {
    NORMAL(0), OVERTIME(1), SHOOTOUT(2);
    
    private final int value;
    private final static Map<Integer, WinType> valueMap = new HashMap<>();
    
    static {
        for (WinType winType : WinType.values()) {
            valueMap.put(winType.getValue(), winType);
        }
    }
    
    private WinType(int value) {
        this.value = value;
    }
    
    public static WinType valueOf(int value) {
        return valueMap.get(value);
    }
    
    public int getValue() {
        return value;
    }
}
