package com.yinuo.api.ws;

import com.yinuo.api.domain.Stuff;
import com.yinuo.api.model.vo.Principal;
import com.yinuo.api.security.AuthenticatedExternalServiceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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
public class ServiceGatewayImpl extends ServiceGatewayBase implements ServiceGateway {
    @Autowired
    public ServiceGatewayImpl(AuthenticatedExternalServiceProvider authenticatedExternalServiceProvider) {
        super(authenticatedExternalServiceProvider);
    }

    @Override
    public List<Stuff> getSomeStuff() {
        String stuffFromExternalWebService = externalService().getSomeStuff();
        // do some processing, create return list
        return null;
    }

    @Override
    public void createStuff(Stuff newStuff, Principal domainUser) {

    }
}
