package com.yinuo.api.util;

import com.alibaba.fastjson.JSON;
import com.yinuo.api.client.CommonClient;
import com.yinuo.api.config.SmsConfig;
import com.yinuo.api.model.vo.SmsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author Bill Lv {@literal <billcc.lv@hotmail.com>}
 * @version 1.0
 * @since 2016-05-26
 */
public class SmsUtil {
    static final Logger LOGGER = LoggerFactory.getLogger(SmsUtil.class);
    static final SmsConfig smsConfig = SmsConfig.getInstance();
    static final CommonClient smsClient = CommonClient.getInstance();

    public static SmsResponse sendVMsg(String mobile, String vcode) throws IOException {
        final String result = smsClient.get(smsConfig.getUrl() + "?" + buildQueryString(mobile, vcode));
        return JSON.parseObject(result, SmsResponse.class);
    }

    private static String buildQueryString(String mobile, String vcode) throws UnsupportedEncodingException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("mobile=").append(mobile)
                .append("&tpl_id=").append(smsConfig.getTpl())
                .append("&tpl_value=").append(URLEncoder.encode("#code#=" + vcode, "UTF-8"))
                .append("&key=").append(smsConfig.getAppKey())
                .append("&dtype=").append("json");
        return stringBuilder.toString();
    }

}
