package com.yinuo.api.security;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author Bill Lv {@literal <billcc.lv@hotmail.com>}
 * @version 1.0
 * @since 2016-05-28
 */
public interface ExternalServiceAuthenticator {
    AuthenticationWithToken authenticate(String username, String password);
}
