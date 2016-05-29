package com.yinuo.api.ws;

import com.yinuo.api.domain.Stuff;
import com.yinuo.api.model.vo.Principal;

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
public interface ServiceGateway {
    List<Stuff> getSomeStuff();

    void createStuff(Stuff newStuff, Principal domainUser);
}
