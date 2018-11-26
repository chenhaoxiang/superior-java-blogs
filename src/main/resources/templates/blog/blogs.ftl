<!DOCTYPE html>
<html lang="en">

<head>
    <#include "../include/head.ftl"/>
    <title>博客-huijava</title>
</head>

<body>
<div class="container">
    <div class="row clearfix">

        <div class="col-md-12 column">
            <#include "../include/navbar.ftl"/>
        </div>


        <div class="col-md-12 column">
        <#--<#include "../include/breadcrumb.ftl"/>-->
            <ul class="breadcrumb">
                <li>
                    <a href="/">首页</a>
                </li>
                <li class="active">
                    <a>博客</a>
                </li>
            </ul>

        </div>

        <div class="col-md-12 column">

            <div class="row clearfix">


                <div class="col-md-8 column">

                    <div class="col-md-12 column">
                        <ul class="nav nav-tabs">
                            <li id="category-li-0">
                                <a href="/blogs/0/1">全部</a>
                            </li>
                            <#if categoryList??>
                                <#list categoryList as category>
                                    <li id="category-li-${category.id}">
                                        <a href="/blogs/${category.id}/1">${category.name}</a>
                                    </li>
                                </#list>
                            </#if>
                        </ul>
                    </div>
                    <div class="col-md-12 column">

                        <#if blogsDTOList??>
                            <#list blogsDTOList as blogs>
                                <div class="col-md-12 column">
                                    <h3>
                                        <a href="/blogs-details/${blogs.blogToken!""}" class="page-header-more">
                                            ${blogs.title!""}
                                        </a>
                                    </h3>
                                    <p>
                                        <a href="/blogs-details/${blogs.blogToken!""}"
                                           class="color-black text-decoration-none">
                                            <#if blogs.summary??>
                                                <#if blogs.summary?length gt 150>
                                                    ${blogs.summary?substring(0,150)}...
                                                <#else >
                                                    ${blogs.summary}
                                                </#if>
                                            </#if>
                                        </a>
                                    </p>
                                    <p class="color-darkgrey">
                                        ${blogs.createTime?string('yyyy-MM-dd HH:mm:ss')}
                                        阅读数:${blogs.viewCount!'0'}
                                        评论数:${blogs.commentCount!'0'}
                                        <a class="text-decoration-none color-dimgray">作者:${blogs.author!''}</a>
                                    </p>
                                    <#if blogs.tagsList??>
                                        <#list blogs.tagsList as tags>
                                            <span class="label label-${tags.colorName}">${tags.name}</span>
                                        </#list>
                                    </#if>
                                </div>

                            </#list>
                        </#if>

                        <div class="col-md-12 column text-center">
                            <ul class="pagination pagination-lg ">
                                <#if pageNum gt 1>
                                    <li>
                                        <a href="/blogs/${nowCategoryId}/${pageNum-1}">Prev</a>
                                    </li>
                                </#if>

                                <#if pageNum gt 3>
                                    <li>
                                        <a href="/blogs/${nowCategoryId}/1">1</a>
                                    </li>
                                    <#if pageNum gt 4>
                                        <li class="disabled">
                                            <a>...</a>
                                        </li>
                                    </#if>
                                </#if>

                                <#if pageNum gt 2>
                                    <li>
                                        <a href="/blogs/${nowCategoryId}/${pageNum-2}">${pageNum-2}</a>
                                    </li>
                                </#if>
                                <#if pageNum gt 1>
                                    <li>
                                        <a href="/blogs/${nowCategoryId}/${pageNum-1}">${pageNum-1}</a>
                                    </li>
                                </#if>
                                <li class="disabled">
                                    <a class="active">${pageNum}</a>
                                </li>

                                <#if pageNum lt pages>
                                    <li>
                                        <a href="/blogs/${nowCategoryId}/${pageNum+1}">${pageNum+1}</a>
                                    </li>
                                </#if>
                                <#if pageNum+1 lt pages>
                                    <li>
                                        <a href="/blogs/${nowCategoryId}/${pageNum+2}">${pageNum+2}</a>
                                    </li>
                                </#if>
                                <#if pageNum+2 lt pages>
                                    <#if pageNum+3 lt pages>
                                        <li class="disabled">
                                            <a>...</a>
                                        </li>
                                    </#if>
                                    <li>
                                        <a href="/blogs/${nowCategoryId}/${pages}">${pages}</a>
                                    </li>
                                </#if>
                                <#if pageNum lt pages>
                                    <li>
                                        <a href="/blogs/${nowCategoryId}/${pageNum+1}">Next</a>
                                    </li>
                                </#if>

                            </ul>
                        </div>

                    </div>

                </div>

                <div class="col-md-4 column">

                    <#include "../include/tabbable.ftl"/>

                </div>

            </div>
        </div>


    </div>

</div>

<#include "../include/footer.ftl"/>

<#include "../include/base-js.ftl"/>

<script type="text/javascript">
    $(function () {
        var nowCategoryId = '${nowCategoryId}';
        $("#category-li-" + nowCategoryId).addClass("active");
    });
</script>
</body>

</html>