package com.yinuo.api.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.yinuo.api.model.vo.SmsResponse;
import com.yinuo.api.util.SmsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Title.
 * <p>
 * To be replaced with Redis.
 *
 * @author Bill Lv {@literal <billcc.lv@hotmail.com>}
 * @version 1.0
 * @since 2016-05-27
 */
public class VCodeCache {
    static final Logger LOGGER = LoggerFactory.getLogger(VCodeCache.class);

    static class VCodeCacheHolder {
        static final VCodeCache INSTANCE = new VCodeCache();
    }

    public static VCodeCache getInstance() {
        return VCodeCacheHolder.INSTANCE;
    }


    Cache<String, SmsResponse> smsCache;

    Cache<String, String> codeCache;

    private VCodeCache() {
        smsCache = CacheBuilder.newBuilder()
                .maximumSize(1000)
                .expireAfterWrite(2, TimeUnit.MINUTES)
                .build();
        codeCache = CacheBuilder.newBuilder()
                .maximumSize(1000)
                .expireAfterWrite(2, TimeUnit.MINUTES)
                .build();
    }

    /**
     * 获取短信,如果已经缓存了,就直接提取出来,
     * 如果没有缓存,就随机生成一个,并发送短信验证,最后缓存起来
     *
     * @param mobile
     * @return 短信验证码
     */
    public SmsResponse getSms(String mobile) throws ExecutionException, CacheLoader.InvalidCacheLoadException {
        return smsCache.get(mobile, () -> {
            final String code = codeCache.get(mobile, () -> createVerification());
            return SmsUtil.sendVMsg(mobile, code);
        });
    }

    /**
     * 获取缓存验证码,如果已经过期,或者不存在,则返回null
     *
     * @param mobile
     * @return
     */
    public String getCode(String mobile) {
        return codeCache.getIfPresent(mobile);
    }

    private String createVerification() {
        int r = (int) (Math.random() * 9000 + 1000);
        return String.valueOf(r);
    }

}
