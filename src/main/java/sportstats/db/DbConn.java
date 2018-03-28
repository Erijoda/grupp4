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
    public static void _open() {
        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/sportstats1?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "admin");
    }
    
    public static void _close() {
        Base.close();
    }
    
    public void open() {
        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/sportstats1?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "admin");
    }
    
    public void close() {
        Base.close();
    }
}
