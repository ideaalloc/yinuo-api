package com.yinuo.api.service;

import com.yinuo.api.repository.dao.FlightDao;
import com.yinuo.api.repository.po.Flights;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author Bill Lv {@literal <billcc.lv@hotmail.com>}
 * @version 1.0
 * @since 2016-05-13
 */
@Service
public class FlightService {
    static final Logger LOGGER = LoggerFactory.getLogger(FlightService.class);

    @Autowired
    FlightDao flightDao;

    @Transactional
    public Flights findById(Long id) {
        return flightDao.findById(id);
    }
}
