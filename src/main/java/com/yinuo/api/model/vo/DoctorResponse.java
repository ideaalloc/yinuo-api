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
public class DoctorResponse extends BaseResponse {
    DoctorVo doctor;

    public DoctorResponse() {
    }

    public DoctorResponse(Integer code, String message, DoctorVo doctor) {
        super(code, message);
        this.doctor = doctor;
    }

    public DoctorVo getDoctor() {
        return doctor;
    }

    public void setDoctor(DoctorVo doctor) {
        this.doctor = doctor;
    }

    @Override
    public String toString() {
        return "DoctorResponse{" +
                "doctor=" + doctor +
                "} " + super.toString();
    }
}
