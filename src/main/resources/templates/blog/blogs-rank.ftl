<!DOCTYPE html>
<html lang="en">

<head>
    <#include "../include/head.ftl"/>
    <title>博客排行-huijava</title>
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
                    <a>博客排行</a>
                </li>
            </ul>

        </div>

        <div class="col-md-12 column">

            <div class="row clearfix">


                <div class="col-md-8 column">

                    <div class="col-md-12 column">
                        <ul class="nav nav-tabs">
                            <li id="view_count">
                                <a href="/blogs-rank/view_count">浏览量排行</a>
                            </li>
                            <li id="top_count">
                                <a href="/blogs-rank/top_count">被顶排行</a>
                            </li>
                            <li id="comment_count">
                                <a href="/blogs-rank/comment_count">评论排行</a>
                            </li>
                            <li id="collect_count">
                                <a href="/blogs-rank/collect_count">收藏排行</a>
                            </li>
                        </ul>
                    </div>
                    <div class="col-md-12 column">
                        <div class="list-group">
                            <a class="list-group-item active"><span class="badge">1-50</span>
                            ${day!"365"}
                                <#if activeLi=='view_count'>
                                    天内浏览量排行
                                <#elseif activeLi=='top_count'>
                                    天内被顶排行
                                <#elseif activeLi=='comment_count'>
                                    天内评论排行
                                <#elseif activeLi=='collect_count'>
                                    天内收藏排行
                                </#if>
                            </a>
                            <#list blogsDTOList as blogs>
                            <div class="list-group-item">
                                <h4 class="list-group-item-heading">
                                    <a href="/blogs-details/${blogs.blogToken!""}"
                                       class="page-header-more">${blogs.title!""}</a>
                                    <a href="/user-info/${blogs.createId!""}">${blogs.nikeName!""}</a>
                                    <#if activeLi=='view_count'>
                                        ${blogs.viewCount!"0"}
                                    <#elseif activeLi=='top_count'>
                                        ${blogs.topCount!"0"}
                                    <#elseif activeLi=='comment_count'>
                                        ${blogs.commentCount!"0"}
                                    <#elseif activeLi=='collect_count'>
                                        ${blogs.collectCount!"0"}
                                    </#if>
                                </h4>
                            </div>
                            </#list>
                        </div>

                        <div class="col-md-12 column text-center">
                            <ul class="pagination pagination-lg ">
                                <li>
                                    <a href="#">Prev</a>
                                </li>
                                <li>
                                    <a href="#">1</a>
                                </li>
                                <li>
                                    <a href="#">2</a>
                                </li>
                                <li>
                                    <a href="#">3</a>
                                </li>
                                <li>
                                    <a href="#">4</a>
                                </li>
                                <li>
                                    <a href="#">5</a>
                                </li>
                                <li>
                                    <a href="#">Next</a>
                                </li>
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
        var activeLi = '${activeLi}';
        $("#" + activeLi).addClass("active");
    });
</script>
</body>

</html>