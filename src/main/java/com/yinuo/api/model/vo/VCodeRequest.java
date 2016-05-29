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
public class VCodeRequest implements Serializable {
    String mobile;
    String vcode;

    public VCodeRequest() {
    }

    public VCodeRequest(String mobile, String vcode) {
        this.mobile = mobile;
        this.vcode = vcode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getVcode() {
        return vcode;
    }

    public void setVcode(String vcode) {
        this.vcode = vcode;
    }

    @Override
    public String toString() {
        return "VCodeRequest{" +
                "mobile='" + mobile + '\'' +
                ", vcode='" + vcode + '\'' +
                '}';
    }
}
