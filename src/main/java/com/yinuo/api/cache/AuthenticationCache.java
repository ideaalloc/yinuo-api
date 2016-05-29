package com.yinuo.api.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;

import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Title.
 * <p>
 * To be replaced with Redis.
 *
 * @author Bill Lv {@literal <billcc.lv@hotmail.com>}
 * @version 1.0
 * @since 2016-05-28
 */
public class AuthenticationCache {
    static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationCache.class);

    static class AuthenticationCacheHolder {
        static final AuthenticationCache INSTANCE = new AuthenticationCache();
    }

    public static AuthenticationCache getInstance() {
        return AuthenticationCacheHolder.INSTANCE;
    }

    Cache<String, Authentication> authenticationCache;

    Cache<String, String> tokenCache;

    private AuthenticationCache() {
        authenticationCache = CacheBuilder.newBuilder()
                .maximumSize(1000)
                .expireAfterAccess(5, TimeUnit.MINUTES)
                .build();
        tokenCache = CacheBuilder.newBuilder()
                .maximumSize(100)
                .build();
    }

    public String generateNewToken() {
        return UUID.randomUUID().toString();
    }

    public void store(String token, Authentication authentication) {
        authenticationCache.put(token, authentication);
    }

    public boolean contains(String token) {
        return authenticationCache.getIfPresent(token) != null;
    }

    public Authentication retrieve(String token) {
        return authenticationCache.getIfPresent(token);
    }

    public String getNonExpiredToken(String basicAuthValue) throws ExecutionException {
        return tokenCache.get(basicAuthValue, () -> generateNewToken());
    }
}
