package com.yinuo.api.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author Bill Lv {@literal <billcc.lv@hotmail.com>}
 * @version 1.0
 * @since 2016-05-27
 */
public class DateUtil {
    public static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static String formatDate(Date date) {
        return new SimpleDateFormat(DEFAULT_DATETIME_FORMAT).format(date);
    }
}
