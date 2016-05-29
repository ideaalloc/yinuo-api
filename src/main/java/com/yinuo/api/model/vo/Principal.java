package com.yinuo.api.model.vo;

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
public class Principal implements Serializable {
    Long uid;
    String username;

    public Principal() {
    }

    public Principal(Long uid, String username) {
        this.uid = uid;
        this.username = username;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Principal{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                '}';
    }
}
