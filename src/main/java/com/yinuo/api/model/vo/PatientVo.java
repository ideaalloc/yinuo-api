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
public class PatientVo implements Serializable {
    String id;
    String name;
    String sex;
    String age;
    String number;
    String phone;
    String phonedoc;
    String comments;

    public PatientVo() {

    }

    public PatientVo(String id, String name, String sex, String age, String number, String phone, String phonedoc, String comments) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.number = number;
        this.phone = phone;
        this.phonedoc = phonedoc;
        this.comments = comments;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhonedoc() {
        return phonedoc;
    }

    public void setPhonedoc(String phonedoc) {
        this.phonedoc = phonedoc;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "PatientVo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age='" + age + '\'' +
                ", number='" + number + '\'' +
                ", phone='" + phone + '\'' +
                ", phonedoc='" + phonedoc + '\'' +
                ", comments='" + comments + '\'' +
                "}";
    }
}
