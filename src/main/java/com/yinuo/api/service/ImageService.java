package com.yinuo.api.service;

import com.yinuo.api.model.vo.ImageVo;
import com.yinuo.api.repository.dao.ImageDao;
import com.yinuo.api.repository.po.ImageMapping;
import com.yinuo.api.repository.po.Images;
import com.yinuo.api.util.IdUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author Bill Lv {@literal <billcc.lv@hotmail.com>}
 * @version 1.0
 * @since 2016-05-27
 */
@Service
public class ImageService {
    static final Logger LOGGER = LoggerFactory.getLogger(ImageService.class);

    @Autowired
    ImageDao imageDao;

    @Transactional
    public long addImage(ImageVo image) {
        final long imageId = IdUtil.generateId();
        imageDao.insertImages(new Images(imageId, image.getLargeUrl(), image.getMiddleUrl(), image.getSmallUrl(), new Date()));
        imageDao.insertImageMapping(new ImageMapping(imageId, image.getType(), Long.parseLong(image.getRelatedId())));
        return imageId;
    }

    @Transactional
    public ImageVo findImage(Long relatedId, Integer type) {
        final List<Images> images = imageDao.selectByRelatedIdAndType(relatedId, type);
        if (images == null || images.isEmpty()) {
            return null;
        }
        final Images image = images.get(0);
        return new ImageVo(String.valueOf(relatedId), type, image.getLargeUrl(), image.getMiddleUrl(), image.getSmallUrl());
    }
}
