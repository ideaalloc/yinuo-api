package com.yinuo.api.controller;

import com.yinuo.api.domain.Stuff;
import com.yinuo.api.model.annotation.CurrentlyLoggedUser;
import com.yinuo.api.model.vo.Principal;
import com.yinuo.api.ws.ServiceGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
@RestController
@PreAuthorize("hasAnyAuthority('ROLE_PATIENT','ROLE_DOCTOR')")
public class SampleController extends ApiController {
    private final ServiceGateway serviceGateway;

    @Autowired
    public SampleController(ServiceGateway serviceGateway) {
        this.serviceGateway = serviceGateway;
    }

    @RequestMapping(value = STUFF_URL, method = RequestMethod.GET)
    public List<Stuff> getSomeStuff() {
        return serviceGateway.getSomeStuff();
    }

    @RequestMapping(value = STUFF_URL, method = RequestMethod.POST)
    public void createStuff(@RequestBody Stuff newStuff, @CurrentlyLoggedUser Principal domainUser) {
        serviceGateway.createStuff(newStuff, domainUser);
    }
}
