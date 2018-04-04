/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportstats.db;

import org.javalite.activejdbc.Base;

/**
 *
 * @author Rebecca
 */
public class DbConn {
    private static String Dburl = System.getProperty("dburl", "node71250-env-grupp4.jls-sto1.elastx.net:11069/sportstats1");
    public static void _open() {
        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://" + Dburl + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "sportstats", "asafepassw");
    }
    
    public static void _close() {
        Base.close();
    }
    
    public void open() {
        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://" + Dburl + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "sportstats", "asafepassw");
    }
    
    public void close() {
        Base.close();
    }
}
