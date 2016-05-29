package com.yinuo.api.model.vo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author Bill Lv {@literal <billcc.lv@hotmail.com>}
 * @version 1.0
 * @since 2016-05-27
 */
public class Y02Indexes implements Serializable {
    Long id;
    String phone;
    Date time;
    String spo2;
    String sys;
    String name;
    List<String> ecg;
    String bpr;
    String temp;
    String dia;
    String docPhone;
    String bpm;

    public Y02Indexes() {
    }

    public Y02Indexes(Long id, String phone, Date time, String spo2, String sys, String name, List<String> ecg, String bpr, String temp, String dia, String docPhone, String bpm) {
        this.id = id;
        this.phone = phone;
        this.time = time;
        this.spo2 = spo2;
        this.sys = sys;
        this.name = name;
        this.ecg = ecg;
        this.bpr = bpr;
        this.temp = temp;
        this.dia = dia;
        this.docPhone = docPhone;
        this.bpm = bpm;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTime() {
        return new SimpleDateFormat("yyyy-MM-dd hh:mm").format(time);
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getSpo2() {
        return spo2;
    }

    public void setSpo2(String spo2) {
        this.spo2 = spo2;
    }

    public String getSys() {
        return sys;
    }

    public void setSys(String sys) {
        this.sys = sys;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getEcg() {
        return ecg;
    }

    public void setEcg(List<String> ecg) {
        this.ecg = ecg;
    }

    public String getBpr() {
        return bpr;
    }

    public void setBpr(String bpr) {
        this.bpr = bpr;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getDocPhone() {
        return docPhone;
    }

    public void setDocPhone(String docPhone) {
        this.docPhone = docPhone;
    }

    public String getBpm() {
        return bpm;
    }

    public void setBpm(String bpm) {
        this.bpm = bpm;
    }

    @Override
    public String toString() {
        return "Y02Indexes{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", time=" + time +
                ", spo2='" + spo2 + '\'' +
                ", sys='" + sys + '\'' +
                ", name='" + name + '\'' +
                ", ecg=" + ecg +
                ", bpr='" + bpr + '\'' +
                ", temp='" + temp + '\'' +
                ", dia='" + dia + '\'' +
                ", docPhone='" + docPhone + '\'' +
                ", bpm='" + bpm + '\'' +
                '}';
    }
}
