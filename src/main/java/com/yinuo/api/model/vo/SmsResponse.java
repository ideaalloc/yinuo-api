package com.yinuo.api.model.vo;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author Bill Lv {@literal <billcc.lv@hotmail.com>}
 * @version 1.0
 * @since 2016-05-26
 */
public class SmsResponse implements Serializable {
    @JSONField(name = "error_code")
    Integer errorCode;
    SmsResult result;
    String reason;

    public SmsResponse() {
    }

    public SmsResponse(Integer errorCode, SmsResult result, String reason) {
        this.errorCode = errorCode;
        this.result = result;
        this.reason = reason;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public SmsResult getResult() {
        return result;
    }

    public void setResult(SmsResult result) {
        this.result = result;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "SmsResponse{" +
                "errorCode=" + errorCode +
                ", result=" + result +
                ", reason='" + reason + '\'' +
                '}';
    }
}
