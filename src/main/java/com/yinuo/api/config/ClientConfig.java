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
public class ClientConfig {
    static final Logger LOGGER = LoggerFactory.getLogger(ClientConfig.class);

    Properties props;

    static class ClientConfigHolder {
        static final ClientConfig INSTANCE = new ClientConfig();
    }

    public static ClientConfig getInstance() {
        return ClientConfigHolder.INSTANCE;
    }

    private ClientConfig() {
        props = new Properties();
        InputStream input = null;

        try {
            input = getClass().getClassLoader().getResourceAsStream("client.properties");
            props.load(input);
        } catch (IOException ex) {
            LOGGER.error("load client config file error");
            throw new RuntimeException("fatal error, caused by client config error");
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    LOGGER.error("close client config file error");
                }
            }
        }
    }

    public Integer getRequestTimeout() {
        return Integer.parseInt(props.getProperty("client.requestTimeout"));
    }

    public Integer getConnectTimeout() {
        return Integer.parseInt(props.getProperty("client.connectTimeout"));
    }

    public Integer getSocketTimeout() {
        return Integer.parseInt(props.getProperty("client.socketTimeout"));
    }
}
