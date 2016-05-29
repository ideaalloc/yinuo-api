package com.yinuo.api.security;

import com.yinuo.api.exception.UsernamePasswordNotMatchException;
import com.yinuo.api.model.vo.Principal;
import com.yinuo.api.service.UserService;
import com.yinuo.api.ws.ExternalWebServiceStub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.stream.Collectors;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author Bill Lv {@literal <billcc.lv@hotmail.com>}
 * @version 1.0
 * @since 2016-05-28
 */
public class SomeExternalServiceAuthenticator implements ExternalServiceAuthenticator {

    @Autowired
    UserService userService;

    @Override
    public AuthenticationWithToken authenticate(String username, String password) {
        ExternalWebServiceStub externalWebService = new ExternalWebServiceStub();

        if (!userService.authenticate(username, password)) {
            throw new UsernamePasswordNotMatchException("username or password incorrect");
        }

        final Long uid = userService.findId(username);

        AuthenticatedExternalWebService authenticatedExternalWebService =
                new AuthenticatedExternalWebService(new Principal(uid, username), null,
                        AuthorityUtils.commaSeparatedStringToAuthorityList(
                                userService.findAuthorities(username).stream().collect(Collectors.joining(","))));
        authenticatedExternalWebService.setExternalWebService(externalWebService);

        return authenticatedExternalWebService;
    }
}
