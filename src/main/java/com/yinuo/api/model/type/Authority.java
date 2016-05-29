package com.yinuo.api.model.type;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author Bill Lv {@literal <billcc.lv@hotmail.com>}
 * @version 1.0
 * @since 2016-05-28
 */
public enum Authority {
    ROLE_SYSTEM_ADMIN("系统管理员"), ROLE_ADMIN("管理员"), ROLE_PATIENT("病人"), ROLE_DOCTOR("医生");

    final String desc;

    Authority(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public static String getDomainAuthorities() {
        final Authority[] values = Authority.values();
        return IntStream.range(0, values.length)
                .filter(i -> i > 1)
                .mapToObj(i -> values[i].name())
                .collect(Collectors.joining(","));
    }
}
