package com.yinuo.api.controller;

import com.aliyun.oss.OSSClient;
import com.yinuo.api.config.ImageConfig;
import com.yinuo.api.config.OssConfig;
import com.yinuo.api.model.annotation.CurrentlyLoggedUser;
import com.yinuo.api.model.type.OSSBucketDir;
import com.yinuo.api.model.vo.*;
import com.yinuo.api.service.ImageService;
import com.yinuo.api.service.UserService;
import com.yinuo.api.util.DataUtil;
import com.yinuo.api.util.OSSUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import net.coobird.thumbnailator.Thumbnails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author Bill Lv {@literal <billcc.lv@hotmail.com>}
 * @version 1.0
 * @since 2016-05-27
 */
@Api("/Image")
@RestController
@RequestMapping(value = "/v1/images")
public class ImageController {
    static final Logger LOGGER = LoggerFactory.getLogger(ImageController.class);

    @Autowired
    ImageConfig imageConfig;

    @Autowired
    OssConfig ossConfig;

    @Autowired
    OSSClient ossClient;

    @Autowired
    ImageService imageService;

    @Autowired
    UserService userService;

    @ApiOperation(value = "获取用户头像信息", nickname = "getAvatar")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = ImageResponse.class),
            @ApiResponse(code = 404, message = "Not Found", response = CommonResponse.class)})
    @RequestMapping(method = RequestMethod.GET, path = "/avatar", produces = "application/json")
    public ResponseEntity<BaseResponse> getAvatar(@CurrentlyLoggedUser Principal principal) {
        final ImageVo image = imageService.findImage(principal.getUid(), 0);
        if (image == null) {
            return new ResponseEntity<>(new CommonResponse(-1, "the user does not exist"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ImageResponse(0, "successfully", image), HttpStatus.OK);
    }

    @ApiOperation(value = "上传用户头像", nickname = "uploadAvatar")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = ImageResp.class),
            @ApiResponse(code = 400, message = "BAD REQUEST", response = CommonResponse.class)})
    @RequestMapping(method = RequestMethod.POST, path = "/avatar", produces = "application/json")
    @ResponseBody
    public ResponseEntity<BaseResponse> uploadAvatar(
            @RequestParam("avatar") MultipartFile[] materials, @CurrentlyLoggedUser Principal principal) {
        final List<String> materialIds = new ArrayList<>(materials.length);
        for (MultipartFile material : materials) {
            final String uniqueName = UUID.randomUUID().toString();

            Path tmpFile = null;
            File highQualityFile = null;
            File middleQualityFile = null;
            File smallQualityFile = null;
            try (final InputStream inputStream = material.getInputStream()) {
                tmpFile = Paths.get(imageConfig.getCompressTmpPath(), uniqueName);
                final File tmpFileToAdd = tmpFile.toFile();
                if (!tmpFileToAdd.getParentFile().exists()) {
                    tmpFileToAdd.getParentFile().mkdirs();
                }
                Files.copy(inputStream, tmpFile);
                BufferedImage bufferedImage = ImageIO.read(new FileInputStream(tmpFile.toFile()));

                final String highQualityName = imageConfig.getHighQualityPrefix() + uniqueName + ".JPG";
                highQualityFile = Paths.get(imageConfig.getCompressTmpPath(), highQualityName).toFile();
                Thumbnails.of(new FileInputStream(tmpFile.toFile()))
                        .size(bufferedImage.getWidth(), bufferedImage.getHeight())
                        .outputQuality(imageConfig.getHighQualityCompressRatio())
                        .toFile(highQualityFile);

                final String middleQualityName = imageConfig.getMiddleQualityPrefix() + uniqueName + ".JPG";
                middleQualityFile = Paths.get(imageConfig.getCompressTmpPath(), middleQualityName).toFile();
                Thumbnails.of(new FileInputStream(tmpFile.toFile()))
                        .size(bufferedImage.getWidth(), bufferedImage.getHeight())
                        .outputQuality(imageConfig.getMiddleQualityCompressRatio())
                        .toFile(middleQualityFile);

                final String smallQualityName = imageConfig.getSmallQualityPrefix() + uniqueName + ".JPG";
                smallQualityFile = Paths.get(imageConfig.getCompressTmpPath(), smallQualityName).toFile();
                Thumbnails.of(new FileInputStream(tmpFile.toFile()))
                        .size(bufferedImage.getWidth(), bufferedImage.getHeight())
                        .outputQuality(imageConfig.getSmallQualityCompressRatio())
                        .toFile(smallQualityFile);

                final Long uid = principal.getUid();

                final String highKeyName = createAvatarKeyName(uid, uniqueName, imageConfig.getHighQualityPrefix());
                LOGGER.info("high key name: {}", highKeyName);
                OSSUtil.uploadFile(ossClient, ossConfig.getBucket(), highKeyName, highQualityFile, JPG);
                final String middleKeyName = createAvatarKeyName(uid, uniqueName, imageConfig.getMiddleQualityPrefix());
                LOGGER.info("middle key name: {}", middleKeyName);
                OSSUtil.uploadFile(ossClient, ossConfig.getBucket(), middleKeyName, middleQualityFile, JPG);
                final String smallKeyName = createAvatarKeyName(uid, uniqueName, imageConfig.getSmallQualityPrefix());
                LOGGER.info("small key name: {}", smallKeyName);
                OSSUtil.uploadFile(ossClient, ossConfig.getBucket(), smallKeyName, smallQualityFile, JPG);

                final Long picId = imageService.addImage(new ImageVo(String.valueOf(uid), 0, createAvatarImageAccessUrl(highKeyName), createAvatarImageAccessUrl(middleKeyName), createAvatarImageAccessUrl(smallKeyName)));

                materialIds.add(String.valueOf(picId));
            } catch (IOException e) {
                LOGGER.error("read or write avatar picture error", e);
                return new ResponseEntity<>(new CommonResponse(-2, "read or write avatar picture error: " + e.getCause()), HttpStatus.BAD_REQUEST);
            } finally {
                if (tmpFile != null) {
                    tmpFile.toFile().delete();
                }
                if (highQualityFile != null) {
                    highQualityFile.delete();
                }
                if (middleQualityFile != null) {
                    middleQualityFile.delete();
                }
                if (smallQualityFile != null) {
                    smallQualityFile.delete();
                }
            }
        }

        return new ResponseEntity<>(new ImageResp(0, "upload successfully", materialIds), HttpStatus.OK);
    }

    private String createAvatarKeyName(final Long id, final String fileName, final String prefix) {
        StringBuilder sb = new StringBuilder();
        sb.append(OSSBucketDir.USER_AVATAR.getBucketDirName()).append('/').append(DataUtil.getOSSkeyFullPrefixByUid(id)).append(prefix).append(fileName);
        return sb.append(imageConfig.getSuffix()).toString();
    }

    private String createAvatarImageAccessUrl(final String keyName) {
        StringBuilder sb = new StringBuilder();
        final String endpoint = ossConfig.getEndpoint();
        final String bucket = ossConfig.getBucket();
        sb.append("http://").append(bucket).append('.').append(endpoint.substring("http://".length()));
        return sb.append('/').append(keyName).toString();
    }

    private static final String JPG = "image/jpeg";

    static class ImageResp extends BaseResponse {
        List<String> imageIds;

        public ImageResp() {
        }

        public ImageResp(Integer code, String message, List<String> imageIds) {
            super(code, message);
            this.imageIds = imageIds;
        }

        public List<String> getImageIds() {
            return imageIds;
        }

        public void setImageIds(List<String> imageIds) {
            this.imageIds = imageIds;
        }

        @Override
        public String toString() {
            return "ImageResp{" +
                    "imageIds=" + imageIds +
                    "} " + super.toString();
        }
    }
}
