package com.yinuo.api.model.vo;

import java.io.Serializable;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author Bill Lv {@literal <billcc.lv@hotmail.com>}
 * @version 1.0
 * @since 2016-05-27
 */
public class SmsRequest implements Serializable {
    String mobile;

    public SmsRequest() {
    }

    public SmsRequest(String mobile) {
        this.mobile = mobile;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "SmsRequest{" +
                "mobile='" + mobile + '\'' +
                '}';
    }
}
