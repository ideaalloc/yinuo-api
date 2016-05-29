package com.yinuo.api.security;

import com.google.common.base.Optional;
import com.yinuo.api.cache.AuthenticationCache;
import com.yinuo.api.security.AuthenticationWithToken;
import com.yinuo.api.security.ExternalServiceAuthenticator;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author Bill Lv {@literal <billcc.lv@hotmail.com>}
 * @version 1.0
 * @since 2016-05-28
 */
public class DomainUsernamePasswordAuthenticationProvider implements AuthenticationProvider {
    static final AuthenticationCache authenticationCache = AuthenticationCache.getInstance();
    private ExternalServiceAuthenticator externalServiceAuthenticator;

    public DomainUsernamePasswordAuthenticationProvider(ExternalServiceAuthenticator externalServiceAuthenticator) {
        this.externalServiceAuthenticator = externalServiceAuthenticator;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Optional<String> username = (Optional) authentication.getPrincipal();
        Optional<String> password = (Optional) authentication.getCredentials();

        if (!username.isPresent() || !password.isPresent()) {
            throw new BadCredentialsException("Invalid Domain User Credentials");
        }

        AuthenticationWithToken resultOfAuthentication = externalServiceAuthenticator.authenticate(username.get(), password.get());
        String newToken = authenticationCache.generateNewToken();
        resultOfAuthentication.setToken(newToken);
        authenticationCache.store(newToken, resultOfAuthentication);

        return resultOfAuthentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
