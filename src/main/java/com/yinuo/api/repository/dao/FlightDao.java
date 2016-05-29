package com.yinuo.api.repository.dao;

import com.yinuo.api.repository.po.Flights;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author Bill Lv {@literal <billcc.lv@hotmail.com>}
 * @version 1.0
 * @since 2016-05-13
 */
public interface FlightDao {
    Flights findById(Long id);
}
