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
 * @since 2016-05-25
 */
public class Users implements Serializable {
    Long id;
    String username;
    String passwordHash;
    Integer status;
    Date createTime;

    public Users() {
    }

    public Users(Long id, String username, String passwordHash, Integer status, Date createTime) {
        this.id = id;
        this.username = username;
        this.passwordHash = passwordHash;
        this.status = status;
        this.createTime = createTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                '}';
    }
}
