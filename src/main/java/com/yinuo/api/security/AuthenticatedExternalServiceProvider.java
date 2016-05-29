package com.yinuo.api.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author Bill Lv {@literal <billcc.lv@hotmail.com>}
 * @version 1.0
 * @since 2016-05-28
 */
@Component
public class AuthenticatedExternalServiceProvider {
    public AuthenticatedExternalWebService provide() {
        return (AuthenticatedExternalWebService) SecurityContextHolder.getContext().getAuthentication();
    }
}
