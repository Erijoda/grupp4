/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportstats.service;

import sportstats.domain.broker.BrokerFactory;

/**
 *
 * @author Rebecca
 */
public interface SportstatsService<T> {

    SportstatsService<T> init(BrokerFactory brokerFactory);

    T execute();
}
