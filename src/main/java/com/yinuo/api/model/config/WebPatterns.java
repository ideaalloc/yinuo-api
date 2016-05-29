package com.yinuo.api.model.config;

import java.io.Serializable;
import java.util.List;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author Bill Lv {@literal <billcc.lv@hotmail.com>}
 * @version 1.0
 * @since 2016-05-28
 */
public class WebPatterns implements Serializable {
    List<String> antPatterns;
    List<String> appClientPatterns;

    public WebPatterns() {
    }

    public WebPatterns(List<String> antPatterns, List<String> appClientPatterns) {
        this.antPatterns = antPatterns;
        this.appClientPatterns = appClientPatterns;
    }

    public List<String> getAntPatterns() {
        return antPatterns;
    }

    public void setAntPatterns(List<String> antPatterns) {
        this.antPatterns = antPatterns;
    }

    public List<String> getAppClientPatterns() {
        return appClientPatterns;
    }

    public void setAppClientPatterns(List<String> appClientPatterns) {
        this.appClientPatterns = appClientPatterns;
    }

    @Override
    public String toString() {
        return "WebPatterns{" +
                "antPatterns=" + antPatterns +
                ", appClientPatterns=" + appClientPatterns +
                '}';
    }
}
