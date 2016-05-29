package com.yinuo.api.model.vo;

import java.io.Serializable;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author Gary Yu {@literal <garyhbut@gmail.com>}
 * @version 1.0
 * @since 2016-05-26
 */
public class DoctorVo implements Serializable {
    String id;
    String name;
    String org;
    String title;
    String pic;
    String phone;

    public DoctorVo() {

    }

    public DoctorVo(String id, String name, String org, String title, String pic, String phone) {
        this.id = id;
        this.name = name;
        this.org = org;
        this.title = title;
        this.pic = pic;
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "DoctorVo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", org='" + org + '\'' +
                ", title='" + title + '\'' +
                ", pic='" + pic + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
