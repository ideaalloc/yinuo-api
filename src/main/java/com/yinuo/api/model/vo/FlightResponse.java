package com.yinuo.api.model.vo;

import com.yinuo.api.repository.po.Flights;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author Bill Lv {@literal <billcc.lv@hotmail.com>}
 * @version 1.0
 * @since 2016-05-27
 */
public class FlightResponse extends BaseResponse {
    Flights result;

    public FlightResponse() {
    }

    public FlightResponse(Integer code, String message, Flights result) {
        super(code, message);
        this.result = result;
    }

    public Flights getResult() {
        return result;
    }

    public void setResult(Flights result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "FlightResponse{" +
                "result=" + result +
                "} " + super.toString();
    }
}
