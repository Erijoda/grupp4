/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportstats.domain;

import org.javalite.activejdbc.Model;

/**
 *
 * @author Davik
 * @param <T> The Dao
 */
public interface Base<T extends Model> {
    public T getDao();
}
