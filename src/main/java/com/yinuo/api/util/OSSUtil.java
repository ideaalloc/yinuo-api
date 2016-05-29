package com.yinuo.api.util;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import com.aliyun.oss.model.ObjectMetadata;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

/**
 * Title.
 * <p/>
 * Description.
 *
 * @author Bill Lv {@literal <billcc.lv@hotmail.com>}
 * @version 1.0
 * @since 2015-06-03
 */
public class OSSUtil {
    public static void uploadFile(OSSClient client, String bucketName, String key, File file, String contentType)
            throws OSSException, ClientException, FileNotFoundException {
        ObjectMetadata objectMeta = new ObjectMetadata();
        objectMeta.setContentLength(file.length());
        // 可以在metadata中标记文件类型
        objectMeta.setContentType(contentType);

        InputStream input = new FileInputStream(file);
        client.putObject(bucketName, key, input, objectMeta);
    }

    public static ObjectMetadata downloadFile(OSSClient client, String bucketName, String key, File file)
            throws OSSException, ClientException {
        return client.getObject(new GetObjectRequest(bucketName, key),
                file);
    }

    public static void deleteBucket(OSSClient client, String bucketName)
            throws OSSException, ClientException {
        ObjectListing ObjectListing = client.listObjects(bucketName);
        List<OSSObjectSummary> listDeletes = ObjectListing
                .getObjectSummaries();
        for (int i = 0; i < listDeletes.size(); i++) {
            String objectName = listDeletes.get(i).getKey();
            // 如果不为空，先删除bucket下的文件
            client.deleteObject(bucketName, objectName);
        }
        client.deleteBucket(bucketName);
    }
}
