/**
 * huijava.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.huijava.superiorjavablogs.common.enums;

import lombok.Getter;

/**
 * 博客数据对应数据库中的字段
 *
 * @author chenhx
 * @version BlogDataEnum.java, v 0.1 2018-08-03 上午 12:08
 */
@Getter
public enum BlogFieldEnum {
    /**
     * 被顶数
     */
    TOP_COUNT("top_count", "被顶数")
    /*
     * 浏览量
     */, VIEW_COUNT("view_count", "浏览量")
    /*
     * 评论数
     */, COMMENT_COUNT("comment_count", "评论数")
    /*
     * 收藏数
     */, COLLECT_COUNT("collect_count", "收藏数");
    private String fieldName;
    private String comment;

    BlogFieldEnum(String fieldName, String comment) {
        this.fieldName = fieldName;
        this.comment = comment;
    }

    public static BlogFieldEnum getBlogFieldEnumByFieldName(String fieldName) {
        BlogFieldEnum[] blogFieldEnums = BlogFieldEnum.values();
        for (BlogFieldEnum blogFieldEnum : blogFieldEnums) {
            if (blogFieldEnum.getFieldName().equals(fieldName)) {
                return blogFieldEnum;
            }
        }
        return null;
    }

}
