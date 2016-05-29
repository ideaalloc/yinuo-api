package com.yinuo.api.model.type;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author Bill Lv {@literal <billcc.lv@hotmail.com>}
 * @version 1.0
 * @since 2016-05-27
 */
public enum OSSBucketDir {
    USER_AVATAR("avatar", "用户头像");

    private String bucketDirName;
    private String desc;

    OSSBucketDir(String bucketDirName, String desc) {
        this.bucketDirName = bucketDirName;
        this.desc = desc;
    }

    public String getBucketDirName() {
        return bucketDirName;
    }

    public String getDesc() {
        return desc;
    }
}
