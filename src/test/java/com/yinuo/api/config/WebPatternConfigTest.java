package com.yinuo.api.config;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * Created by Bill Lv on 5/28/16.
 */
public class WebPatternConfigTest {
    static final Logger LOGGER = LoggerFactory.getLogger(WebPatternConfigTest.class);

//    @Test
//    public void matchPattern() throws Exception {
//        final String patternStr = "/v1/flights/**";
//        final String newPatternStr = patternStr.replaceAll("\\*\\*", "\\\\S*");
//        LOGGER.info(newPatternStr);
//        final Boolean match = WebPatternConfig.getInstance().matchPattern(newPatternStr, "/v1/flights/1");
//        LOGGER.info(String.valueOf(match));
//    }

    @Test
    public void getAntPatterns() throws Exception {
        final String[] antPatterns = WebPatternConfig.getInstance().getAntPatterns();
        LOGGER.info(Arrays.asList(antPatterns).toString());
    }

}