package com.yinuo.api.config;

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
public class ImageConfig {
    @Value("${image.highquality.compressratio}")
    Double highQualityCompressRatio;

    @Value("${image.middlequality.compressratio}")
    Double middleQualityCompressRatio;

    @Value("${image.smallquality.compressratio}")
    Double smallQualityCompressRatio;

    @Value("${image.compress.tmp.path}")
    String compressTmpPath;

    @Value("${image.highquality.prefix}")
    String highQualityPrefix;

    @Value("${image.middlequality.prefix}")
    String middleQualityPrefix;

    @Value("${image.smallquality.prefix}")
    String smallQualityPrefix;

    @Value("${image.suffix}")
    String suffix;

    public ImageConfig() {
    }

    public ImageConfig(Double highQualityCompressRatio, Double middleQualityCompressRatio, Double smallQualityCompressRatio, String compressTmpPath, String highQualityPrefix, String middleQualityPrefix, String smallQualityPrefix, String suffix) {
        this.highQualityCompressRatio = highQualityCompressRatio;
        this.middleQualityCompressRatio = middleQualityCompressRatio;
        this.smallQualityCompressRatio = smallQualityCompressRatio;
        this.compressTmpPath = compressTmpPath;
        this.highQualityPrefix = highQualityPrefix;
        this.middleQualityPrefix = middleQualityPrefix;
        this.smallQualityPrefix = smallQualityPrefix;
        this.suffix = suffix;
    }

    @Bean
    public ImageConfig imageConfiguration() {
        return new ImageConfig(highQualityCompressRatio, middleQualityCompressRatio, smallQualityCompressRatio, compressTmpPath, highQualityPrefix, middleQualityPrefix, smallQualityPrefix, suffix);
    }

    public Double getHighQualityCompressRatio() {
        return highQualityCompressRatio;
    }

    public Double getMiddleQualityCompressRatio() {
        return middleQualityCompressRatio;
    }

    public Double getSmallQualityCompressRatio() {
        return smallQualityCompressRatio;
    }

    public String getCompressTmpPath() {
        return compressTmpPath;
    }

    public String getHighQualityPrefix() {
        return highQualityPrefix;
    }

    public String getMiddleQualityPrefix() {
        return middleQualityPrefix;
    }

    public String getSmallQualityPrefix() {
        return smallQualityPrefix;
    }

    public String getSuffix() {
        return suffix;
    }
}
