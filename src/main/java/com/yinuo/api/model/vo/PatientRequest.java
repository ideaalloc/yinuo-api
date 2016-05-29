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
public class PatientRequest implements Serializable {
    String name;

    public PatientRequest() {
    }

    public PatientRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PatientRequest{" +
                "name='" + name + '\'' +
                '}';
    }
}
