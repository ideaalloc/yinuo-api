package com.yinuo.api.model.vo;

import java.io.Serializable;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author Bill Lv {@literal <billcc.lv@hotmail.com>}
 * @version 1.0
 * @since 2016-05-20
 */
public class Y02Request implements Serializable {
    String docPhone;
    String phone;
    String name;

    public Y02Request() {
    }

    public Y02Request(String docPhone, String phone, String name) {
        this.docPhone = docPhone;
        this.phone = phone;
        this.name = name;
    }

    public String getDocPhone() {
        return docPhone;
    }

    public void setDocPhone(String docPhone) {
        this.docPhone = docPhone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Y02Request{" +
                "docPhone='" + docPhone + '\'' +
                ", phone='" + phone + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
