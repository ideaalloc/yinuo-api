package com.yinuo.api.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author Bill Lv {@literal <billcc.lv@hotmail.com>}
 * @version 1.0
 * @since 2016-05-26
 */
public class SmsConfig {
    static final Logger LOGGER = LoggerFactory.getLogger(SmsConfig.class);

    Properties props;

    static class SmsConfigHolder {
        static final SmsConfig INSTANCE = new SmsConfig();
    }

    public static SmsConfig getInstance() {
        return SmsConfigHolder.INSTANCE;
    }

    private SmsConfig() {
        props = new Properties();
        InputStream input = null;

        try {
            input = getClass().getClassLoader().getResourceAsStream("sms.properties");
            props.load(input);
        } catch (IOException ex) {
            LOGGER.error("load sms config file error");
            throw new RuntimeException("fatal error, caused by sms config error");
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    LOGGER.error("close sms config file error");
                }
            }
        }
    }


    public String getAppKey() {
        return props.getProperty("sms.appKey");
    }

    public String getUrl() {
        return props.getProperty("sms.url");
    }

    public String getTpl() {
        return props.getProperty("sms.tpl");
    }

    public Integer getConnTimeout() {
        return Integer.parseInt(props.getProperty("sms.conn.timeout"));
    }

    public Integer getReadTimeout() {
        return Integer.parseInt(props.getProperty("sms.read.timeout"));
    }

}
