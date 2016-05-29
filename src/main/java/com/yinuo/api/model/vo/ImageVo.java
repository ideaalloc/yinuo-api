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
public class ImageVo implements Serializable {
    String relatedId;
    Integer type;
    String largeUrl;
    String middleUrl;
    String smallUrl;

    public ImageVo() {
    }

    public ImageVo(String relatedId, Integer type, String largeUrl, String middleUrl, String smallUrl) {
        this.relatedId = relatedId;
        this.type = type;
        this.largeUrl = largeUrl;
        this.middleUrl = middleUrl;
        this.smallUrl = smallUrl;
    }

    public String getRelatedId() {
        return relatedId;
    }

    public void setRelatedId(String relatedId) {
        this.relatedId = relatedId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getLargeUrl() {
        return largeUrl;
    }

    public void setLargeUrl(String largeUrl) {
        this.largeUrl = largeUrl;
    }

    public String getMiddleUrl() {
        return middleUrl;
    }

    public void setMiddleUrl(String middleUrl) {
        this.middleUrl = middleUrl;
    }

    public String getSmallUrl() {
        return smallUrl;
    }

    public void setSmallUrl(String smallUrl) {
        this.smallUrl = smallUrl;
    }

    @Override
    public String toString() {
        return "ImageVo{" +
                "relatedId='" + relatedId + '\'' +
                ", type=" + type +
                ", largeUrl='" + largeUrl + '\'' +
                ", middleUrl='" + middleUrl + '\'' +
                ", smallUrl='" + smallUrl + '\'' +
                '}';
    }
}
