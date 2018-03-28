/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportstats.rest.json;

import com.owlike.genson.Genson;

/**
 *
 * @author Rebecca
 */
public class JsonOutputFormatter {
    
    public String createOutput(Object resultData) {
        return new Genson().serialize(resultData);
    }
}
