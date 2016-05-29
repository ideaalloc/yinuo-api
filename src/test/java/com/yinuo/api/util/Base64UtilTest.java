package com.yinuo.api.util;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Bill Lv on 5/28/16.
 */
public class Base64UtilTest {
    static final Logger LOGGER = LoggerFactory.getLogger(Base64UtilTest.class);

    @Test
    public void encode() throws Exception {
        final String encode = Base64Util.encode("ab95373488:7dsh0jsdopjw5jwq3hjw5");
        LOGGER.info(encode);
    }

}