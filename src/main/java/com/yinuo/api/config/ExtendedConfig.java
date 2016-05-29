package com.yinuo.api.config;

import com.yinuo.api.util.Base64Util;
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
 * @since 2016-05-29
 */
public class ExtendedConfig {
    static final Logger LOGGER = LoggerFactory.getLogger(ExtendedConfig.class);

    static class ExtendedConfigHolder {
        static final ExtendedConfig INSTANCE = new ExtendedConfig();
    }

    public static ExtendedConfig getInstance() {
        return ExtendedConfigHolder.INSTANCE;
    }

    Properties props;

    private ExtendedConfig() {
        props = new Properties();
        InputStream input = null;

        try {
            input = getClass().getClassLoader().getResourceAsStream("extended.properties");
            props.load(input);
        } catch (IOException ex) {
            LOGGER.error("load extended config file error");
            throw new RuntimeException("fatal error, caused by extended config error");
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    LOGGER.error("close extended config file error");
                }
            }
        }
    }

    private String getClientId() {
        return props.getProperty("security.clientId");
    }

    private String getClientSecret() {
        return props.getProperty("security.clientSecret");
    }

    public String getBasicAuthValue() {
        final String clientId = getClientId();
        final String clientSecret = getClientSecret();
        return Base64Util.encode(clientId + ":" + clientSecret);
    }
}
