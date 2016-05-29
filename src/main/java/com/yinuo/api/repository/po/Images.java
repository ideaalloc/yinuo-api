package com.yinuo.api.repository.po;

import java.io.Serializable;
import java.util.Date;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author Bill Lv {@literal <billcc.lv@hotmail.com>}
 * @version 1.0
 * @since 2016-05-27
 */
public class Images implements Serializable {
    Long id;
    String largeUrl;
    String middleUrl;
    String smallUrl;
    Date createTime;

    public Images() {
    }

    public Images(Long id, String largeUrl, String middleUrl, String smallUrl, Date createTime) {
        this.id = id;
        this.largeUrl = largeUrl;
        this.middleUrl = middleUrl;
        this.smallUrl = smallUrl;
        this.createTime = createTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Images{" +
                "id=" + id +
                ", largeUrl='" + largeUrl + '\'' +
                ", middleUrl='" + middleUrl + '\'' +
                ", smallUrl='" + smallUrl + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
