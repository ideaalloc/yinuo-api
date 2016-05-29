package com.yinuo.api.util;

import org.junit.Test;

/**
 * Created by Bill Lv on 5/26/16.
 */
public class SmsUtilTest {
    @Test
    public void sendVMsg() throws Exception {
        SmsUtil.sendVMsg("1877107662", "短信验证测试");
    }
}