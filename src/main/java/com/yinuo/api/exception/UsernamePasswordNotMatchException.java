package com.yinuo.api.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * Username and password not match exception.
 * <p>
 * Description.
 *
 * @author Bill Lv {@literal <billcc.lv@hotmail.com>}
 * @version 1.0
 * @since 2016-05-28
 */
public class UsernamePasswordNotMatchException extends AuthenticationException {
    public UsernamePasswordNotMatchException(String msg, Throwable t) {
        super(msg, t);
    }

    public UsernamePasswordNotMatchException(String msg) {
        super(msg);
    }
}
