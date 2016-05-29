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
public class SmsResult implements Serializable {
    String sid;
    Integer fee;
    Integer count;

    public SmsResult() {
    }

    public SmsResult(String sid, Integer fee, Integer count) {
        this.sid = sid;
        this.fee = fee;
        this.count = count;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public Integer getFee() {
        return fee;
    }

    public void setFee(Integer fee) {
        this.fee = fee;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "SmsResult{" +
                "sid='" + sid + '\'' +
                ", fee=" + fee +
                ", count=" + count +
                '}';
    }
}
