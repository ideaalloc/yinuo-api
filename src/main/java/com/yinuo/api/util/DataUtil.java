package com.yinuo.api.util;

import org.apache.commons.lang3.StringUtils;

/**
 * Title.
 * <p/>
 * Description.
 *
 * @author Bill Lv {@literal <billcc.lv@hotmail.com>}
 * @version 1.0
 * @since 2015-10-30
 */
public class DataUtil {
    /**
     * 根据UID获取key前缀
     * @param uid
     * @return String key前缀
     */
    public static String getOSSkeyPrefixByUid(long uid) {
        String userIdString = StringUtils.leftPad(String.valueOf(uid), 5, '0');
        int length = userIdString.length();
        String firstSubDir = userIdString.substring(length - 4, length - 2);
        String secondSubDir = userIdString.substring(length - 2, length);
        StringBuilder keyPrefix = new StringBuilder();
        keyPrefix.append(firstSubDir).append("/").append(secondSubDir).append("/");
        return keyPrefix.toString();
    }

    /**
     * 根据UID获取key前缀(包含uid)
     * @param uid
     * @return String key前缀
     */
    public static String getOSSkeyFullPrefixByUid(long uid) {
        return getOSSkeyPrefixByUid(uid) + uid + "/";
    }
}
