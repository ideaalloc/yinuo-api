package com.yinuo.api.model.vo;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author Bill Lv {@literal <billcc.lv@hotmail.com>}
 * @version 1.0
 * @since 2016-05-27
 */
public class PatientResponse extends BaseResponse {
    PatientVo result;

    public PatientResponse() {
    }

    public PatientResponse(Integer code, String message, PatientVo result) {
        super(code, message);
        this.result = result;
    }

    public PatientVo getResult() {
        return result;
    }

    public void setResult(PatientVo result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "PatientResponse{" +
                "result=" + result +
                "} " + super.toString();
    }
}
