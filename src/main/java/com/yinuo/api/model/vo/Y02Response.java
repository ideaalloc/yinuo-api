package com.yinuo.api.model.vo;

import java.util.List;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author Bill Lv {@literal <billcc.lv@hotmail.com>}
 * @version 1.0
 * @since 2016-05-20
 */
public class Y02Response extends BaseResponse {
    List<Y02Indexes> result;

    public Y02Response() {
    }

    public Y02Response(Integer code, String message, List<Y02Indexes> result) {
        super(code, message);
        this.result = result;
    }

    public List<Y02Indexes> getResult() {
        return result;
    }

    public void setResult(List<Y02Indexes> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Y02Response{" +
                "result=" + result +
                "} " + super.toString();
    }
}
