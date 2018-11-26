/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.huijava.superiorjavablogs.util;

import com.huijava.superiorjavablogs.common.enums.ColorNameEnum;
import com.huijava.superiorjavablogs.entity.BlogsTagsR;
import com.huijava.superiorjavablogs.entity.Tags;

import java.util.Date;

/**
 * 给一些Bean赋值默认值
 *
 * @author chenhx
 * @version DefaultBeanUtils.java, v 0.1 2018-09-19 下午 8:26
 */
public class DefaultBeanUtils {

    public static void defaultTags(Tags tags) {
        tags.setComment(tags.getComment() == null ? "" : tags.getComment());
        tags.setSort(tags.getSort() == null ? 0 : tags.getSort());
        tags.setColorName(tags.getColorName() == null ? ColorNameEnum.getRandomColorName() : tags.getColorName());
        tags.setCreateId(tags.getCreateId() == null ? 0 : tags.getCreateId());
        tags.setUpdateId(tags.getUpdateId() == null ? 0 : tags.getUpdateId());
        tags.setCreateTime(tags.getCreateTime() == null ? new Date() : tags.getCreateTime());
        tags.setUpdateTime(tags.getUpdateTime() == null ? new Date() : tags.getUpdateTime());
        tags.setStatus(tags.getStatus() == null ? 0 : tags.getStatus());
    }

    public static void defaultBlogsTagsR(BlogsTagsR blogsTagsR) {
        blogsTagsR.setCreateTime(blogsTagsR.getCreateTime() == null ? new Date() : blogsTagsR.getCreateTime());
        blogsTagsR.setUpdateTime(blogsTagsR.getUpdateTime() == null ? new Date() : blogsTagsR.getUpdateTime());
    }

}