package com.yinuo.api.config;

import com.aliyun.oss.OSSClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Title.
 * <p/>
 * Description.
 *
 * @author Bill Lv {@literal <billcc.lv@hotmail.com>}
 * @version 1.0
 * @since 2015-10-30
 */
@Configuration
@EnableConfigurationProperties
public class OssConfig {
    @Value("${thirdservice.oss.accessKeyId}")
    String accessKeyId;

    @Value("${thirdservice.oss.accessKeySecret}")
    String accessKeySecret;

    @Value("${thirdservice.oss.endpoint}")
    String endpoint;

    @Value("${thirdservice.oss.internal.endpoint}")
    String internalEndpoint;

    @Value("${thirdservice.oss.bucket}")
    String bucket;

    public OssConfig() {
    }

    public OssConfig(String accessKeyId, String accessKeySecret, String endpoint, String internalEndpoint, String bucket) {
        this.accessKeyId = accessKeyId;
        this.accessKeySecret = accessKeySecret;
        this.endpoint = endpoint;
        this.internalEndpoint = internalEndpoint;
        this.bucket = bucket;
    }

    @Bean
    public OssConfig ossConfiguration() {
        return new OssConfig(accessKeyId, accessKeySecret, endpoint, internalEndpoint, bucket);
    }

    @Bean
    public OSSClient ossClient() {
        return new OSSClient(internalEndpoint, accessKeyId, accessKeySecret);
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public String getInternalEndpoint() {
        return internalEndpoint;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public String getBucket() {
        return bucket;
    }
}
