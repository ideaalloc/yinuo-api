package com.yinuo.api.repository.po;

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
public class ImageMapping implements Serializable {
    Long image;
    Integer type;
    Long relatedId;

    public ImageMapping() {
    }

    public ImageMapping(Long image, Integer type, Long relatedId) {
        this.image = image;
        this.type = type;
        this.relatedId = relatedId;
    }

    public Long getImage() {
        return image;
    }

    public void setImage(Long image) {
        this.image = image;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getRelatedId() {
        return relatedId;
    }

    public void setRelatedId(Long relatedId) {
        this.relatedId = relatedId;
    }

    @Override
    public String toString() {
        return "ImageMapping{" +
                "image=" + image +
                ", type=" + type +
                ", relatedId=" + relatedId +
                '}';
    }
}
