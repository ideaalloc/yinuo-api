package com.yinuo.api.repository.dao;

import com.yinuo.api.repository.po.ImageMapping;
import com.yinuo.api.repository.po.Images;
import org.apache.ibatis.annotations.Param;

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
public interface ImageDao {
    int insertImages(Images image);

    int insertImageMapping(ImageMapping imageMapping);

    List<Images> selectByRelatedIdAndType(@Param("relatedId") Long relatedId, @Param("type") Integer type);
}
