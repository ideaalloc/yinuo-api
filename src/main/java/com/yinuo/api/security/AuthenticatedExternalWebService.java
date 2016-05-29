package com.yinuo.api.security;


import com.yinuo.api.ws.ExternalWebServiceStub;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author Bill Lv {@literal <billcc.lv@hotmail.com>}
 * @version 1.0
 * @since 2016-05-28
 */
public class AuthenticatedExternalWebService extends AuthenticationWithToken {
    private ExternalWebServiceStub externalWebService;

    public AuthenticatedExternalWebService(Object aPrincipal, Object aCredentials, Collection<? extends GrantedAuthority> anAuthorities) {
        super(aPrincipal, aCredentials, anAuthorities);
    }

    public void setExternalWebService(ExternalWebServiceStub externalWebService) {
        this.externalWebService = externalWebService;
    }

    public ExternalWebServiceStub getExternalWebService() {
        return externalWebService;
    }
}
