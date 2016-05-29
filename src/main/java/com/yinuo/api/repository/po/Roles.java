package com.yinuo.api.repository.po;

import java.io.Serializable;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author Bill Lv {@literal <billcc.lv@hotmail.com>}
 * @version 1.0
 * @since 2016-05-28
 */
public class Roles implements Serializable {
    Long uid;
    String authority;

    public Roles() {
    }

    public Roles(Long uid, String authority) {
        this.uid = uid;
        this.authority = authority;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        return "Roles{" +
                "uid=" + uid +
                ", authority='" + authority + '\'' +
                '}';
    }
}
