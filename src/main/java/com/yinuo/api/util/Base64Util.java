package com.yinuo.api.util;

import java.nio.charset.Charset;
import java.util.Base64;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author Bill Lv {@literal <billcc.lv@hotmail.com>}
 * @version 1.0
 * @since 2016-05-28
 */
public class Base64Util {
    static final String CHARSET_NAME = "UTF-8";

    public static String encode(String origin) {
        return new String(
                Base64.getEncoder().encode(
                        origin.getBytes(Charset.forName(CHARSET_NAME))), Charset.forName(CHARSET_NAME));
    }
}
