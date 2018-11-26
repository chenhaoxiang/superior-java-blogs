/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.huijava.superiorjavablogs.form;

import com.huijava.superiorjavablogs.common.base.BaseEntity;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 后台添加博客的表单对象
 *
 * @author chenhx
 * @version BlogForm.java, v 0.1 2018-08-30 下午 11:05
 */
@Data
@ToString(callSuper = true)
public class BlogForm extends BaseEntity {
    /**
     * 标题
     * field name : blogs.title
     */
    @NotBlank(message = "标题不能为空")
    @Length(max = 254, message = "标题最长为254个字符")
    private String title;

    /**
     * 摘要
     */
    @Length(max = 500, message = "摘要最长为500个字符")
    private String summary;

    /**
     * 作者
     * field name : blogs.author
     */
    private String author;

    /**
     * 没填写原文链接，默认就是在本网站的文章链接
     * 原文链接
     * field name : blogs.original_url
     */
    private String originalUrl;

    /**
     * 类别id
     * field name : blogs.category_id
     */
    @NotNull(message = "类别id不能为空")
    private Integer categoryId;

    /**
     * 文章内容
     * field name : blogs.content
     */
    @NotBlank(message = "文章内容不能为空")
    private String content;

    /**
     * 标签，多个标签用;号隔开
     */
    private String tags;

}