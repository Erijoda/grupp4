/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportstats.spikes;

import sportstats.domain.Season;

/**
 *
 * @author Rebecca
 */
public class SecondShot {
    
    public static void main(String[] args) {
        //open();
        Season season = new Season();
        //season.setYear(2016, 4L);
        season.setSummer(Boolean.TRUE);
        System.out.println(season.getName());
        season.setSummer(Boolean.FALSE);
        System.out.println(season.getName());
        //close();
    }
}
