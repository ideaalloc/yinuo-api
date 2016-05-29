package com.yinuo.api.filter;

import com.google.common.base.Optional;
import com.yinuo.api.cache.AuthenticationCache;
import com.yinuo.api.config.ExtendedConfig;
import com.yinuo.api.config.WebPatternConfig;
import com.yinuo.api.controller.ApiController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
public class AppClientFilter extends GenericFilterBean {
    static final Logger LOGGER = LoggerFactory.getLogger(AppClientFilter.class);
    static final String BASIC_PREFIX = "Basic ";
    static final String OAUTH2_PREFIX = "Bearer ";
    static final AuthenticationCache authenticationCache = AuthenticationCache.getInstance();
    static final WebPatternConfig webPatternConfig = WebPatternConfig.getInstance();
    static final ExtendedConfig extendedConfig = ExtendedConfig.getInstance();

    private AuthenticationManager authenticationManager;

    public AppClientFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = asHttp(request);
        HttpServletResponse httpResponse = asHttp(response);

        String resourcePath = new UrlPathHelper().getPathWithinApplication(httpRequest);
        Optional<String> authorization = Optional.fromNullable(httpRequest.getHeader("Authorization"));

        if (postToAuthenticate(httpRequest, resourcePath)) {
            if (authorization.isPresent()) {
                final String auth = authorization.get();
                if (auth.startsWith(BASIC_PREFIX)) {
                    if (auth.length() == BASIC_PREFIX.length()) {
                        throw new BadCredentialsException("Basic value invalid");
                    }
                    final String encodedBasic = auth.substring(BASIC_PREFIX.length());
                    if (extendedConfig.getBasicAuthValue().equals(encodedBasic)) {
                        LOGGER.info("Basic authentication passed");
                        final String nonExpiredToken;
                        try {
                            nonExpiredToken = authenticationCache.getNonExpiredToken(encodedBasic);
                        } catch (ExecutionException e) {
                            LOGGER.error("get token error");
                            throw new IOException("get token error");
                        }
                        final StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("{").append('"').append("token").append('"').append(":").append('"')
                                .append(nonExpiredToken).append('"').append("}");
                        httpResponse.addHeader("Content-Type", "application/json");
                        httpResponse.getWriter().print(stringBuilder.toString());
                        return;
                    } else {
                        throw new BadCredentialsException("Basic value invalid");
                    }
                } else {
                    throw new BadCredentialsException("Authorization credentials not found");
                }
            } else {
                throw new BadCredentialsException("Authorization credentials not found");
            }
        }

        if (!webPatternConfig.isAppClientPattern(resourcePath)) {
            chain.doFilter(request, response);
            return;
        }

        if (authorization.isPresent()) {
            final String auth = authorization.get();
            if (auth.startsWith(OAUTH2_PREFIX)) {
                final String nonExpiredToken;
                try {
                    nonExpiredToken = authenticationCache.getNonExpiredToken(extendedConfig.getBasicAuthValue());
                } catch (ExecutionException e) {
                    LOGGER.error("get token error");
                    throw new IOException("get token error");
                }
                if (nonExpiredToken != null && auth.length() > OAUTH2_PREFIX.length() &&
                        auth.substring(OAUTH2_PREFIX.length()).equals(nonExpiredToken)) {
                    LOGGER.info("Bearer token validated");
                    processTokenAuthentication(Optional.of(nonExpiredToken));
                    chain.doFilter(request, response);
                    return;
                } else {
                    throw new BadCredentialsException("Bearer value invalid");
                }
            } else {
                throw new BadCredentialsException("Authorization credentials not found");
            }
        } else {
            throw new BadCredentialsException("Authorization credentials not found");
        }
    }

    private HttpServletRequest asHttp(ServletRequest request) {
        return (HttpServletRequest) request;
    }

    private HttpServletResponse asHttp(ServletResponse response) {
        return (HttpServletResponse) response;
    }

    private boolean postToAuthenticate(HttpServletRequest httpRequest, String resourcePath) {
        return ApiController.APP_AUTHENTICATE_URL.equalsIgnoreCase(resourcePath) && httpRequest.getMethod().equals("POST");
    }

    private void processTokenAuthentication(Optional<String> token) {
        Authentication resultOfAuthentication = tryToAuthenticateWithToken(token);
        SecurityContextHolder.getContext().setAuthentication(resultOfAuthentication);
    }

    private Authentication tryToAuthenticateWithToken(Optional<String> token) {
        PreAuthenticatedAuthenticationToken requestAuthentication = new PreAuthenticatedAuthenticationToken(token, null);
        return tryToAuthenticate(requestAuthentication);
    }

    private Authentication tryToAuthenticate(Authentication requestAuthentication) {
        Authentication responseAuthentication = authenticationManager.authenticate(requestAuthentication);
        if (responseAuthentication == null || !responseAuthentication.isAuthenticated()) {
            throw new InternalAuthenticationServiceException("Unable to authenticate Domain User for provided credentials");
        }
        logger.debug("User successfully authenticated");
        return responseAuthentication;
    }
}
