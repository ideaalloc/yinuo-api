package com.yinuo.api.client;

import org.apache.commons.io.IOUtils;
import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * Title.
 * <p/>
 * Description.
 *
 * @author Bill Lv {@literal <billcc.lv@hotmail.com>}
 * @version 1.0
 * @since 2015-09-14
 */
public abstract class BaseClient {
    static final Logger LOGGER = LoggerFactory.getLogger(BaseClient.class);

    static final String CONTENT_TYPE = "application/json";

    protected abstract CloseableHttpClient createClient();

    protected abstract String getCredentials();

    protected abstract String getUserAgent();

    public String get(String url) throws IOException {
        HttpGet request = new HttpGet(url);
        request.setHeader("User-Agent", getUserAgent());
        request.setHeader("accept", CONTENT_TYPE);
        if (getCredentials() != null) {
            request.setHeader("Authorization", getCredentials());
        }

        return service(request);
    }

    public String post(String url, String jsonIn) throws IOException {
        HttpPost request = new HttpPost(url);
        request.setHeader("User-Agent", getUserAgent());
        request.setHeader("Content-Type", CONTENT_TYPE);
        if (getCredentials() != null) {
            request.setHeader("Authorization", getCredentials());
        }

        StringEntity stringEntity = new StringEntity(jsonIn, Consts.UTF_8);
        stringEntity.setContentType(CONTENT_TYPE);
        request.setEntity(stringEntity);

        return service(request);
    }

    private String service(HttpRequestBase request) throws IOException {
        CloseableHttpClient client = null;
        try {
            client = createClient();
            HttpResponse response = client.execute(request);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200 || statusCode == 201) {
                InputStream input = null;
                try {
                    input = response.getEntity().getContent();
                    String content = IOUtils.toString(input, Consts.UTF_8);
                    LOGGER.info("response content: {}", content);
                    return content;
                } catch (IOException e) {
                    LOGGER.error("", e);
                    throw new RuntimeException(e);
                } finally {
                    if (input != null) {
                        try {
                            input.close();
                        } catch (IOException e) {
                            LOGGER.error("", e);
                            throw new RuntimeException(e);
                        }
                    }
                }
            } else {
                throw new RuntimeException(String.format("Error status code %d", statusCode));
            }
        } finally {
            if (request != null) {
                request.releaseConnection();
            }
            if (client != null) {
                client.close();
            }
        }
    }
}
