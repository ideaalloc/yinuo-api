package com.yinuo.api.model.vo;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author Bill Lv {@literal <billcc.lv@hotmail.com>}
 * @version 1.0
 * @since 2016-05-25
 */
public class CommonResponse extends BaseResponse {
    public CommonResponse() {
    }

    public CommonResponse(Integer code, String message) {
        super(code, message);
    }

    @Override
    public String toString() {
        return "CommonResponse{} " + super.toString();
    }
}
