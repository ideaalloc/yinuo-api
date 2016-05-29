package com.yinuo.api.security;

import com.google.common.base.Optional;
import com.yinuo.api.cache.AuthenticationCache;
import com.yinuo.api.config.ExtendedConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import java.util.concurrent.ExecutionException;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author Bill Lv {@literal <billcc.lv@hotmail.com>}
 * @version 1.0
 * @since 2016-05-28
 */
public class TokenAuthenticationProvider implements AuthenticationProvider {
    final static Logger LOGGER = LoggerFactory.getLogger(TokenAuthenticationProvider.class);

    static final AuthenticationCache authenticationCache = AuthenticationCache.getInstance();
    static final ExtendedConfig extendedConfig = ExtendedConfig.getInstance();

    public TokenAuthenticationProvider() {
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Optional<String> token = (Optional) authentication.getPrincipal();
        if (!token.isPresent() || token.get().isEmpty()) {
            throw new BadCredentialsException("Invalid token");
        }
        try {
            if (token.get().equals(authenticationCache.getNonExpiredToken(extendedConfig.getBasicAuthValue()))) {
                authentication.setAuthenticated(true);
                return authentication;
            }
        } catch (ExecutionException e) {
            LOGGER.error("execution error");
            throw new BadCredentialsException("execution error", e);
        }
        if (!authenticationCache.contains(token.get())) {
            throw new BadCredentialsException("Invalid token or token expired");
        }
        return authenticationCache.retrieve(token.get());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(PreAuthenticatedAuthenticationToken.class);
    }
}
