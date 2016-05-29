package com.yinuo.api.client;

import com.yinuo.api.config.ClientConfig;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author Bill Lv {@literal <billcc.lv@hotmail.com>}
 * @version 1.0
 * @since 2016-05-26
 */
public class CommonClient extends BaseClient {
    static final ClientConfig clientConfig = ClientConfig.getInstance();

    static class CommonClientHolder {
        static final CommonClient INSTANCE = new CommonClient();
    }

    public static CommonClient getInstance() {
        return CommonClientHolder.INSTANCE;
    }

    private CommonClient() {

    }

    @Override
    protected CloseableHttpClient createClient() {
        Integer requestTimeout = clientConfig.getRequestTimeout();
        Integer connectTimeout = clientConfig.getConnectTimeout();
        Integer socketTimeout = clientConfig.getSocketTimeout();

        RequestConfig requestConfig = RequestConfig.custom().
                setConnectionRequestTimeout(requestTimeout)
                .setConnectTimeout(connectTimeout).setSocketTimeout(socketTimeout).build();

        HttpClientBuilder builder = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig);
        return builder.build();
    }

    @Override
    protected String getCredentials() {
        return null;
    }

    @Override
    protected String getUserAgent() {
        return "Mozilla/5.0";
    }
}
