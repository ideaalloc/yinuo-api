package com.yinuo.api.model.type;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Bill Lv on 5/28/16.
 */
public class AuthorityTest {
    static final Logger LOGGER = LoggerFactory.getLogger(AuthorityTest.class);

    @Test
    public void getDomainAuthorities() throws Exception {
        final String domainAuthorities = Authority.getDomainAuthorities();
        LOGGER.info(domainAuthorities);
    }

}