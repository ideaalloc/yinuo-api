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
public class ImageResponse extends BaseResponse {
    ImageVo result;

    public ImageResponse() {
    }

    public ImageResponse(Integer code, String message, ImageVo result) {
        super(code, message);
        this.result = result;
    }

    public ImageVo getResult() {
        return result;
    }

    public void setResult(ImageVo result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "ImageResponse{" +
                "result=" + result +
                "} " + super.toString();
    }
}
